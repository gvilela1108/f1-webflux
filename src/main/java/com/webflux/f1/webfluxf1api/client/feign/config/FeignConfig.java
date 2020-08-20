package com.webflux.f1.webfluxf1api.client.feign.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webflux.f1.webfluxf1api.client.feign.F1ClientFactory;
import com.webflux.f1.webfluxf1api.client.feign.F1FeignClient;
import feign.*;
import feign.http2client.Http2Client;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;

@Configuration
public class FeignConfig {

  private final ObjectMapper mapper =
      new ObjectMapper()
          .registerModules(new Jdk8Module(), new JavaTimeModule())
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

  private final JacksonEncoder jacksonEncoder = new JacksonEncoder(mapper);
  private final JacksonDecoder jacksonDecoder = new JacksonDecoder(mapper);

  @Value("${ergast.url}")
  private String apiUrl;

  @Bean
  public F1FeignClient f1FeignClient() {
    return HystrixFeign.builder()
        .contract(feignContract())
        .encoder(jacksonEncoder)
        .decoder(new F1ClientDecoder())
        .logger(new Slf4jLogger())
        .logLevel(Logger.Level.FULL)
        .client(new Http2Client())
        .retryer(Retryer.NEVER_RETRY)
        .decode404()
        .target(new Target.HardCodedTarget<>(F1FeignClient.class, apiUrl), new F1ClientFactory());
  }

  @Bean
  public Contract feignContract() {
    return new Contract.Default();
  }

  @Bean
  public F1ClientFactory fallbackFactory() {
    return new F1ClientFactory();
  }

  class F1ClientDecoder extends JacksonDecoder {

    private static final String OPTIONAL_VOID_NAME = "java.util.Optional<java.lang.Void>";

    @Override
    public Object decode(Response response, Type type) throws IOException {
      final boolean isOptionalVoidType = type.getTypeName().equals(OPTIONAL_VOID_NAME);
      final boolean successfulStatus = HttpStatus.valueOf(response.status()).is2xxSuccessful();
      if (successfulStatus && isOptionalVoidType) {
        return Optional.of(response.body());
      }
      return jacksonDecoder.decode(response, type);
    }
  }
}

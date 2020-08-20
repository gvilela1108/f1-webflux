package com.webflux.f1.webfluxf1api.client.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class F1ClientFactory implements FallbackFactory<F1FeignClient> {

  @Override
  public F1FeignClient create(Throwable cause) {
    return new F1ClientFallback(cause);
  }
}

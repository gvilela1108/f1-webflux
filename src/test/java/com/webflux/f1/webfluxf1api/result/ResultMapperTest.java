package com.webflux.f1.webfluxf1api.result;

import com.webflux.f1.webfluxf1api.results.ResultMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.factory.F1Factory.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResultMapperTest {

  @Autowired private ResultMapper mapper;

  @Test
  public void shouldGetResult_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(resultClientBuilder(), SEASON, ROUND);
    assertEquals(result, resultBuilder(false));
  }

  @Test
  public void shouldGetResultResponse_whenFromEntityToClientIsCalled() {
    final var result =
        mapper.fromEntityToResponse(
            resultBuilder(false),
            driverResponseBuilder(),
            constructorResponseBuilder(),
            fastestLapResponseBuilder());
    assertEquals(result, resultClientBuilder());
  }

  @Test
  public void shouldKafkaResult_whenResultKafka() {
    final var resultKafka = mapper.resultKafka(resultKafkaBuilder(true));
    assertEquals(resultKafka, buildResultKafka());
  }
}

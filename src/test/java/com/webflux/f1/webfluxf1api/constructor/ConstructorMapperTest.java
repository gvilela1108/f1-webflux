package com.webflux.f1.webfluxf1api.constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.race.RaceFactory.constructorBuilder;
import static com.webflux.f1.webfluxf1api.race.RaceFactory.constructorResponseBuilder;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConstructorMapperTest {

  @Autowired private ConstructorMapper mapper;

  @Test
  public void shouldGetConstructor_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(constructorResponseBuilder());
    assertEquals(result, constructorBuilder());
  }

  @Test
  public void shouldGetConstructorResponse_whenFromEntityToClientIsCalled() {
    final var result = mapper.fromEntityToResponse(constructorBuilder());
    assertEquals(result, constructorResponseBuilder());
  }
}

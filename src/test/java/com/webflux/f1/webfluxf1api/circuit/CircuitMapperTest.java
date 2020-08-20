package com.webflux.f1.webfluxf1api.circuit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.race.RaceFactory.circuitBuilder;
import static com.webflux.f1.webfluxf1api.race.RaceFactory.circuitResponseBuilder;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CircuitMapperTest {

  @Autowired private CircuitMapper mapper;

  @Test
  public void shouldGetCircuit_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(circuitResponseBuilder());
    assertEquals(result, circuitBuilder());
  }

  @Test
  public void shouldGetCircuitResponse_whenFromEntityToClientIsCalled() {
    final var result = mapper.fromEntityToResponse(circuitBuilder());
    assertEquals(result, circuitResponseBuilder());
  }
}

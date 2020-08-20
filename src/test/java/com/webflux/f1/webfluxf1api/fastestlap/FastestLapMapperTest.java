package com.webflux.f1.webfluxf1api.fastestlap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.race.RaceFactory.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FastestLapMapperTest {

  @Autowired private FastestLapMapper mapper;

  @Test
  public void shouldGetFLap_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(fastestLapResponseBuilder(), SEASON, ROUND);
    assertEquals(result, driverBuilder());
  }

  @Test
  public void shouldGetFLapResponse_whenFromEntityToClientIsCalled() {
    final var result = mapper.fromEntityToClient(fastestLapBuilder());
    assertEquals(result, fastestLapResponseBuilder());
  }
}

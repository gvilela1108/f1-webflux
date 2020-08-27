package com.webflux.f1.webfluxf1api.race;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.factory.F1Factory.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RaceMapperTest {

  @Autowired private RaceMapper mapper;

  @Test
  public void shouldGetRaceResults_whenGetRaceResultsIsCalled() {
    final var result = mapper.getRaceResults(raceTableBuilder());
    assertEquals(result, raceResponseBuilder());
  }

  @Test
  public void shouldGetRaceResponse_whenFromEntityToResponseIsCalled() {
    final var result =
        mapper.fromEntityToResponse(
            raceBuilder(), circuitResponseBuilder(), resultsResponseBuilder());
    assertEquals(result, racesBuilder());
  }

  @Test
  public void shouldGetRace_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(racesBuilder());
    assertEquals(result, raceBuilder());
  }
}

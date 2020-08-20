package com.webflux.f1.webfluxf1api.race;

import com.webflux.f1.webfluxf1api.circuit.CircuitMapper;
import com.webflux.f1.webfluxf1api.circuit.CircuitRepository;
import com.webflux.f1.webfluxf1api.client.feign.F1FeignClient;
import com.webflux.f1.webfluxf1api.constructor.ConstructorMapper;
import com.webflux.f1.webfluxf1api.constructor.ConstructorRepository;
import com.webflux.f1.webfluxf1api.driver.DriverMapper;
import com.webflux.f1.webfluxf1api.driver.DriverRepository;
import com.webflux.f1.webfluxf1api.fastestlap.FastestLapMapper;
import com.webflux.f1.webfluxf1api.fastestlap.FastestLapRepository;
import com.webflux.f1.webfluxf1api.results.ResultMapper;
import com.webflux.f1.webfluxf1api.results.ResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.test.StepVerifier;

import static com.webflux.f1.webfluxf1api.race.RaceFactory.*;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RaceServiceImplTests {

  @Mock private F1FeignClient f1FeignClient;

  @Mock private CircuitMapper circuitMapper;
  @Mock private RaceMapper raceMapper;
  @Mock private ResultMapper resultMapper;
  @Mock private FastestLapMapper fastestLapMapper;
  @Mock private DriverMapper driverMapper;
  @Mock private ConstructorMapper constructorMapper;

  @Mock private CircuitRepository circuitRepository;
  @Mock private DriverRepository driverRepository;
  @Mock private ConstructorRepository constructorRepository;
  @Mock private FastestLapRepository fastestLapRepository;
  @Mock private RaceRepository raceRepository;
  @Mock private ResultRepository resultRepository;

  @InjectMocks private RaceServiceImpl service;

  @Test
  public void shouldReturnRaceResult_whenGetRaceResultBySeasonAndRound() {
    var circuit = circuitBuilder();
    var race = raceBuilder();
    var constructor = constructorBuilder();
    var driver = driverBuilder();
    var fastestLap = fastestLapBuilder();
    var results = resultBuilder();

    when(f1FeignClient.getRaceResults(SEASON, ROUND)).thenReturn(feignResponse());
    when(raceMapper.getRaceResults(raceTableBuilder())).thenReturn(raceResponseBuilder());
    when(raceRepository.findBySeasonAndRound(SEASON, ROUND)).thenReturn(null);
    when(circuitRepository.findByCircuitId(CIRCUIT_ID)).thenReturn(null);
    when(circuitMapper.fromClientToEntity(circuitResponseBuilder())).thenReturn(circuit);
    when(circuitRepository.save(circuit)).thenReturn(circuit);
    when(raceMapper.fromClientToEntity(racesBuilder())).thenReturn(race);
    when(raceRepository.save(race)).thenReturn(race);
    when(constructorRepository.findByConstructorId(CONSTRUCTOR_ID)).thenReturn(null);
    when(constructorMapper.fromClientToEntity(constructorResponseBuilder()))
        .thenReturn(constructor);
    when(constructorRepository.save(constructor)).thenReturn(constructor);
    when(driverRepository.findByDriverId(DRIVER_ID)).thenReturn(null);
    when(driverMapper.fromClientToEntity(driverResponseBuilder())).thenReturn(driver);
    when(driverRepository.save(driver)).thenReturn(driver);
    when(fastestLapMapper.fromClientToEntity(fastestLapResponseBuilder(), SEASON, ROUND, DRIVER_ID))
        .thenReturn(fastestLap);
    when(fastestLapRepository.save(fastestLap)).thenReturn(fastestLap);
    when(resultMapper.fromClientToEntity(resultClientBuilder(), SEASON, ROUND)).thenReturn(results);
    when(resultRepository.save(results)).thenReturn(results);

    final var result = service.getRaceResult(SEASON, ROUND);

    StepVerifier.create(result.log()).expectNext(raceResponseBuilder()).verifyComplete();

    verify(f1FeignClient, times(INTEGER_ONE)).getRaceResults(SEASON, ROUND);
    verify(raceMapper, times(INTEGER_ONE)).getRaceResults(raceTableBuilder());
    verify(raceRepository, times(INTEGER_ONE)).findBySeasonAndRound(SEASON, ROUND);
    verify(circuitRepository, times(INTEGER_ONE)).findByCircuitId(CIRCUIT_ID);
    verify(circuitMapper, times(INTEGER_ONE)).fromClientToEntity(circuitResponseBuilder());
    verify(circuitRepository, times(INTEGER_ONE)).save(circuit);
    verify(raceMapper, times(INTEGER_ONE)).fromClientToEntity(racesBuilder());
    verify(raceRepository, times(INTEGER_ONE)).save(race);
    verify(constructorRepository, times(INTEGER_ONE)).findByConstructorId(CONSTRUCTOR_ID);
    verify(constructorMapper, times(INTEGER_ONE)).fromClientToEntity(constructorResponseBuilder());
    verify(constructorRepository, times(INTEGER_ONE)).save(constructor);
    verify(driverRepository, times(INTEGER_ONE)).findByDriverId(DRIVER_ID);
    verify(driverMapper, times(INTEGER_ONE)).fromClientToEntity(driverResponseBuilder());
    verify(driverRepository, times(INTEGER_ONE)).save(driver);
    verify(fastestLapMapper, times(INTEGER_ONE))
        .fromClientToEntity(fastestLapResponseBuilder(), SEASON, ROUND,DRIVER_ID);
    verify(fastestLapRepository, times(INTEGER_ONE)).save(fastestLap);
    verify(resultMapper, times(INTEGER_ONE))
        .fromClientToEntity(resultClientBuilder(), SEASON, ROUND);
    verify(resultRepository, times(INTEGER_ONE)).save(results);
  }

  @Test
  public void shouldReturnAllRaceResult_whenGetResultsBySeason() {

    when(raceRepository.findRacesBySeason(SEASON)).thenReturn(raceListBuilder());
    when(circuitRepository.findByCircuitId(CIRCUIT_ID)).thenReturn(circuitBuilder());
    when(resultRepository.findBySeasonAndRound(SEASON, ROUND)).thenReturn(resultListBuilder());
    when(driverRepository.findByDriverId(DRIVER_ID)).thenReturn(driverBuilder());
    when(constructorRepository.findByConstructorId(CONSTRUCTOR_ID))
        .thenReturn(constructorBuilder());
    when(fastestLapRepository.findBySeasonAndRoundAndDriverId(SEASON, ROUND,DRIVER_ID)).thenReturn(fastestLapBuilder());
    when(driverMapper.fromEntityToResponse(driverBuilder())).thenReturn(driverResponseBuilder());
    when(constructorMapper.fromEntityToResponse(constructorBuilder()))
        .thenReturn(constructorResponseBuilder());
    when(fastestLapMapper.fromEntityToClient(fastestLapBuilder()))
        .thenReturn(fastestLapResponseBuilder());
    when(resultMapper.fromEntityToResponse(
            resultBuilder(),
            driverResponseBuilder(),
            constructorResponseBuilder(),
            fastestLapResponseBuilder()))
        .thenReturn(resultClientBuilder());
    when(circuitMapper.fromEntityToResponse(circuitBuilder())).thenReturn(circuitResponseBuilder());
    when(raceMapper.fromEntityToResponse(
            raceBuilder(), circuitResponseBuilder(), resultsResponseBuilder()))
        .thenReturn(racesBuilder());

    final var result = service.getResultsFromSeason(SEASON);

    StepVerifier.create(result.log()).expectNext(raceResponseBuilder()).verifyComplete();

    verify(raceRepository, times(INTEGER_ONE)).findRacesBySeason(SEASON);
    verify(circuitRepository, times(INTEGER_ONE)).findByCircuitId(CIRCUIT_ID);
    verify(resultRepository, times(INTEGER_ONE)).findBySeasonAndRound(SEASON, ROUND);
    verify(driverRepository, times(INTEGER_ONE)).findByDriverId(DRIVER_ID);
    verify(constructorRepository, times(INTEGER_ONE)).findByConstructorId(CONSTRUCTOR_ID);
    verify(fastestLapRepository, times(INTEGER_ONE)).findBySeasonAndRoundAndDriverId(SEASON, ROUND,DRIVER_ID);
    verify(driverMapper, times(INTEGER_ONE)).fromEntityToResponse(driverBuilder());
    verify(constructorMapper, times(INTEGER_ONE)).fromEntityToResponse(constructorBuilder());
    verify(fastestLapMapper, times(INTEGER_ONE)).fromEntityToClient(fastestLapBuilder());
    verify(resultMapper, times(INTEGER_ONE))
        .fromEntityToResponse(
            resultBuilder(),
            driverResponseBuilder(),
            constructorResponseBuilder(),
            fastestLapResponseBuilder());
    verify(circuitMapper, times(INTEGER_ONE)).fromEntityToResponse(circuitBuilder());
    verify(raceMapper, times(INTEGER_ONE))
        .fromEntityToResponse(raceBuilder(), circuitResponseBuilder(), resultsResponseBuilder());
  }
}

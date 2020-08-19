package com.webflux.f1.webfluxf1api.race;

import com.webflux.f1.webfluxf1api.circuit.Circuit;
import com.webflux.f1.webfluxf1api.circuit.CircuitMapper;
import com.webflux.f1.webfluxf1api.circuit.CircuitRepository;
import com.webflux.f1.webfluxf1api.client.feign.F1FeignClient;
import com.webflux.f1.webfluxf1api.client.feign.response.Races;
import com.webflux.f1.webfluxf1api.client.feign.response.ResultsResponse;
import com.webflux.f1.webfluxf1api.constructor.ConstructorMapper;
import com.webflux.f1.webfluxf1api.constructor.ConstructorRepository;
import com.webflux.f1.webfluxf1api.driver.DriverMapper;
import com.webflux.f1.webfluxf1api.driver.DriverRepository;
import com.webflux.f1.webfluxf1api.exception.InternalErrorException;
import com.webflux.f1.webfluxf1api.exception.NotFoundException;
import com.webflux.f1.webfluxf1api.exception.ValidationException;
import com.webflux.f1.webfluxf1api.fatestlap.FastestLap;
import com.webflux.f1.webfluxf1api.fatestlap.FastestLapMapper;
import com.webflux.f1.webfluxf1api.fatestlap.FastestLapRepository;
import com.webflux.f1.webfluxf1api.results.ResultMapper;
import com.webflux.f1.webfluxf1api.results.ResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.webflux.f1.webfluxf1api.exception.ErrorCode.*;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RaceServiceImpl implements RaceService {

    private final F1FeignClient f1FeignClient;

    private final CircuitMapper circuitMapper;
    private final RaceMapper raceMapper;
    private final ResultMapper resultMapper;
    private final FastestLapMapper fastestLapMapper;
    private final DriverMapper driverMapper;
    private final ConstructorMapper constructorMapper;

    private final CircuitRepository circuitRepository;
    private final DriverRepository driverRepository;
    private final ConstructorRepository constructorRepository;
    private final FastestLapRepository fastestLapRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;


    @Override
    public Mono<RaceResponse> getRaceResult(int season, String round) {

        if (season > LocalDate.now().getYear()) {
            log.debug("Temporada superior ao ano corrente m=getRaceResults season={} round={}", season, round);
            throw new ValidationException(SEASON_ERROR);
        }

        var raceData = f1FeignClient.getRaceResults(season, round);
        final var cRef =
                new Object() {
                    RaceResponse response;
                };

        raceData.ifPresentOrElse(
                results -> {
                    cRef.response = raceMapper.getRaceResults(results.getMrData().getRaceTable());
                    if (isEmpty(cRef.response.getRaceResult())) {
                        log.debug("Corrida não encontrada m=getRaceResults season={} round={}", season, round);
                        throw new NotFoundException(RACE_NOT_FOUND);
                    }

                    saveRaceResult(cRef.response.getRaceResult(), round, season);
                },
                () -> {
                    log.error("Erro no feign client m=getRaceResults season={} round={}", season, round);
                    throw new InternalErrorException(F1_CLIENT_ERROR);
                }
        );

        return Mono.just(cRef.response);
    }

    public void saveRaceResult(ArrayList<Races> races, String round, Integer season) {

        try {
            var raceExists = raceRepository.findBySeasonAndRound(season,round);
            if(isEmpty(raceExists)) {

                var circuitExists = circuitRepository.findByCircuitId(races.get(NumberUtils.INTEGER_ZERO).getCircuit().getCircuitId());
                if(isEmpty(circuitExists)) circuitRepository.save(circuitMapper.fromClientToEntity(races.get(NumberUtils.INTEGER_ZERO).getCircuit()));

                raceRepository.save(raceMapper.fromClientToEntity(races.get(NumberUtils.INTEGER_ZERO)));

                races.stream().forEach(result -> {
                    result.getResults().stream().forEach(resultRace -> {

                        var constructorExists = constructorRepository.findByConstructorId(resultRace.getConstructor().getConstructorId());
                        if(isEmpty(constructorExists)) constructorRepository.save(constructorMapper.fromClientToEntity(resultRace.getConstructor()));

                        var driverExists = driverRepository.findByDriverId(resultRace.getDriver().getDriverId());
                        if(isEmpty(driverExists)) driverRepository.save(driverMapper.fromClientToEntity(resultRace.getDriver()));

                        fastestLapRepository.save(fastestLapMapper.fromClientToEntity(resultRace.getFastestLap(),season,round));
                        resultRepository.save(resultMapper.fromClientToEntity(resultRace,round,season));

                    });

                });
            }

        } catch (Exception e) {
            System.out.println(e);
            log.error("Erro ao salvar na base de dados m=saveRaceResult season={} round={} e={}",season,round,e.getStackTrace());
            throw new InternalErrorException(DATABASE_ERROR);
        }


    }


    @Override
    public Flux<RaceResponse> getResultsFromSeason(int season) {

        var racesResponse = new ArrayList<Races>();
        var races = raceRepository.findRacesBySeason(season);
        races.stream().forEach(race -> {
            var resultsResponses = new ArrayList<ResultsResponse>();

            var circuit = circuitRepository.findByCircuitId(race.getCircuitId());
            var results = resultRepository.findBySeasonAndRound(race.getSeason(), race.getRound());
            results.stream().forEach(result -> {
                var driver = driverRepository.findByDriverId(result.getDriverId());
                var constructor = constructorRepository.findByConstructorId(result.getConstructorId());
                var fastestLap = fastestLapRepository.findBySeasonAndRound(race.getSeason(),race.getRound());

                resultsResponses.add(resultMapper.fromEntityToResponse(result,
                        driverMapper.fromEntityToResponse(driver),
                        constructorMapper.fromEntityToConstructor(constructor),
                        fastestLapMapper.fromEntityToClient(fastestLap)));

            });

            racesResponse.add(raceMapper.fromEntityToResponse(race, circuitMapper.fromEntityToResponse(circuit), resultsResponses));
        });

        return Flux.just(RaceResponse.builder().raceResult(racesResponse).build());
    }

}

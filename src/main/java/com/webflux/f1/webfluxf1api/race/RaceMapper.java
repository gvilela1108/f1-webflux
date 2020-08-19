package com.webflux.f1.webfluxf1api.race;

import com.webflux.f1.webfluxf1api.circuit.Circuit;
import com.webflux.f1.webfluxf1api.circuit.CircuitMapper;
import com.webflux.f1.webfluxf1api.client.feign.response.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.mapstruct.Mapper;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface RaceMapper {

    default RaceResponse getRaceResults(RaceTable raceTable) {
        return RaceResponse.builder().raceResult(raceTable.getRaces()).build();

    }

    default Race fromClientToEntity(Races races) {
        return Race.builder()
                .circuitId(races.getCircuit().getCircuitId())
                .date(LocalDate.parse(races.getDate()))
                .raceName(races.getRaceName())
                .round(races.getRound())
                .season(Integer.valueOf(races.getSeason()))
                .time(races.getTime())
                .url(races.getUrl())
                .build();
    }

    default Races fromEntityToResponse(Race race, CircuitResponse circuit, ArrayList<ResultsResponse> results) {

        return Races.builder()
                .circuit(circuit)
                .date(String.valueOf(race.getDate()))
                .raceName(race.getRaceName())
                .results(results)
                .round(race.getRound())
                .season(String.valueOf(race.getSeason()))
                .time(race.getTime())
                .url(race.getUrl())
                .build();
    }
}

package com.webflux.f1.webfluxf1api.fastestlap;

import com.webflux.f1.webfluxf1api.client.feign.response.AverageSpeed;
import com.webflux.f1.webfluxf1api.client.feign.response.FastestLapResponse;
import com.webflux.f1.webfluxf1api.client.feign.response.Time;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface FastestLapMapper {

  default FastestLap fromClientToEntity(
      FastestLapResponse fastestLapResponse, Integer season, String round, String driverId) {
    return FastestLap.builder()
        .driverId(driverId)
        .lap(isEmpty(fastestLapResponse.getLap()) ? 0 : Integer.valueOf(fastestLapResponse.getLap()))
        .rank(isEmpty(fastestLapResponse.getRank()) ? 0 : Integer.valueOf(fastestLapResponse.getRank()))
        .speed(isEmpty(fastestLapResponse.getAverageSpeed().getSpeed()) ? 0 : Float.valueOf(fastestLapResponse.getAverageSpeed().getSpeed()))
        .time(fastestLapResponse.getTime().getTime())
        .round(round)
        .season(season)
        .units(fastestLapResponse.getAverageSpeed().getUnits())
        .build();
  }

  default FastestLapResponse fromEntityToClient(FastestLap fastestLap) {
    var averageSpeed =
        AverageSpeed.builder()
            .speed(String.valueOf(fastestLap.getSpeed()))
            .units(fastestLap.getUnits())
            .build();
    var time = Time.builder().time(fastestLap.getTime()).build();

    return FastestLapResponse.builder()
        .averageSpeed(averageSpeed)
        .lap(String.valueOf(fastestLap.getLap()))
        .rank(String.valueOf(fastestLap.getRank()))
        .time(time)
        .build();
  }
}

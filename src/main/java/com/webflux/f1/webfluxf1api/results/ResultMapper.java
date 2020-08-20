package com.webflux.f1.webfluxf1api.results;

import com.webflux.f1.webfluxf1api.client.feign.response.*;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ResultMapper {

  default Result fromClientToEntity(ResultsResponse resultsResponse, Integer season, String round) {
    return Result.builder()
        .constructorId(resultsResponse.getConstructor().getConstructorId())
        .driverId(resultsResponse.getDriver().getDriverId())
        .grid(resultsResponse.getGrid())
        .laps(isEmpty(resultsResponse.getLaps()) ? 0 : Integer.valueOf(resultsResponse.getLaps()))
        .millis(
            isEmpty(resultsResponse.getTime())
                ? 0
                : isEmpty(resultsResponse.getTime().getMillis())
                    ? 0
                    : Integer.valueOf(resultsResponse.getTime().getMillis()))
        .number(resultsResponse.getNumber())
        .points(
            isEmpty(resultsResponse.getPoints()) ? 0 : Integer.valueOf(resultsResponse.getPoints()))
        .position(resultsResponse.getPosition())
        .positionText(resultsResponse.getPositionText())
        .round(round)
        .season(season)
        .status(resultsResponse.getStatus())
        .time(
            isEmpty(resultsResponse.getTime())
                ? "A"
                : isEmpty(resultsResponse.getTime().getTime())
                    ? "0"
                    : resultsResponse.getTime().getTime())
        .build();
  }

  default ResultsResponse fromEntityToResponse(
      Result result,
      DriverResponse driver,
      ConstructorResponse constructor,
      FastestLapResponse fastestLap) {
    var time =
        Time.builder().time(result.getTime()).millis(String.valueOf(result.getMillis())).build();

    return ResultsResponse.builder()
        .driver(driver)
        .constructor(constructor)
        .fastestLap(fastestLap)
        .time(time)
        .grid(result.getGrid())
        .laps(String.valueOf(result.getLaps()))
        .number(result.getNumber())
        .points(String.valueOf(result.getPoints()))
        .position(result.getPosition())
        .positionText(result.getPositionText())
        .status(result.getStatus())
        .build();
  }
}

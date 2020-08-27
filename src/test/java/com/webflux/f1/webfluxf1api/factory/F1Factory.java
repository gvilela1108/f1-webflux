package com.webflux.f1.webfluxf1api.factory;

import com.webflux.f1.webfluxf1api.circuit.Circuit;
import com.webflux.f1.webfluxf1api.client.feign.response.*;
import com.webflux.f1.webfluxf1api.constructor.Constructor;
import com.webflux.f1.webfluxf1api.driver.Driver;
import com.webflux.f1.webfluxf1api.fastestlap.FastestLap;
import com.webflux.f1.webfluxf1api.race.Race;
import com.webflux.f1.webfluxf1api.race.RaceResponse;
import com.webflux.f1.webfluxf1api.results.Result;
import com.webflux.f1.webfluxf1api.results.ResultKafka;
import org.apache.commons.lang3.math.NumberUtils;
import org.hamcrest.Factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public final class F1Factory {

  public static final Integer SEASON = 2020;
  public static final String ROUND = "1";
  public static final String CIRCUIT_ID = "red_bull_ring";
  public static final String CONSTRUCTOR_ID = "mercedes";
  public static final String DRIVER_ID = "leclerc";
  private static final String RACE_NAME = "Austrian Grand Prix";
  private static final String RACE_DATE = "2020-07-05";
  private static final String RACE_TIME = "13:10:00Z";
  private static final String RACE_URL = "https://en.wikipedia.org/wiki/2020_Austrian_Grand_Prix";
  private static final String LATITUDE = "47.2197";
  private static final String LONGITUDE = "14.7647";
  private static final String LOCALITY = "Spielburg";
  private static final String COUNTRY = "Austria";
  private static final String CIRCUIT_NAME = "Red Bull Ring";
  private static final String CIRCUIT_URL = "http://en.wikipedia.org/wiki/Red_Bull_Ring";
  private static final String CONSTRUCTOR_NAME = "Mercedes";
  private static final String CONSTRUCTOR_URL =
      "http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One";
  private static final String CONSTRUCTOR_NATIONALITY = "German";
  private static final String DRIVER_URL = "http://en.wikipedia.org/wiki/Charles_Leclerc";
  private static final String DRIVER_PNUMBER = "16";
  private static final String DRIVER_NATIONALITY = "Monegasque";
  private static final String DRIVER_GNAME = "Charles";
  private static final String DRIVER_FNAME = "Leclerc";
  private static final String DRIVER_BIRTH = "1997-10-16";
  private static final String DRIVER_CODE = "LEC";
  private static final String SPEED = "229.758";
  private static final String LAP = "68";
  private static final String RANK = "2";
  private static final String TIME_LAP = "1:07.657";
  private static final String UNITS = "kph";
  private static final String RESULT_GRID = "1";
  private static final String RESULT_LAPS = "71";
  private static final String RESULT_NUMBER = "77";
  private static final String RESULT_POINTS = "25";
  private static final String RESULT_POSITION = "1";
  private static final String RESULT_STATUS = "Finished";
  private static final String RESULT_MILLIS = "5455739";
  private static final String RESULT_TIME = "1:30:55.739";

  @Factory
  public static Optional<RaceData> feignResponse() {
    var raceData = RaceData.builder().mrData(mrDataBuilder()).build();

    return Optional.of(raceData);
  }

  private static MrData mrDataBuilder() {
    return MrData.builder().raceTable(raceTableBuilder()).build();
  }

  public static RaceTable raceTableBuilder() {
    return RaceTable.builder()
        .races(racesListBuilder())
        .round(ROUND)
        .season(String.valueOf(SEASON))
        .build();
  }

  public static RaceResponse raceResponseBuilder() {
    return RaceResponse.builder().raceResult(racesListBuilder()).build();
  }

  public static Race raceBuilder() {
    return Race.builder()
        .raceName(RACE_NAME)
        .circuitId(CIRCUIT_ID)
        .date(LocalDate.parse(RACE_DATE))
        .round(ROUND)
        .season(SEASON)
        .time(RACE_TIME)
        .url(RACE_URL)
        .build();
  }

  public static ArrayList<Race> raceListBuilder() {
    return new ArrayList<Race>() {
      {
        add(raceBuilder());
      }
    };
  }

  public static Races racesBuilder() {
    return Races.builder()
        .raceName(RACE_NAME)
        .circuit(circuitResponseBuilder())
        .date(RACE_DATE)
        .results(resultsResponseBuilder())
        .round(ROUND)
        .season(String.valueOf(SEASON))
        .time(RACE_TIME)
        .url(RACE_URL)
        .build();
  }

  private static ArrayList<Races> racesListBuilder() {
    return new ArrayList<Races>() {
      {
        add(racesBuilder());
      }
    };
  }

  public static CircuitResponse circuitResponseBuilder() {
    return CircuitResponse.builder()
        .circuitId(CIRCUIT_ID)
        .circuitName(CIRCUIT_NAME)
        .url(CIRCUIT_URL)
        .location(locationBuilder())
        .build();
  }

  public static Circuit circuitBuilder() {
    return Circuit.builder()
        .url(CIRCUIT_URL)
        .locality(LOCALITY)
        .country(COUNTRY)
        .circuitName(CIRCUIT_NAME)
        .circuitId(CIRCUIT_ID)
        .latitude(Float.valueOf(LATITUDE))
        .longitude(Float.valueOf(LONGITUDE))
        .build();
  }

  private static Location locationBuilder() {
    return Location.builder()
        .longitude(LONGITUDE)
        .locality(LOCALITY)
        .latitude(LATITUDE)
        .country(COUNTRY)
        .build();
  }

  public static Result resultBuilder(boolean kafka) {
    return Result.builder()
        .time(RESULT_TIME)
        .status(RESULT_STATUS)
        .season(SEASON)
        .round(ROUND)
        .positionText(RESULT_POSITION)
        .position(RESULT_POSITION)
        .points(Integer.valueOf(RESULT_POINTS))
        .number(RESULT_NUMBER)
        .millis(Integer.valueOf(RESULT_MILLIS))
        .laps(Integer.valueOf(RESULT_LAPS))
        .grid(RESULT_GRID)
        .driverId(DRIVER_ID)
        .constructorId(CONSTRUCTOR_ID)
        .sentKafka(kafka)
        .build();
  }

  public static Result resultKafkaBuilder(boolean kafka) {
    return Result.builder()
            .id(NumberUtils.INTEGER_ONE)
            .time(RESULT_TIME)
            .status(RESULT_STATUS)
            .season(SEASON)
            .round(ROUND)
            .positionText(RESULT_POSITION)
            .position(RESULT_POSITION)
            .points(Integer.valueOf(RESULT_POINTS))
            .number(RESULT_NUMBER)
            .millis(Integer.valueOf(RESULT_MILLIS))
            .laps(Integer.valueOf(RESULT_LAPS))
            .grid(RESULT_GRID)
            .driverId(DRIVER_ID)
            .constructorId(CONSTRUCTOR_ID)
            .sentKafka(kafka)
            .build();
  }

  public static ResultsResponse resultClientBuilder() {
    return ResultsResponse.builder()
        .constructor(constructorResponseBuilder())
        .driver(driverResponseBuilder())
        .fastestLap(fastestLapResponseBuilder())
        .grid(RESULT_GRID)
        .laps(RESULT_LAPS)
        .number(RESULT_NUMBER)
        .points(RESULT_POINTS)
        .position(RESULT_POSITION)
        .positionText(RESULT_POSITION)
        .status(RESULT_STATUS)
        .time(timeBuilder(RESULT_MILLIS, RESULT_TIME))
        .build();
  }

  public static ArrayList<Result> resultListBuilder() {
    return new ArrayList<Result>() {
      {
        add(resultBuilder(false));
      }
    };
  }

  public static ArrayList<Result> resultListKafkaBuilder() {
    return new ArrayList<Result>() {
      {
        add(resultKafkaBuilder(true));
      }
    };
  }

  public static ArrayList<ResultsResponse> resultsResponseBuilder() {
    return new ArrayList<ResultsResponse>() {
      {
        add(resultClientBuilder());
      }
    };
  }

  public static ConstructorResponse constructorResponseBuilder() {
    return ConstructorResponse.builder()
        .url(CONSTRUCTOR_URL)
        .nationality(CONSTRUCTOR_NATIONALITY)
        .name(CONSTRUCTOR_NAME)
        .constructorId(CONSTRUCTOR_ID)
        .build();
  }

  public static Constructor constructorBuilder() {
    return Constructor.builder()
        .url(CONSTRUCTOR_URL)
        .nationality(CONSTRUCTOR_NATIONALITY)
        .name(CONSTRUCTOR_NAME)
        .constructorId(CONSTRUCTOR_ID)
        .build();
  }

  public static DriverResponse driverResponseBuilder() {
    return DriverResponse.builder()
        .url(DRIVER_URL)
        .permanentNumber(DRIVER_PNUMBER)
        .nationality(DRIVER_NATIONALITY)
        .givenName(DRIVER_GNAME)
        .familyName(DRIVER_FNAME)
        .driverId(DRIVER_ID)
        .dateOfBirth(DRIVER_BIRTH)
        .code(DRIVER_CODE)
        .build();
  }

  public static Driver driverBuilder() {
    return Driver.builder()
        .url(DRIVER_URL)
        .permanentNumber(Integer.valueOf(DRIVER_PNUMBER))
        .nationality(DRIVER_NATIONALITY)
        .givenName(DRIVER_GNAME)
        .familyName(DRIVER_FNAME)
        .driverId(DRIVER_ID)
        .dateOfBirth(LocalDate.parse(DRIVER_BIRTH))
        .code(DRIVER_CODE)
        .build();
  }

  public static FastestLapResponse fastestLapResponseBuilder() {
    return FastestLapResponse.builder()
        .time(timeBuilder(null, TIME_LAP))
        .rank(RANK)
        .lap(LAP)
        .averageSpeed(averageSpeedBuilder())
        .build();
  }

  public static FastestLap fastestLapBuilder() {
    return FastestLap.builder()
        .speed(Float.valueOf(SPEED))
        .lap(Integer.valueOf(LAP))
        .rank(Integer.valueOf(RANK))
        .driverId(DRIVER_ID)
        .time(TIME_LAP)
        .round(ROUND)
        .season(SEASON)
        .units(UNITS)
        .build();
  }

  private static AverageSpeed averageSpeedBuilder() {
    return AverageSpeed.builder().speed(SPEED).units(UNITS).build();
  }

  private static Time timeBuilder(String millis, String time) {
    return Time.builder().millis(millis).time(time).build();
  }

  public static ResultKafka buildResultKafka() {
    return ResultKafka.builder()
        .id(1)
        .time(RESULT_TIME)
        .status(RESULT_STATUS)
        .season(SEASON)
        .round(ROUND)
        .positionText(RESULT_POSITION)
        .position(RESULT_POSITION)
        .points(Integer.valueOf(RESULT_POINTS))
        .number(RESULT_NUMBER)
        .millis(Integer.valueOf(RESULT_MILLIS))
        .laps(Integer.valueOf(RESULT_LAPS))
        .grid(RESULT_GRID)
        .driverId(DRIVER_ID)
        .constructorId(CONSTRUCTOR_ID)
        .build();
  }
}

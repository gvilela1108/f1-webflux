package com.webflux.f1.webfluxf1api.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultKafka {

    private Integer id;
    private Integer season;
    private String round;
    private String number;
    private String position;
    private String positionText;
    private Integer points;
    private String driverId;
    private String constructorId;
    private String grid;
    private Integer laps;
    private String status;
    private Integer millis;
    private String time;
}

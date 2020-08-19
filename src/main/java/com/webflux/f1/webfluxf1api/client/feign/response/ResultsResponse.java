package com.webflux.f1.webfluxf1api.client.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsResponse {

    @JsonProperty("number")
    private String number;

    @JsonProperty("position")
    private String position;

    @JsonProperty("positionText")
    private String positionText;

    @JsonProperty("points")
    private String points;

    @JsonProperty("Driver")
    private DriverResponse driver;

    @JsonProperty("Constructor")
    private ConstructorResponse constructor;

    @JsonProperty("grid")
    private String grid;

    @JsonProperty("laps")
    private String laps;

    @JsonProperty("status")
    private String status;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("FastestLap")
    private FastestLapResponse fastestLap;

}

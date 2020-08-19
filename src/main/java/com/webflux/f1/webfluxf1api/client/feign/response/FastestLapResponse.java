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
public class FastestLapResponse {

    @JsonProperty("rank")
    private String rank;

    @JsonProperty("lap")
    private String lap;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("AverageSpeed")
    private AverageSpeed averageSpeed;

}

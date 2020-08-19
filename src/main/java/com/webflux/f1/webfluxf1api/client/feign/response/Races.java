package com.webflux.f1.webfluxf1api.client.feign.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Races {

    @JsonProperty("season")
    private String season;

    @JsonProperty("round")
    private String round;

    @JsonProperty("url")
    private String url;

    @JsonProperty("raceName")
    private String raceName;

    @JsonProperty("Circuit")
    private CircuitResponse circuit;

    @JsonProperty("date")
    private String date;

    @JsonProperty("time")
    private String time;

    @JsonProperty("Results")
    private ArrayList<ResultsResponse> results;

}

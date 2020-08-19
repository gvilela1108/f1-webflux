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
public class MrData {

    @JsonProperty("xmlns")
    private String xmlns;

    @JsonProperty("series")
    private String series;

    @JsonProperty("url")
    private String url;

    @JsonProperty("limit")
    private String limit;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("total")
    private String total;

    @JsonProperty("RaceTable")
    private RaceTable raceTable;


}

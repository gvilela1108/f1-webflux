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
public class RaceTable {

  @JsonProperty("season")
  private String season;

  @JsonProperty("round")
  private String round;

  @JsonProperty("Races")
  private ArrayList<Races> races;
}

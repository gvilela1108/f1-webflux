package com.webflux.f1.webfluxf1api.race;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webflux.f1.webfluxf1api.client.feign.response.Races;
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
public class RaceResponse {

  @JsonProperty("race_result")
  private ArrayList<Races> raceResult;
}

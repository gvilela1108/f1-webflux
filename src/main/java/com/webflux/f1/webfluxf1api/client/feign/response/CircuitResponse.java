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
public class CircuitResponse {

  @JsonProperty("circuitId")
  private String circuitId;

  @JsonProperty("url")
  private String url;

  @JsonProperty("circuitName")
  private String circuitName;

  @JsonProperty("Location")
  private Location location;
}

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
public class Location {

  @JsonProperty("lat")
  private String latitude;

  @JsonProperty("long")
  private String longitude;

  @JsonProperty("locality")
  private String locality;

  @JsonProperty("country")
  private String country;
}

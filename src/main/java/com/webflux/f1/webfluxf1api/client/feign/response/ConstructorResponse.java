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
public class ConstructorResponse {

  @JsonProperty("constructorId")
  private String constructorId;

  @JsonProperty("url")
  private String url;

  @JsonProperty("name")
  private String name;

  @JsonProperty("nationality")
  private String nationality;
}

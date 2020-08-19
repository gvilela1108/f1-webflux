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
public class DriverResponse {

    @JsonProperty("driverId")
    private String driverId;

    @JsonProperty("permanentNumber")
    private String permanentNumber;

    @JsonProperty("code")
    private String code;

    @JsonProperty("url")
    private String url;

    @JsonProperty("givenName")
    private String givenName;

    @JsonProperty("familyName")
    private String familyName;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("nationality")
    private String nationality;

}

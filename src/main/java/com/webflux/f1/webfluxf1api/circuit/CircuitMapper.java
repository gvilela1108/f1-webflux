package com.webflux.f1.webfluxf1api.circuit;

import com.webflux.f1.webfluxf1api.client.feign.response.CircuitResponse;
import com.webflux.f1.webfluxf1api.client.feign.response.Location;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CircuitMapper {

  default Circuit fromClientToEntity(CircuitResponse circuit) {
    return Circuit.builder()
        .circuitId(circuit.getCircuitId())
        .circuitName(circuit.getCircuitName())
        .country(circuit.getLocation().getCountry())
        .latitude(
            isEmpty(circuit.getLocation().getLatitude())
                ? 0
                : Float.valueOf(circuit.getLocation().getLatitude()))
        .locality(circuit.getLocation().getLocality())
        .longitude(
            isEmpty(circuit.getLocation().getLongitude())
                ? 0
                : Float.valueOf(circuit.getLocation().getLongitude()))
        .url(circuit.getUrl())
        .build();
  }

  default CircuitResponse fromEntityToResponse(Circuit circuit) {
    Location location =
        Location.builder()
            .country(circuit.getCountry())
            .latitude(String.valueOf(circuit.getLatitude()))
            .locality(circuit.getLocality())
            .longitude(String.valueOf(circuit.getLongitude()))
            .build();

    return CircuitResponse.builder()
        .circuitId(circuit.getCircuitId())
        .circuitName(circuit.getCircuitName())
        .location(location)
        .url(circuit.getUrl())
        .build();
  }
}

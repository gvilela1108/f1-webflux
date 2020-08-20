package com.webflux.f1.webfluxf1api.constructor;

import com.webflux.f1.webfluxf1api.client.feign.response.ConstructorResponse;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ConstructorMapper {

  default Constructor fromClientToEntity(ConstructorResponse constructorResponse) {
    return Constructor.builder()
        .constructorId(constructorResponse.getConstructorId())
        .name(constructorResponse.getName())
        .nationality(constructorResponse.getNationality())
        .url(constructorResponse.getUrl())
        .build();
  }

  default ConstructorResponse fromEntityToResponse(Constructor constructor) {
    return ConstructorResponse.builder()
        .constructorId(constructor.getConstructorId())
        .name(constructor.getName())
        .nationality(constructor.getNationality())
        .url(constructor.getUrl())
        .build();
  }
}

package com.webflux.f1.webfluxf1api.driver;

import com.webflux.f1.webfluxf1api.client.feign.response.DriverResponse;
import org.mapstruct.Mapper;

import java.time.LocalDate;

import static org.mapstruct.ReportingPolicy.IGNORE;
import static org.springframework.util.ObjectUtils.isEmpty;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface DriverMapper {

  default Driver fromClientToEntity(DriverResponse driverResponse) {
    return Driver.builder()
        .driverId(driverResponse.getDriverId())
        .code(driverResponse.getCode())
        .dateOfBirth(LocalDate.parse(driverResponse.getDateOfBirth()))
        .familyName(driverResponse.getFamilyName())
        .givenName(driverResponse.getGivenName())
        .nationality(driverResponse.getNationality())
        .permanentNumber(
            isEmpty(driverResponse.getPermanentNumber())
                ? 0
                : Integer.valueOf(driverResponse.getPermanentNumber()))
        .url(driverResponse.getUrl())
        .build();
  }

  default DriverResponse fromEntityToResponse(Driver driver) {
    return DriverResponse.builder()
        .code(driver.getCode())
        .dateOfBirth(String.valueOf(driver.getDateOfBirth()))
        .driverId(driver.getDriverId())
        .familyName(driver.getFamilyName())
        .givenName(driver.getGivenName())
        .nationality(driver.getNationality())
        .permanentNumber(String.valueOf(driver.getPermanentNumber()))
        .url(driver.getUrl())
        .build();
  }
}

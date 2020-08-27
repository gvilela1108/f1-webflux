package com.webflux.f1.webfluxf1api.driver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.webflux.f1.webfluxf1api.factory.F1Factory.driverBuilder;
import static com.webflux.f1.webfluxf1api.factory.F1Factory.driverResponseBuilder;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DriverMapperTest {

  @Autowired private DriverMapper mapper;

  @Test
  public void shouldGetDriver_whenFromClientToEntityIsCalled() {
    final var result = mapper.fromClientToEntity(driverResponseBuilder());
    assertEquals(result, driverBuilder());
  }

  @Test
  public void shouldGetDriverResponse_whenFromEntityToClientIsCalled() {
    final var result = mapper.fromEntityToResponse(driverBuilder());
    assertEquals(result, driverResponseBuilder());
  }
}

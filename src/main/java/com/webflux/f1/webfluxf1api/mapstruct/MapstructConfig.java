package com.webflux.f1.webfluxf1api.mapstruct;

import org.mapstruct.MapperConfig;
import org.springframework.context.annotation.Configuration;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.NullValueCheckStrategy.ON_IMPLICIT_CONVERSION;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Configuration
@MapperConfig(
    componentModel = "spring",
    unmappedTargetPolicy = IGNORE,
    injectionStrategy = CONSTRUCTOR,
    nullValueCheckStrategy = ON_IMPLICIT_CONVERSION)
public class MapstructConfig {}

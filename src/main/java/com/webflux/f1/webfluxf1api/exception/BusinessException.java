package com.webflux.f1.webfluxf1api.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

import static com.webflux.f1.webfluxf1api.exception.ErrorCode.GENERIC;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessException extends RuntimeException implements Supplier<BusinessException> {

  @Getter private final @NonNull ErrorCode errorCode;

  public BusinessException() {
    super();
    this.errorCode = GENERIC;
  }

  @Override
  public BusinessException get() {
    return new BusinessException();
  }

  @Override
  public String getMessage() {
    return errorCode.getDescription();
  }
}

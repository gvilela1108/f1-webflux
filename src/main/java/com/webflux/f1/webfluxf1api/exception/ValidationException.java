package com.webflux.f1.webfluxf1api.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

import static com.webflux.f1.webfluxf1api.exception.ErrorCode.GENERIC;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidationException extends RuntimeException implements Supplier<ValidationException> {

  private final @NonNull ErrorCode errorCode;

  public ValidationException() {
    super();
    this.errorCode = GENERIC;
  }

  @Override
  public ValidationException get() {
    return new ValidationException();
  }

  @Override
  public String getMessage() {
    return errorCode.getDescription();
  }
}

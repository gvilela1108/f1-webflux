package com.webflux.f1.webfluxf1api.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

import static com.webflux.f1.webfluxf1api.exception.ErrorCode.GENERIC;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InternalErrorException extends RuntimeException
    implements Supplier<InternalErrorException> {

  private final @NonNull ErrorCode errorCode;

  public InternalErrorException() {
    super();
    this.errorCode = GENERIC;
  }

  public InternalErrorException get() {
    return new InternalErrorException();
  }

  @Override
  public String getMessage() {
    return errorCode.getDescription();
  }
}

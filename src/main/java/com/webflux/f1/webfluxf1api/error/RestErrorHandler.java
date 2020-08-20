package com.webflux.f1.webfluxf1api.error;

import com.webflux.f1.webfluxf1api.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class RestErrorHandler {

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(UNPROCESSABLE_ENTITY)
  public final ErrorDetail handleBusinessException(final BusinessException exception) {
    log.info("m=handleBusinessException error={} requestDescription={}", exception.getMessage());
    return new ErrorDetail(exception.getErrorCode());
  }

  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(UNPROCESSABLE_ENTITY)
  public final ErrorDetail handleValidationException(final ValidationException exception) {
    log.debug("m=handleValidationException error={} requestDescription={}", exception.getMessage());
    return new ErrorDetail(exception.getErrorCode());
  }

  @ExceptionHandler(InternalErrorException.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public final ErrorDetail handleInternalException(final InternalErrorException exception) {
    final String stackTraceStr = collectStackTrace(exception);

    log.info(
        "m=handleInternalException error={} requestDescription={} stackTrace={}",
        exception.getMessage(),
        stackTraceStr);
    return new ErrorDetail(exception.getErrorCode());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public final ErrorDetail handleNotFoundException(final NotFoundException exception) {
    log.info(
        "m=handleNotFoundException status=error exception={} request={}", exception.getMessage());
    return new ErrorDetail(exception.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorDetail handleGenericException(final Exception exception) {
    final String stackTraceStr = collectStackTrace(exception);

    log.error(
        "m=handleGenericException name={} message={} cause={} stackTrace={}",
        exception.getClass().getName(),
        exception.getMessage(),
        exception.getCause(),
        stackTraceStr);
    return new ErrorDetail(ErrorCode.GENERIC);
  }

  private String collectStackTrace(final Exception exception) {
    return Arrays.stream(exception.getStackTrace())
        .map(StackTraceElement::toString)
        .collect(Collectors.joining("\n"));
  }
}

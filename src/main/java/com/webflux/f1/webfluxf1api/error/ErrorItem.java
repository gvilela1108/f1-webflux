package com.webflux.f1.webfluxf1api.error;

import com.webflux.f1.webfluxf1api.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorItem {

  private String code;
  private String message;

  public ErrorItem(ErrorCode errorCode) {
    this.code = errorCode.getCode().toString();
    this.message = errorCode.getDescription();
  }
}

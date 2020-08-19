package com.webflux.f1.webfluxf1api.error;

import com.webflux.f1.webfluxf1api.exception.ErrorCode;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

    @Singular
    private List<ErrorItem> errors;

    public ErrorDetail(final ErrorCode errorCode) {
        this.errors = Collections.singletonList(new ErrorItem(errorCode));
    }
}

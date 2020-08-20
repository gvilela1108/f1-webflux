package com.webflux.f1.webfluxf1api.mapstruct;

import java.util.List;

public interface ResponseMapper<D, R> {

  R fromResponse(D dto);

  List<R> fromResponse(List<D> responseList);
}

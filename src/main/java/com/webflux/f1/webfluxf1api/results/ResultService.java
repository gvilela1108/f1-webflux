package com.webflux.f1.webfluxf1api.results;

import reactor.core.publisher.Flux;

import java.util.ArrayList;

public interface ResultService {

    Flux<ArrayList<Result>> sendResultKafka() throws Exception;

    Flux<ArrayList<ResultKafka>> getResultKafka() throws Exception;
}

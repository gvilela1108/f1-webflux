package com.webflux.f1.webfluxf1api.race;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RaceService {

    Mono<RaceResponse> getRaceResult(int season, String round);

    Flux<RaceResponse> getResultsFromSeason(int season);
}

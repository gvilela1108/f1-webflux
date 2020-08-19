package com.webflux.f1.webfluxf1api.client.feign;

import com.webflux.f1.webfluxf1api.client.feign.response.RaceData;
import feign.Param;
import feign.RequestLine;

import java.util.Optional;

public interface F1FeignClient {

    @RequestLine("GET /{season}/{round}/results.json")
    Optional<RaceData> getRaceResults(
            @Param("season") final int season, @Param("round") final String round);
}

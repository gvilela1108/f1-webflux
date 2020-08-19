package com.webflux.f1.webfluxf1api.client.feign;

import com.webflux.f1.webfluxf1api.client.feign.response.RaceData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class F1ClientFallback implements F1FeignClient {

    private final Throwable cause;

    @Override
    public Optional<RaceData> getRaceResults(int season, String round) {
        log.error("Erro F1FeignClient.getRaceResults: " + cause.getMessage());
        return Optional.empty();
    }

}

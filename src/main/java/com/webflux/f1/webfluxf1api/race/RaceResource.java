package com.webflux.f1.webfluxf1api.race;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/race/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RaceResource {

    private final RaceService raceService;

    @GetMapping("/resultBySeasonAndRound")
    @ResponseStatus(HttpStatus.OK)
    public Mono<RaceResponse> getRaceResult(@RequestParam("season") int season, @RequestParam("round") String round) {
        return raceService.getRaceResult(season, round);
    }

    @GetMapping("/resultsFromSeason")
    @ResponseStatus(HttpStatus.OK)
    public Flux<RaceResponse> getResultsFromSeason(@RequestParam("season") int season) {
        return raceService.getResultsFromSeason(season);
    }
}

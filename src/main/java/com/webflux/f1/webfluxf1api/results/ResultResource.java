package com.webflux.f1.webfluxf1api.results;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResultResource {

    private final ResultService resultService;

    @GetMapping("/sendResultsKafka")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ArrayList<Result>> sendResultKafka() throws Exception {
        return resultService.sendResultKafka();
    }

    @GetMapping("/getResultsKafka")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ArrayList<ResultKafka>> getResultKafka() throws Exception {
        return resultService.getResultKafka();
    }

}

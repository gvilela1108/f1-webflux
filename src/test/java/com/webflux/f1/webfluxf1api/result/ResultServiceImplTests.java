package com.webflux.f1.webfluxf1api.result;

import com.webflux.f1.webfluxf1api.results.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;

import static com.webflux.f1.webfluxf1api.factory.F1Factory.*;
import static com.webflux.f1.webfluxf1api.factory.F1Factory.resultListKafkaBuilder;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTests {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ResultMapper resultMapper;

    @InjectMocks
    private ResultServiceImpl service;

    @Test
    public void shouldSentResultKafka_whenSendResultsKafka() throws Exception {

        when(resultRepository.findBySentKafka(false)).thenReturn(resultListKafkaBuilder());

        var resultKafka = service.sendResultKafka();
        StepVerifier.create(resultKafka.log()).expectNext(resultListKafkaBuilder()).verifyComplete();

        verify(resultRepository, times(INTEGER_ONE)).findBySentKafka(false);
    }

    @Test @Ignore
    //TODO Fix test when sent to dynamo
    public void shouldGetResultKafka_whenGetResultsKafka() throws Exception {
        var resultKafka = service.getResultKafka();
        var list = new ArrayList<ResultKafka>();
        StepVerifier.create(resultKafka.log()).expectNext(list).verifyComplete();
    }
}

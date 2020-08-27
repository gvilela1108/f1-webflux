package com.webflux.f1.webfluxf1api.results;


import com.webflux.f1.webfluxf1api.config.kafka.ResultKafkaDeserializer;
import com.webflux.f1.webfluxf1api.config.kafka.ResultKafkaSerializer;
import com.webflux.f1.webfluxf1api.exception.InternalErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

import static com.webflux.f1.webfluxf1api.config.kafka.ConsumerKafkaConfig.createConsumer;
import static com.webflux.f1.webfluxf1api.config.kafka.ConsumerKafkaConfig.runResultConsumer;
import static com.webflux.f1.webfluxf1api.config.kafka.KafkaConstants.F1_TOPIC_NAME;
import static com.webflux.f1.webfluxf1api.config.kafka.ProducerKafkaConfig.*;
import static com.webflux.f1.webfluxf1api.exception.ErrorCode.SEND_KAFKA_ERROR;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResultServiceImpl implements ResultService{

    private final ResultRepository resultRepository;

    private final ResultMapper resultMapper;
    @Override
    public Flux<ArrayList<Result>> sendResultKafka() throws Exception {
        var resultsToSend = getResults();
        runKafkaProducer(F1_TOPIC_NAME, resultsToSend);
        return Flux.just(resultsToSend);
    }

    @Override
    public Flux<ArrayList<ResultKafka>> getResultKafka() throws Exception {
        Consumer<Integer, ResultKafka> consumer = createConsumer(F1_TOPIC_NAME, ResultKafkaDeserializer.class);
        return Flux.just(runResultConsumer(consumer));
    }

    private ArrayList<Result> getResults() {
        return resultRepository.findBySentKafka(false);
    }

    private void runKafkaProducer(String topicName, ArrayList<Result> results) throws Exception {
        final Producer<Integer, ResultKafka> producer = createProducer(ResultKafkaSerializer.class);
        try {
            results.stream().forEach(
                    result -> {
                        var record = new ProducerRecord<>(topicName, result.getId(),resultMapper.resultKafka(result));
                        runProducer(producer, record);
                        result.setSentKafka(true);
                        resultRepository.save(result);
                    }
            );
        } catch (Exception e) {
            log.error("Error to sent results to Kafka m=runKafkaProducer e={}",e.getStackTrace());
            throw new InternalErrorException(SEND_KAFKA_ERROR);
        }
        finally {
            producerClose(producer);
        }
    }


}

package com.webflux.f1.webfluxf1api.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webflux.f1.webfluxf1api.exception.InternalErrorException;
import com.webflux.f1.webfluxf1api.results.Result;
import com.webflux.f1.webfluxf1api.results.ResultKafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

import static com.webflux.f1.webfluxf1api.exception.ErrorCode.KAFKA_PRODUCER_ERROR;

@Slf4j
public class ResultKafkaDeserializer implements Deserializer<ResultKafka> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    @Override
    public ResultKafka deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ResultKafka object = null;
        try {
            object = mapper.readValue(data, ResultKafka.class);
        } catch (Exception e) {
            log.error("Error in deserializing bytes m=deserialize e={}",e.getStackTrace());
        }
        return object;
    }
    @Override
    public void close() {
    }

}

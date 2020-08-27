package com.webflux.f1.webfluxf1api.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webflux.f1.webfluxf1api.results.Result;
import com.webflux.f1.webfluxf1api.results.ResultKafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class ResultKafkaSerializer implements Serializer<ResultKafka> {

  @Override
  public byte[] serialize(String topic, ResultKafka data) {
      byte[] retVal = null;
      ObjectMapper objectMapper = new ObjectMapper();
      try {
          retVal = objectMapper.writeValueAsString(data).getBytes();
      } catch (Exception e) {
          log.error("Error in serializing object e={}",e.getStackTrace());
      }
      return retVal;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {}

  @Override
  public void close() {}
}
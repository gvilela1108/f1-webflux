package com.webflux.f1.webfluxf1api.config.kafka;

import com.webflux.f1.webfluxf1api.results.Result;
import com.webflux.f1.webfluxf1api.results.ResultKafka;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import static com.webflux.f1.webfluxf1api.config.kafka.KafkaConstants.*;

public class ConsumerKafkaConfig {

  private static final Integer consumerPollMs = 1000;

  public static Consumer<Integer, ResultKafka> createConsumer(
      String topicName, Class classToDeserialize) {
    Properties props = new Properties();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classToDeserialize.getName());
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, MAX_POLL_RECORDS);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT_CONFIG);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OFFSET_RESET_EARLIER);
    Consumer<Integer, ResultKafka> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Collections.singletonList(topicName));
    return consumer;
  }

  public static ArrayList<ResultKafka> runResultConsumer(Consumer consumer) {
    var resultsKafka = new ArrayList<ResultKafka>();
    int noMessageFound = NumberUtils.INTEGER_ZERO;
    while (true) {
      ConsumerRecords<Integer, ResultKafka> consumerRecords = consumer.poll(consumerPollMs);
      if (consumerRecords.count() == NumberUtils.INTEGER_ZERO) {
        noMessageFound++;
        if (noMessageFound > MAX_NO_MESSAGE_FOUND_COUNT) break;
        else continue;
      }

      // TODO Replace to save in dynamo and make tests
      consumerRecords.forEach(
          record -> {
            resultsKafka.add(record.value());
          });

      consumer.commitAsync();
    }
    consumer.close();
    return resultsKafka;
  }
}

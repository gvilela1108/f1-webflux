package com.webflux.f1.webfluxf1api.config.kafka;

import com.webflux.f1.webfluxf1api.exception.InternalErrorException;
import com.webflux.f1.webfluxf1api.results.Result;
import com.webflux.f1.webfluxf1api.results.ResultKafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Properties;

import static com.webflux.f1.webfluxf1api.config.kafka.KafkaConstants.BOOTSTRAP_SERVERS;
import static com.webflux.f1.webfluxf1api.config.kafka.KafkaConstants.CLIENT_ID;
import static com.webflux.f1.webfluxf1api.exception.ErrorCode.KAFKA_PRODUCER_ERROR;

@Slf4j
public class ProducerKafkaConfig {

    public static Producer<Integer, ResultKafka> createProducer(Class classToSerialize) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                classToSerialize.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        return new KafkaProducer<>(props);
    }

    public static void runProducer(Producer producer, ProducerRecord record) {
        try {
            RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
            log.info("sent record(key={} value={}) meta(partition={}, offset={})", record.key(), record.value(), metadata.partition(),metadata.offset());
        } catch (Exception e) {
            log.error("Kafka producer error m=runProducer e={}",e.getStackTrace());
            throw new InternalErrorException(KAFKA_PRODUCER_ERROR);
        }

    }

    public static void producerClose(Producer producer) {
        producer.flush();
        producer.close();
    }
}

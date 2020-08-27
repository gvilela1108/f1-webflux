package com.webflux.f1.webfluxf1api.config.kafka;

public class KafkaConstants {

    public final static String F1_TOPIC_NAME = "f1_scheduling";
    public final static String BOOTSTRAP_SERVERS = "localhost:9092";
    public final static String KAFKA_BROKERS = "localhost:9092";
    public final static String CLIENT_ID="webFluxF1Api";
    public final static String GROUP_ID_CONFIG="consumerGroup";
    public final static Integer MAX_NO_MESSAGE_FOUND_COUNT=10;
    public final static String OFFSET_RESET_EARLIER="earliest";
    public final static Integer MAX_POLL_RECORDS=1;
    public static final int PARTITION_COUNT=100;
    public static final String ENABLE_AUTO_COMMIT_CONFIG = "false";


}

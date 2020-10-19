package com.bdilab.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class kafkaProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        int position_x, position_y;
        String position = "";
        for (int i = 0; i < 1000; i++) {
            position_x = (int) (Math.random() * 100);
            position_y = (int) (Math.random() * 100);
            position = "(" + position_x + ", " + position_y + ")";
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("bikeinfo", "bike_id:" + i, position);
            producer.send(record);
        }
        producer.close();
    }
}

package com.example.kafka.client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.1.11:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks","1");
        props.put("retries",3);
        props.put("batch.size",16384);
        props.put("linger.ms","5");
        props.put("buffer.memory",3355432);
        props.put("max.block.ms",3000);
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(props);
        for (int i =0 ;i<100;i++) {
            kafkaProducer.send(new ProducerRecord<>("gptest", Integer.toString(i), Integer.toString(i)));
        }
        kafkaProducer.close();
    }
}

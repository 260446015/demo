package com.example.springbootamqp.kafka;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "gptest",groupId = "gptest")
public class KafkaConsumer {
    @KafkaHandler
    public void consumer(String msg) {
        System.out.println("springbootkafkaconsumer:" + msg);
    }
}

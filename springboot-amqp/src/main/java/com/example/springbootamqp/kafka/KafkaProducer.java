package com.example.springbootamqp.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    public void send(){
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("gptest","springbootkafka"+i);
        }
    }
}

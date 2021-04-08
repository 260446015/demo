package com.example.springbootamqp.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = {"topic_springboot_01"})
public class ConsumerTopic {

    @RabbitHandler
    public void process(String msg) throws IOException {
//        channel.basicAck(deliveryTag, true);
        System.out.println(" first queue received msg : " + msg);
    }
}

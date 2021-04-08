package com.example.springbootamqp.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = {"fanout_springboot_01","fanout_springboot_02"})
public class ConsumerFanout {

    @RabbitHandler
    public void process(String msg) throws IOException {
//        channel.basicAck(deliveryTag, true);
        System.out.println(" first queue received msg : " + msg);
    }
}

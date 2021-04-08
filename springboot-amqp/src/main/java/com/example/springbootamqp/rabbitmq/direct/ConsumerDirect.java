package com.example.springbootamqp.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "direct_springboot_01")
public class ConsumerDirect {

    @RabbitHandler
    public void process(String msg) throws IOException {
//        channel.basicAck(deliveryTag, true);
        System.out.println(" first queue received msg : " + msg);
    }
}

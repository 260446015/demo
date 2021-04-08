package com.example.springbootamqp.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class ProducerDirect {

    @Resource
    AmqpTemplate amqpTemplate;
    @Resource
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void send(){
        // 发送4条消息

        amqpTemplate.convertAndSend("","direct_springboot_01","-------- a direct msg");

//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","shanghai.gupao.teacher","-------- a topic msg : shanghai.gupao.teacher");
//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","changsha.gupao.student","-------- a topic msg : changsha.gupao.student");
//
//        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","-------- a fanout msg");
        amqpTemplate.convertAndSend("springboot-direct-exchange","ydw.add","-------- a direct msg");

    }

    @PostConstruct
    public void send2(){
        // 发送4条消息

        rabbitTemplate.convertAndSend("","direct_springboot_01","-------- a rabbitTemplate direct msg");

//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","shanghai.gupao.teacher","-------- a topic msg : shanghai.gupao.teacher");
//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","changsha.gupao.student","-------- a topic msg : changsha.gupao.student");
//
//        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","-------- a fanout msg");
        rabbitTemplate.convertAndSend("springboot-direct-exchange","ydw.add","-------- a rabbitTemplate direct msg");

    }
}

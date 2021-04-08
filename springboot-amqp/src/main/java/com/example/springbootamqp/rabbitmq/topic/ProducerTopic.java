package com.example.springbootamqp.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class ProducerTopic {

    @Resource
    AmqpTemplate amqpTemplate;

    @PostConstruct
    public void send(){
        // 发送4条消息

//        amqpTemplate.convertAndSend("","","-------- a fanout msg without exchange");

//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","shanghai.gupao.teacher","-------- a topic msg : shanghai.gupao.teacher");
//        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","changsha.gupao.student","-------- a topic msg : changsha.gupao.student");
//
//        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","-------- a fanout msg");
        amqpTemplate.convertAndSend("springboot-topic-exchange","ydw.user","-------- a topic msg");

    }
}

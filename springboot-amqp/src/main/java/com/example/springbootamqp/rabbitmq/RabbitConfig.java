package com.example.springbootamqp.rabbitmq;

import com.example.springbootamqp.rabbitmq.direct.ResourceUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
@Configuration
public class RabbitConfig {


    @Bean
    public Queue queue1() {
        return new Queue("direct_springboot_01",false);
    }

    @Bean
    public Queue queue2() {
        return new Queue("fanout_springboot_01",false);
    }

    @Bean
    public Queue queue3() {
        return new Queue("fanout_springboot_02",false);
    }
    @Bean
    public Queue queue4() {
        return new Queue("topic_springboot_01",false);
    }

    @Bean
    public Queue queue5() {
        return new Queue("topic_springboot_02",false);
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("springboot-direct-exchange");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("springboot-fanout-exchange");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("springboot-topic-exchange");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("ydw.add");
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }
    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }
    @Bean
    public Binding binding4(){
        return BindingBuilder.bind(queue4()).to(topicExchange()).with("ydw.*");
    }

    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            public void returnedMessage(Message message,
                                        int replyCode,
                                        String replyText,
                                        String exchange,
                                        String routingKey) {
                System.out.println("回发的消息：");
                System.out.println("replyCode: " + replyCode);
                System.out.println("replyText: " + replyText);
                System.out.println("exchange: " + exchange);
                System.out.println("routingKey: " + routingKey);
            }
        });
        rabbitTemplate.setExchange("springboot-direct-exchange");
        rabbitTemplate.setChannelTransacted(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (!ack) {
                    System.out.println("发送消息失败：" + cause);
                    throw new RuntimeException("发送异常：" + cause);
                }
            }
        });


        return rabbitTemplate;
    }

}
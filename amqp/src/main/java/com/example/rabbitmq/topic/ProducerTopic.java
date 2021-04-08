package com.example.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerTopic {
    private static final String EXCHANGE_NAME = "topic_exchange";
    //交换机类型：direct
    private static final String EXCHANGE_TYPE = "topic";
    //路由键
    private static final String EXCHANGE_ROUTE_KEY = "user.add.submit";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://admin:admin@192.168.1.11:5672");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String msg = "my mq topic test";
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
        channel.basicPublish(EXCHANGE_NAME, EXCHANGE_ROUTE_KEY, null, msg.getBytes());
        channel.close();
        connection.close();
    }
}

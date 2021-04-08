package com.example.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerTopic1 {
    private static final String QUEUE_NAME = "direct_exchange_queue01";
    private static final String EXCHANGE_NAME = "topic_exchange";
    //binding key
    private static final String EXCHANGE_ROUTE_KEY = "user.#";


    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://admin:admin@192.168.1.11:5672");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,EXCHANGE_ROUTE_KEY);
        System.out.println("waiting for message....");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Received message : '" + msg + "' ");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}

package com.example.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerDirect2 {
    private static final String QUEUE_NAME = "direct_exchange_queue01";
    private static final String EXCHANGE_NAME = "direct_exchange";
    //binding key
//    private static final String EXCHANGE_ROUTE_KEY01 = "user.add";
    private static final String EXCHANGE_ROUTE_KEY02 = "user.delete";
//    private static final String EXCHANGE_ROUTE_KEY03 = "user.#";



    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://admin:admin@192.168.1.11:5672");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //将队列绑定到交换机上,并且指定routing_key
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, EXCHANGE_ROUTE_KEY01);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, EXCHANGE_ROUTE_KEY02);
//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, EXCHANGE_ROUTE_KEY03);

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

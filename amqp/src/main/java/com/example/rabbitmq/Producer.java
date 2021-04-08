package com.example.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Producer {
    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://admin:admin@192.168.1.11:5672");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("name", "gupao");
        headers.put("level", "top");
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().
                deliveryMode(2).contentEncoding("UTF-8").expiration("10000")
                .headers(headers).priority(5).messageId(String.valueOf(UUID.randomUUID())).build();
        String msg = "my mq test";
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicPublish("",QUEUE_NAME,properties,msg.getBytes());
        channel.close();
        connection.close();
    }
}

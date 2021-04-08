package com.example.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers","192.168.1.11:9092");
        prop.put("group.id","gptest");
        prop.put("enable.auto.commit",true);
        prop.put("auto.commit.interval.ms","1000");
        prop.put("auto.offset.reset","earliest");
        prop.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(prop);
        kafkaConsumer.subscribe(Arrays.asList("gptest"));
        final int batchSize = 200;
        List<ConsumerRecord<String,String>> buffer = new ArrayList<>();
        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d ,key =%s, value= %s, partition= %s%n" ,record.offset(),record.key(),record.value(),record.partition());
                buffer.add(record);
            }
            if(buffer.size() >= batchSize){
                kafkaConsumer.commitAsync();
                buffer.clear();
            }
        }
    }
}

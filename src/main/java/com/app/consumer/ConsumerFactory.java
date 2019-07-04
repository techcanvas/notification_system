package com.app.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.app.deserializer.UserDataDeserializer;
import com.app.entity.UserData;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

	public class ConsumerFactory {

	    public static void consume() {
	        Properties props = new Properties();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,UserDataDeserializer.class);
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
            //create consumer
	        KafkaConsumer<String, UserData> consumer = new KafkaConsumer<String, UserData>(props);
	    	     
	        //subscribe 
	      
	        consumer.subscribe(Arrays.asList("email-topic1","push-topic1","mobile-topic1"));
	        //poll data
	        while (true) {
				ConsumerRecords<String, UserData> records = consumer.poll(100);
				for ( ConsumerRecord<String, UserData> record : records) {
					System.out.println(record.value().toString());
					
				}
	        
	        }
	    }
	}

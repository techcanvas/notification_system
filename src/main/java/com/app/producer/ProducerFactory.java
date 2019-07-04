package com.app.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.app.entity.UserData;
import com.app.serializer.UserDataSerializer;

import java.util.Properties;

public class ProducerFactory {

	private volatile static KafkaProducer<String, UserData> kafkaProducer = null;

	private ProducerFactory() {

	}

	static {
		kafkaProducer = getKafkaProducer("localhost:9092");
	}

	public static KafkaProducer<String, UserData> getKafkaProducerInstance() {
		return ProducerFactory.kafkaProducer;
	}

	private static KafkaProducer<String, UserData> getKafkaProducer(String bootStrapServer) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		props.put(ProducerConfig.ACKS_CONFIG, "1");
		props.put(ProducerConfig.RETRIES_CONFIG, "3");
		props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
		addAdditionalProps(props);
		return new KafkaProducer<>(props, new StringSerializer(),
				new UserDataSerializer() );
	}

	private static void addAdditionalProps(Properties props) {
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("max.request.size", 52000000);
		props.put("max.block.ms",10000);
		props.put("request.timeout.ms",5000);
	}
}
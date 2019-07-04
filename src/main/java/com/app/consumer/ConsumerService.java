package com.app.consumer;

import org.springframework.stereotype.Service;

@Service
public class ConsumerService implements Runnable {

	@Override
	public void run() {
		ConsumerFactory ConsumerFactory = new ConsumerFactory();
		com.app.consumer.ConsumerFactory.consume();
	}
	
}
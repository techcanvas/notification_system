package com.app.service;

import org.apache.commons.lang.time.DateUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.entity.UserData;
import com.app.producer.ProducerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
@Service


public class KafkaPublishService {

	@Autowired
	private DataCounterService dataCounterService;
	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	private	PacksDefaultLimitService packsDefaultLimitService;
	private String response="data is processing";
	private HttpStatus  httpStatus=HttpStatus.PROCESSING;
	//publish data to kafka topic 

	private boolean publishDataToTopics(String topic, String key, UserData body) {
		AtomicBoolean atomicBoolean = new AtomicBoolean(true);
		ProducerRecord < String, UserData > data = new ProducerRecord < String, UserData > (topic, key,
				body);
		ProducerFactory.getKafkaProducerInstance().send(data, (metadata, exception) -> {
			if (exception != null) {
				atomicBoolean.set(false);
				System.out.println("Exception occured while publishing..." + exception);
				try {} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		return true;
	}
	//handle input from api and publish data to corresponding queue.
	public ResponseEntity<String> filterAndSendData(List < UserData > clientInput, String client) {
		Long packId = subscriptionService.getPackId(client);
		Date validity=dataCounterService.getValidity(client);
		Long default_email_counter = packsDefaultLimitService.getDefaultEmailCounter(packId);
		Long default_push_counter = packsDefaultLimitService.getDefaultPushCounter(packId);
		Long default_sms_counter = packsDefaultLimitService.getDefaultSMSCounter(packId);

		//Reset Counter if Date is not same as current Date - keeping in mind date is always previous in db from current date.

		if(!DateUtils.isSameDay(validity, new Date())){
			dataCounterService.updateCounter(client,packId, 0L, 0L, 0L,new Date()); //reset counter
			validity=new Date();
		}

		//Checking body of input.
		for (int i = 0; i < clientInput.size(); i++) {
			if(!(clientInput.get(i).getType().equals("email") ||
					clientInput.get(i).getType().equals("mobile") || clientInput.get(i).getType().equals("push"))){
				return new ResponseEntity<>("please enter correct type in request body ; [mobile,push,email]", HttpStatus.BAD_REQUEST);
			}

			Long email_counter = dataCounterService.getEmailCounter(client);
			Long sms_counter = dataCounterService.getSMSCounter(client);
			//Package 1 $49 
			if (packId == 1 && email_counter < default_email_counter) {
				if (clientInput.get(i).getType().equals("email")){
					publishDataToTopics("email-topic1", client, clientInput.get(i));
					dataCounterService.updateCounter(client,packId, email_counter+1, 0L, 0L,validity); //update counter
				}
			}else if(packId == 1 && email_counter >= default_email_counter){
				response="Daily limit reeached ,your limit will reset at 00:00:01 am";
				httpStatus=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;
			}

			//Package 2 -counter check for email and sms.
			if (packId == 2) {
				Long totalSentCounter=email_counter + sms_counter;
				if (totalSentCounter < (default_email_counter + default_sms_counter)) {
					if (clientInput.get(i).getType().equals("email") ){
						publishDataToTopics("email-topic1", client, clientInput.get(i));
						dataCounterService.updateCounter(client,packId, email_counter+1,sms_counter, 0L,validity); //update counter
					}
					if (clientInput.get(i).getType().equals("mobile")){
						publishDataToTopics("mobile-topic1", client, clientInput.get(i));
						dataCounterService.updateCounter(client,packId,email_counter , sms_counter+1, 0L,validity); //update counter
					}
				}else if(packId == 2 && totalSentCounter >= (default_email_counter + default_sms_counter)){
					response="Daily limit reeached ,your limit will reset at 00:00:01 am";
					httpStatus=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED;
				}
			}
			//Package 3 unlimited-no counter check and update.
			if (packId == 3) {

				if (clientInput.get(i).getType().equals("email"))
					publishDataToTopics("email-topic1", client, clientInput.get(i));

				if (clientInput.get(i).getType().equals("mobile"))
					publishDataToTopics("mobile-topic1", client, clientInput.get(i));

				if (clientInput.get(i).getType().equals("push"))
					publishDataToTopics("push-topic1", client, clientInput.get(i));
			} 
		}
		return new ResponseEntity<>(response,httpStatus);
	}
}
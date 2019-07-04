package com.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.DataCounter;
import com.app.repositories.DataCounterRepository;

@Service
public class DataCounterService {
	@Autowired
	DataCounterRepository dataCounterRepository;


	public void setCounter(String client,Long packId){
		if(packId!=null){
			if (packId==1) {
				DataCounter dataCounter = new DataCounter(client,packId,0L,0L,0l,new Date());
				dataCounterRepository.save(dataCounter);

			}else if(packId == 2){
				DataCounter dataCounter = new DataCounter(client,packId,0L,0L,0L,new Date());
				dataCounterRepository.save(dataCounter);
			}else{
				DataCounter dataCounter = new DataCounter(client,packId,0L,0L,0L,new Date());
				dataCounterRepository.save(dataCounter);
			}
		}
	}

	public Long getEmailCounter(String client){
		DataCounter dataCounter= dataCounterRepository.findByClient(client);
		return dataCounter.getEmail_counter();
	}
	
	public Long getPushCounter(String client){
		DataCounter dataCounter= dataCounterRepository.findByClient(client);
		return dataCounter.getPush_counter();
	}
	
	public Long getSMSCounter(String client){
		DataCounter dataCounter= dataCounterRepository.findByClient(client);
		return dataCounter.getSms_counter();
	}
	
	public Date getValidity(String client){
		DataCounter dataCounter= dataCounterRepository.findByClient(client);
		return dataCounter.getValidity();
	}

	public void updateCounter(String client,Long packid,Long emailCounter, Long smsCounter, Long pushCounter, Date validity ){
		DataCounter dataCounter = new DataCounter(client,packid,emailCounter,smsCounter,pushCounter,validity);
		dataCounterRepository.save(dataCounter);
	}

	
	
}

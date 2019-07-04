package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repositories.PacksDefaultLimitRepository;

@Service
public class PacksDefaultLimitService {
	
	@Autowired
	PacksDefaultLimitRepository packsDefaultLimitRepository;
	
	
	public Long getDefaultEmailCounter(Long packId){
		
		return packsDefaultLimitRepository.findByPackid(packId).getEmail_counter();
	}
	
	public Long getDefaultPushCounter(Long packId){
		
		return packsDefaultLimitRepository.findByPackid(packId).getPush_counter();
	}
	
	public Long getDefaultSMSCounter(Long packId){
		
		return packsDefaultLimitRepository.findByPackid(packId).getSms_counter();
	}

}

package com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.PackInfo;
import com.app.entity.Subscription;
import com.app.repositories.PackInfoRepository;
import com.app.repositories.SubscriptionRepository;

@Service
public class PackInfoService {

	@Autowired
	PackInfoRepository packInfoRepository;

	public List<PackInfo> findAll() {
		return packInfoRepository.findAll();
	}
	public long findCount(){
		return packInfoRepository.count();
	}

}

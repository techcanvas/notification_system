package com.app.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ClientAuth;
import com.app.entity.Subscription;
import com.app.repositories.ClientAuthenticationRepository;
import com.app.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	ClientAuthService clientAuthService;

	@Autowired
	PackInfoService packInfoService;
   @Autowired
   DataCounterService dataCounterService;

	public String subsribe(String client, Long packId) {
		Subscription subscription = new Subscription(client, packId, new Date());
		//checking valid packid 
		if (packId > 0 && packId <= packInfoService.findCount()) {
			subscriptionRepository.findByClient(client);
			if (subscriptionRepository.findByClient(client) == null) {
				subscriptionRepository.save(subscription);
				String authkey = "not able to subscribe";
				try {
					authkey = getmd5(client);
					clientAuthService.authSave(client, authkey);
					dataCounterService.setCounter(client, packId);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				return "Kindly use mentioned authkey for api calls " + authkey;
			}

			return "You are already Subscribed "
			+ "Kindly use mentioned authkey for api calls "
			+ clientAuthService.findByClient(client);
		}

		return "Please mentioned correct PackId use /packInfo api for more pack details";
	}

	// Util

	public String getmd5(String client) throws NoSuchAlgorithmException {
		String password = new Date().toString() + client;

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hashInBytes = md.digest(password
				.getBytes(StandardCharsets.UTF_8));

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();

	}

	public Long getPackId(String client){
		
		return subscriptionRepository.findByClient(client).getPackId();
	}
}

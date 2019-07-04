package com.app.controller;

import java.util.List;

import kafka.utils.Json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.consumer.ConsumerService;
import com.app.entity.PackInfo;
import com.app.entity.UserData;
import com.app.service.ClientAuthService;
import com.app.service.KafkaPublishService;
import com.app.service.PackInfoService;
import com.app.service.SubscriptionService;
import com.google.gson.Gson;

@RestController
@RequestMapping(path = "/app")
public class ClientDataController {
	@Autowired
	KafkaPublishService kafkaPublishService;
	@Autowired
	PackInfoService packInfoService;
	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	ClientAuthService clientAuthService;
	Gson gson = new Gson();

	@PostMapping(path = "/user/upload", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> uploadData(@RequestBody List<UserData> clientInput,
			@RequestParam("authkey") String authkey,
			@RequestParam("client") String client) {
		// from here we to call publisher service
		if (clientAuthService.findByClient(client) != null
				&& (authkey != null && authkey.equals(clientAuthService
						.findByClient(client).getAuthkey()))) {
			Thread consumer = new Thread(new ConsumerService());
			consumer.start();
			return kafkaPublishService.filterAndSendData(clientInput, client);
		}
		return new ResponseEntity<>("Wrong authkey/client id", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/packInfo", produces = "application/json")
	private List<PackInfo> getPack() {
		return packInfoService.findAll();
	}

	@PostMapping(path = "/user/subscribe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> subscriber(
			@RequestParam("packId") Long packId,
			@RequestParam("client") String client) {
		return ResponseEntity.ok(subscriptionService.subsribe(client, packId));
	}

}

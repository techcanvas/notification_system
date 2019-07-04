package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.ClientAuth;
import com.app.repositories.ClientAuthenticationRepository;

@Service
public class ClientAuthService {
	@Autowired
	ClientAuthenticationRepository clientAuthenticationRepository;

	public	void authSave(String client,String authkey){
		ClientAuth clientAuth = new ClientAuth(client, authkey);
		clientAuthenticationRepository.save(clientAuth);
	}
	public ClientAuth findByClient(String client){
	return	clientAuthenticationRepository.findByClient(client);
		
	}
}

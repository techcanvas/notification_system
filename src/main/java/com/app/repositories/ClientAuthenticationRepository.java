package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.ClientAuth;
import com.app.entity.Subscription;


public interface ClientAuthenticationRepository extends JpaRepository<ClientAuth, Integer> {
	public ClientAuth findByClient(String client);
}


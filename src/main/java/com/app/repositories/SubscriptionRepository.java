package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.PackInfo;
import com.app.entity.Subscription;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

	public Subscription findByClient(String client);
}

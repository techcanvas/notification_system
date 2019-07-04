package com.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private int id;

	private String client;

	private Long packId;

	private Date subscriptionDate;

	public Subscription(String client, Long packId, Date subscriptionDate) {
		super();
		this.client = client;
		this.packId = packId;
		this.subscriptionDate = subscriptionDate;
	}
	public Subscription(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	@Override
	public String toString() {
		return "subscriptionEntity [id=" + id + ", client=" + client
				+ ", packId=" + packId + ", subscriptionDate="
				+ subscriptionDate + "]";
	}

}
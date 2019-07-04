package com.app.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class DataCounter {


	@Id
	private String client;
	private Long packid;
	private Long email_counter;
	private Long sms_counter;
	private Long push_counter;
	private Date validity;


	public DataCounter(String client, Long packid, Long email_counter,
			Long sms_counter, Long push_counter, Date validity) {
		super();
		this.client = client;
		this.packid = packid;
		this.email_counter = email_counter;
		this.sms_counter = sms_counter;
		this.push_counter = push_counter;
		this.validity = validity;
	}




	public DataCounter(){}

	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Long getPackid() {
		return packid;
	}
	public void setPackid(Long packid) {
		this.packid = packid;
	}
	public long getEmail_counter() {
		return email_counter;
	}
	public void setEmail_counter(long email_counter) {
		this.email_counter = email_counter;
	}
	public long getSms_counter() {
		return sms_counter;
	}
	public void setSms_counter(long sms_counter) {
		this.sms_counter = sms_counter;
	}
	public long getPush_counter() {
		return push_counter;
	}
	public void setPush_counter(long push_counter) {
		this.push_counter = push_counter;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	@Override
	public String toString() {
		return "DataCounter [client=" + client + ", packid=" + packid
				+ ", email_counter=" + email_counter + ", sms_counter="
				+ sms_counter + ", push_counter=" + push_counter
				+ ", validity=" + validity + "]";
	}


}

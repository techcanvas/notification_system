package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PacksDefaultLimit {

	@Id
	private Long packid;
	private Long email_counter;
	private Long sms_counter;
	private Long push_counter;
	
	public PacksDefaultLimit(Long packid, Long email_counter, Long sms_counter,
			Long push_counter) {
		super();
		this.packid = packid;
		this.email_counter = email_counter;
		this.sms_counter = sms_counter;
		this.push_counter = push_counter;
	}
	public PacksDefaultLimit() {
		super();
	}
	public Long getPackid() {
		return packid;
	}
	public void setPackid(Long packid) {
		this.packid = packid;
	}
	public Long getEmail_counter() {
		return email_counter;
	}
	public void setEmail_counter(Long email_counter) {
		this.email_counter = email_counter;
	}
	public Long getSms_counter() {
		return sms_counter;
	}
	public void setSms_counter(Long sms_counter) {
		this.sms_counter = sms_counter;
	}
	public Long getPush_counter() {
		return push_counter;
	}
	public void setPush_counter(Long push_counter) {
		this.push_counter = push_counter;
	}
	@Override
	public String toString() {
		return "PacksDefaultLimit [packid=" + packid + ", email_counter="
				+ email_counter + ", sms_counter=" + sms_counter
				+ ", push_counter=" + push_counter + "]";
	}


}

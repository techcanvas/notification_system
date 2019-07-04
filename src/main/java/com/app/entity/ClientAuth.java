package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientAuth {


	@Id
	private String client;

	private String authkey;


	public ClientAuth(String client,String authKey)
	{	super();
	this.client=client;
	this.authkey=authKey;
	}

	public ClientAuth()
	{

	}



	public String getClient() {
		return client;
	}





	public void setClient(String client) {
		this.client = client;
	}





	public String getAuthkey() {
		return authkey;
	}





	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}





	@Override
	public String toString() {
		return "Client_Auth [client=" + client + ", authkey="
				+ authkey + "]";
	}


}

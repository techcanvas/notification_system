package com.app.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.app.entity.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserDataDeserializer implements Deserializer<UserData> {

	  @Override 
	  public void close() {

	  }
	 

	  @Override
	  public UserData deserialize(String arg0, byte[] arg1) {
	    ObjectMapper mapper = new ObjectMapper();
	    UserData user = null;
	    try {
	      user = mapper.readValue(arg1, UserData.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return user;
	  }


	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	}
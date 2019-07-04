package com.app.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.app.entity.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserDataSerializer implements Serializer<UserData> {

	@Override public void close() {

	}

	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated method stub
	}



	@Override
	public byte[] serialize(String arg0, UserData arg1) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(arg1).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
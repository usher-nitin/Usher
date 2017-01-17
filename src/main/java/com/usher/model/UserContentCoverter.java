package com.usher.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserContentCoverter {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	@NotNull
	public static String convertToDatabaseColumn(User userObj) {
     try {
            return objectMapper.writeValueAsString(userObj);
        } catch (Exception ex) {
            return null;
        }
	}

	@NotNull
	public static User convertToEntityAttribute(String userJSONStr) {
        try {
            return objectMapper.readValue(userJSONStr, User.class);
        } catch (Exception ex) {
            return null;
        }
	}

}

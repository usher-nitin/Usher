package com.usher.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.usher.model.User;
import com.usher.model.UserContentCoverter;

public final class UserUtils {
	public static final Logger logger = LogManager.getLogger(UserUtils.class);

	public static long getMaxUserId(List<User> users) {
		long maxId = 0;
		long userId = 0;
		for (User user : users) {
			userId = user.getId();
			if (maxId < userId) {
				maxId = userId;
			}
		}
		return maxId;
	}

	public static List<User> getUsers(Properties prop) {
		List<User> users = new ArrayList<User>();
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			logger.info("Key : " + key + ", Value : " + value);

			users.add((User) UserContentCoverter.convertToEntityAttribute(value));

		}
		return users;
	}

}

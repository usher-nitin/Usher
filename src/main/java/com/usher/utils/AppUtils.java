package com.usher.utils;

import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AppUtils {
	public static final Logger logger = LogManager.getLogger(AppUtils.class);

	/*
	 * This method will generate an alpha-numeric string of length 9 characters.
	 */
	public static String generateOTP() {
		int count = Constants.PASSWORD_LENGTH;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Constants.ALPHA_NUMERIC_STRING.length());
			builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/*
	 * This method will generate an numeric key of length 5 characters.
	 */
	public static String generateLocationKey() {
		int count = Constants.LOCATION_KEY_LENGTH;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Constants.NUMERIC_STRING.length());
			builder.append(Constants.NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/*
	 * This method run queries to fetch answer for user questions.
	 */
	public static String answer(String query) {

		query = query.replaceAll("[^a-zA-Z0-9\\s+]", " ").toLowerCase();

		String questionType = "";
		String poiKey = "";

		String filter = FileUtils.getValue("meta_keywords", "filter");

		String[] tokens = query.split(" ");
		Properties prop = FileUtils.readFile("meta_keywords");
		Enumeration<?> e = prop.propertyNames();

		for (String token : tokens) {
			logger.info("TOKEN - " + token);
			token = token.trim() + ",";
			if (StringUtils.isNotBlank(token) && !filter.contains(token)) {
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = prop.getProperty(key);
					if (StringUtils.isBlank(questionType) && key.contains("question.") && value.contains(token)) {
						questionType = key.substring(key.indexOf(".") + 1);
						logger.info("Found Question Type :: " + questionType);
					} else if (StringUtils.isBlank(poiKey) && key.contains("meta.") && value.contains(token)) {
						poiKey = key.substring(key.indexOf(".") + 1);
						logger.info("Found Point of Interest Key :: " + poiKey);
					}
				}
				if (StringUtils.isBlank(questionType) || StringUtils.isBlank(poiKey)) {
					e = prop.propertyNames();
				} else {
					return FileUtils.getValue("answer", questionType + "." + poiKey);
				}
			} else {
				logger.info("Filtered Token ******** " + token);
			}
		}

		if (StringUtils.isBlank(questionType) || StringUtils.isBlank(poiKey)) {
			logger.info(
					"Sorry for inconvinience, we didn't find an answer for your query.\n Logging this to Audit file.");
			FileUtils.appendToFile("audit", query);
		}

		return "";
	}

	public static void main(String[] args) {
		String query = "find some food1 for me";
		System.out.println(answer(query));
	}
}

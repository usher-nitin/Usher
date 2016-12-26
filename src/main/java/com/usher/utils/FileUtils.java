package com.usher.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileUtils {

	public static final Logger logger = LogManager.getLogger(FileUtils.class);
	public static Properties readFile(String filename) {

		Properties prop = new Properties();
		InputStream input = null;
		String loadType = "dailyLoad";

		logger.info("Load type will be: " + loadType);

		input = FileUtils.class.getClassLoader().getResourceAsStream(filename);

		if (input == null) {
			logger.error("Sorry, unable to find " + filename);
			return null;
		}

		try {
			prop.load(input);
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return prop;
	}

}

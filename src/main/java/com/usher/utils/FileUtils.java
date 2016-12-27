package com.usher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileUtils {
	public static final Logger logger = LogManager.getLogger(FileUtils.class);

	public static Properties readFile(String filename) {
		return readFile(filename, false);
	}

	public static Properties readFile(String filename, boolean printAll) {

		Properties prop = new Properties();
		InputStream input = null;
		filename = Constants.BASE_FILE_PATH + filename;
		logger.info("Reading file :: " + filename);

		try {
			input = new FileInputStream(filename);
			prop.load(input);
			if (printAll) {
				Enumeration<?> e = prop.propertyNames();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = prop.getProperty(key);
					logger.info("Key : " + key + ", Value : " + value);
				}
			}

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

	public static void appendToFile(String filename, HashMap<String, String> hmap) {

		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		filename = Constants.BASE_FILE_PATH + filename;
		logger.info("Writing to file :: " + filename);

		try {
			Properties prop = new Properties();

			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileIn = new FileInputStream(file);

			prop.load(fileIn);
			Iterator<Entry<String, String>> it = hmap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				prop.setProperty((String) pair.getKey(), (String) pair.getValue());
				it.remove(); // avoids a ConcurrentModificationException
			}

			fileOut = new FileOutputStream(file);
			prop.store(fileOut, filename);

		} catch (Exception ex) {
			logger.error(ex);
		} finally {

			try {
				fileOut.close();
			} catch (IOException ex) {
				logger.error(ex);
			}
		}
	}

}

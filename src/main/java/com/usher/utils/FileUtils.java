package com.usher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FileUtils {
	public static final Logger logger = LogManager.getLogger(FileUtils.class);

	public static Properties loadAppConfig() {
		Properties prop = new Properties();
		try {
			prop.load(FileUtils.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static Properties readFile(String filename) {

		Properties prop = new Properties();
		InputStream input = null;
		filename = getFileName(filename);
		
		logger.info("Reading file :: " + filename);

		try {
			input = new FileInputStream(filename);
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

	public static void printAll(Properties prop) {
		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			logger.info("Key : " + key + ", Value : " + value);
		}
	}

	public static String getFileName(String filename) {
		Properties appProp = loadAppConfig();
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("mac") >= 0) {
			OS = ".mac";
		} else {
			OS = "";
		}
		filename = appProp.getProperty("data.folder.path"+OS) + filename + Constants.PROPERTIES_FILE_EXTENSION;
		return filename;
	}

	public static void appendToFile(String filename, HashMap<String, String> hmap) {

		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		filename = getFileName(filename);
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
				Entry<String, String> pair = it.next();
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
	
	public static void deleteFromFile(String filename, String key) {

		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		filename = getFileName(filename);
		logger.info("Writing to file :: " + filename);

		try {
			Properties prop = new Properties();

			File file = new File(filename);
			if (!file.exists()) {
				return;
			}
			fileIn = new FileInputStream(file);

			prop.load(fileIn);
			prop.remove(key);

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
	
	public static void updateInFile(String filename, HashMap<String, String> hmap) {

		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		filename = getFileName(filename);
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
				Entry<String, String> pair = it.next();
				prop.replace((String) pair.getKey(), (String) pair.getValue());
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

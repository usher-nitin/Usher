package com.usher.utils;

public final class AppUtils {

	public static String generateOTP() {
		int count = Constants.PASSWORD_LENGTH;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Constants.ALPHA_NUMERIC_STRING.length());
			builder.append(Constants.ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static String generateLocationKey() {
		int count = Constants.LOCATION_KEY_LENGTH;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * Constants.NUMERIC_STRING.length());
			builder.append(Constants.NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

}

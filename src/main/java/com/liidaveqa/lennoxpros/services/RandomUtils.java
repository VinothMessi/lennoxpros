package com.liidaveqa.lennoxpros.services;

import java.io.File;
import java.security.SecureRandom;
import java.util.Date;

@Logger
public class RandomUtils {
	private static SecureRandom random = new SecureRandom();

	public static String generateRandomString(int length) {
		String text = "AaBbCc123415@#$%";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(text.charAt(random.nextInt(text.length())));
		return sb.toString();
	}

	public static String generateRandomNumericString(int length) {
		String textnumber = "0123456789";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(textnumber.charAt(random.nextInt(textnumber.length())));
		return sb.toString();// build
	}
	
	public static void createFolder(String path) {
		// File is a class inside java.io package
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();// mkdir is used to create folder
		}
	}

	public static String timeStamp() {
		Date now = new Date();
		return now.toString().replace(":", "-");
	}
}
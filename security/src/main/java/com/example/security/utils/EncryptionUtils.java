package com.example.security.utils;

import java.security.MessageDigest;

public class EncryptionUtils {
	/*
	 * 유틸리티 클래스 어느 한프로젝트에서만 사용되는 기능이 아닌 여러 프젝에서 공용으로 사용 될 수 있는 범용 기능을 구현한 클래스 암호화 검사
	 * 토큰 등 기능을 해당 클래스에 모아서 구현하는 것이 바람직함
	 */

	public static String encrypt(String s, String messageDigest) {
		try {
			MessageDigest md = MessageDigest.getInstance(messageDigest);
			byte[] passBytes = s.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++)
				sb.append(Integer.toHexString(0xff & digested[i]));
			return sb.toString();

		} catch (Exception e) {
			return s;
		}
	}

	public static String encryptSHA256(String s) {
		return encrypt(s, "SHA-256");
	}

	public static String encryptMD5(String s) {
		return encrypt(s, "MD5");
	}
}

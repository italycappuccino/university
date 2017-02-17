package com.stone.university.imooc.security;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES {

	// private static String input = "peter liu";
	static String input = "18616705059-1010";

	static String password = "4MCmBDDRoMm9qHd5bKJ9pA==";// CZhHqg4IniH9AqBR//4MCmBDDRoMm9qHd5bKJ9pA==

	public static void main(String[] args) {

		/**
		 * SSH Client, secureCRT
		 * 
		 */

		jdkAES();

		// bcAES();

	}

	public static void jdkAES() {

		try {
			// 生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();

			// key转换
			Key key = new SecretKeySpec(keyBytes, "AES");

			// 加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(input.getBytes());
			System.out.println("jdkAES encrypt:"
					+ org.apache.commons.codec.binary.Base64
							.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result_decrypt = cipher.doFinal(result);
			System.out.println("jdkAES decrypt:" + new String(result_decrypt));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void bcAES() {

		try {
			Security.addProvider(new BouncyCastleProvider());

			// 生成key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
			keyGenerator.getProvider();
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();

			// key转换
			Key key = new SecretKeySpec(keyBytes, "AES");

			// 加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(input.getBytes());
			System.out.println("bcAES encrypt:"
					+ org.apache.commons.codec.binary.Base64
							.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result_decrypt = cipher.doFinal(result);
			System.out.println("bcAES decrypt:" + new String(result_decrypt));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

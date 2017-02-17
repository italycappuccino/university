package com.stone.university.imooc.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 数字签名算法RSA<br>
 * 数据加密&数字签名<br>
 * 公钥加密，私钥解密<br>
 * 私钥加密，公钥解密<br>
 * 
 * 
 * 基于因子分解
 * 
 * 
 * @author Peter
 *
 */
public class RSA {

	private static String input = "peter liu";

	public static void main(String[] args) {
		jdkRSA();

		jdkRSA_Diff();
	}

	private static void jdkRSA() {
		try {

			// 初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

			// 执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("MD5withRSA");
			signature.initSign(privateKey);
			signature.update(input.getBytes());
			byte[] result = signature.sign();

			System.out.println("jdkRSA sign=" + Hex.encodeHexString(result));

			// 验证签名
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(input.getBytes());
			boolean boo = signature.verify(result);
			System.out.println("jdkRSA verify=" + boo);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void jdkRSA_Diff() {
		try {

			// 初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

			System.out.println("rsaPublicKey="
					+ Base64.encodeBase64String(rsaPublicKey.getEncoded()));
			System.out.println("rsaPrivateKey="
					+ Base64.encodeBase64String(rsaPrivateKey.getEncoded()));

			// 私钥加密，公钥解密-------------加密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			byte[] result = cipher.doFinal(input.getBytes());
			System.out.println("私钥加密，公钥解密-------------加密="
					+ Base64.encodeBase64String(result));

			// 私钥加密，公钥解密-------------解密
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			System.out.println("私钥加密，公钥解密-------------解密="
					+ new String(cipher.doFinal(result)));

			// ================================================================================================
			// ================================================================================================
			// ================================================================================================
			// ================================================================================================

			// 公钥加密，私钥解密-------------加密
			x509EncodedKeySpec = new X509EncodedKeySpec(
					rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(input.getBytes());
			System.out.println("公钥加密，私钥解密-------------加密="
					+ Base64.encodeBase64String(result));

			// 公钥加密，私钥解密-------------解密
			pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					rsaPrivateKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			System.out.println("公钥加密，私钥解密-------------解密="
					+ new String(cipher.doFinal(result)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

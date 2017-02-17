package com.stone.university.imooc.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * 数字签名DSA
 * 
 * @author Peter
 *
 */
public class DSA {

	private static String input = "peter liu";

	public static void main(String[] args) {

		jdkDSA();
	}

	private static void jdkDSA() {
		try {
			// 初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("DSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
			DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

			// 执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					dsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("DSA");
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withDSA");
			signature.initSign(privateKey);
			signature.update(input.getBytes());
			byte[] result = signature.sign();
			System.out.println("jdkDSA sign=" + Hex.encodeHexString(result));

			// 验证签名
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					dsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("DSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withDSA");
			signature.initVerify(publicKey);
			signature.update(input.getBytes());
			boolean boo = signature.verify(result);
			System.out.println("jdkDSA verify=" + boo);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

package com.stone.university.imooc.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Hex;

public class ECDSA {

	private static String input = "peter liu";

	public static void main(String[] args) {

		jdkECDSA();
	}

	private static void jdkECDSA() {
		try {
			// 初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("EC");
			keyPairGenerator.initialize(256);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();

			ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
			ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();

			// 执行签名
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
					ecPrivateKey.getEncoded());// 私钥加密
			KeyFactory keyFactory = KeyFactory.getInstance("EC");
			PrivateKey privateKey = keyFactory
					.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("SHA1withECDSA");
			signature.initSign(privateKey);
			signature.update(input.getBytes());
			byte[] result = signature.sign();

			System.out.println("jdkECDSA sign=" + Hex.encodeHexString(result));

			// 验证签名
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					ecPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("EC");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("SHA1withECDSA");
			signature.initVerify(publicKey);
			signature.update(input.getBytes());
			boolean boo = signature.verify(result);
			System.out.println("jdkECDSA verify=" + boo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

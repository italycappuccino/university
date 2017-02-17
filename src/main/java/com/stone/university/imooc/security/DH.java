package com.stone.university.imooc.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DH (Diffie-Hellman)密钥交换算法
 * 
 * @author Peter
 *
 */
public class DH {

	private static String input = "peter liu";

	public static void main(String[] args) {
		jdkDH();
	}

	private static void jdkDH() {

		try {
			// 初始化发送方密钥
			KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator
					.getInstance("DH");
			senderKeyPairGenerator.initialize(512);
			KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();

			byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();// 发送方公钥,发送给接收方

			// 初始化接收方密钥
			KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					senderPublicKeyEnc);
			PublicKey receiverPublicKey = receiverKeyFactory
					.generatePublic(x509EncodedKeySpec);

			DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey)
					.getParams();
			KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator
					.getInstance("DH");
			receiverKeyPairGenerator.initialize(dhParameterSpec);
			KeyPair receiverKeyPair = receiverKeyPairGenerator
					.generateKeyPair();

			PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
			byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic()
					.getEncoded();

			// 密钥构建
			KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
			receiverKeyAgreement.init(receiverPrivateKey);
			receiverKeyAgreement.doPhase(receiverPublicKey, true);
			SecretKey receiverDesKey = receiverKeyAgreement
					.generateSecret("DES");
			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
			PublicKey senderPublicKey = senderKeyFactory
					.generatePublic(x509EncodedKeySpec);

			KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
			senderKeyAgreement.init(senderKeyPair.getPrivate());
			senderKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");

			if (Objects.equals(receiverDesKey, senderDesKey)) {
				System.out.println("双方密钥相同");
			} else {
				System.out.println("不相同。。。");
			}

			// 加密
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
			byte[] result = cipher.doFinal(input.getBytes());
			System.out.println("jdkDH encrypt="
					+ Base64.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
			String result2 = new String(cipher.doFinal(result));
			System.out.println("jdkDH decrypt=" + result2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package com.stone.university.imooc.security;

import org.apache.commons.codec.binary.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Peter on 2/17/17.
 */
public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println("rsaPublicKey="
                + org.apache.commons.codec.binary.Base64.encodeBase64String(rsaPublicKey.getEncoded()));
        System.out.println("rsaPrivateKey="
                + org.apache.commons.codec.binary.Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
    }
}

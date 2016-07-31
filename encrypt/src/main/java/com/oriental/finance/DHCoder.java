package com.oriental.finance;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuwenchang on 31/7/16
 * 双方在没有交换本地密钥，同样可以完成数据的对称加密算法，this is the DH algorithm key
 */
public abstract class DHCoder {

    public static final String KEY_ALGORITHM = "DH";

    public static final String SECRET_ALGORITHM = "DES";

    public static final int KEY_SIZE = 512;

    public static final String PUBLIC_KEY = "DHPublicKey";

    public static final String PRIVATE_KEY = "DHPrivateKey";

    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DHPublicKey dhPublicKey = (DHPublicKey) keyPair.getPublic();
        DHPrivateKey dhPrivateKey = (DHPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, dhPublicKey);
        keyMap.put(PRIVATE_KEY, dhPrivateKey);
        return keyMap;
    }

    public static Map<String, Object> initKey(byte[] key) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) publicKey).getParams();

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());

        keyPairGenerator.initialize(dhParameterSpec);

        KeyPair keyPair = keyPairGenerator.genKeyPair();

        DHPublicKey publicKey1 = (DHPublicKey) keyPair.getPublic();

        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey1);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
        SecretKey secretKey=new SecretKeySpec(key,SECRET_ALGORITHM);

        Cipher cipher= Cipher.getInstance(secretKey.getAlgorithm());

        cipher.init(Cipher.ENCRYPT_MODE,secretKey);

        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        SecretKey secretKey=new SecretKeySpec(key,SECRET_ALGORITHM);
        Cipher cipher=Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(data);
    }


    public static byte[] getSecretKey(byte[] publicKey,byte[] privateKey) throws Exception{
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);

        X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(publicKey);

        PublicKey pubKey=keyFactory.generatePublic(x509EncodedKeySpec);

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(privateKey);

        PrivateKey priKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        KeyAgreement keyAgreement=KeyAgreement.getInstance(keyFactory.getAlgorithm());

        keyAgreement.init(priKey);
        keyAgreement.doPhase(pubKey,true);
        SecretKey secretKey=keyAgreement.generateSecret(SECRET_ALGORITHM);
        return secretKey.getEncoded();
    }

    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key=(Key)keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key key=(Key)keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }
}

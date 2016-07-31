package com.oriental.finance;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuwenchang on 31/7/16.
 */
public abstract class RSACoder {

    public static final String KEY_ALGORITHM="RSA";

    private static final String PUBLIC_KEY="RSAPublicKey";

    private static final String PRIVATE_KEY="RSAPrivateKey";

    private static final  int KEY_SIZE=512;

    /**
     * 私钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory= KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        return cipher.doFinal(data);

    }

    /**
     * 公钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data,byte[] key)throws Exception{
        X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(key);
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);

        PublicKey publicKey=keyFactory.generatePublic(x509EncodedKeySpec);

        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        return  cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static  byte[] encryptByPublicKey(byte[] data,byte[] key) throws Exception{
        X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(key);
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey=keyFactory.generatePublic((x509EncodedKeySpec));

        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data,byte[] key)throws Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(key);

        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);

        PrivateKey privateKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 嫁始化
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair=keyPairGenerator.generateKeyPair();

        RSAPublicKey rsaPublicKey=(RSAPublicKey)keyPair.getPublic();

        RSAPrivateKey rsaPrivateKey=(RSAPrivateKey) keyPair.getPrivate();
        Map<String,Object> keyMap=new HashMap<String,Object>();

        keyMap.put(PUBLIC_KEY,rsaPublicKey);
        keyMap.put(PRIVATE_KEY,rsaPrivateKey);
        return keyMap;
    }


    /**
     * 取得私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key=(Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
        Key key=(Key)keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }
}

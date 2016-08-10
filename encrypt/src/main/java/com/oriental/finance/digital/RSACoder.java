package com.oriental.finance.digital;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuwenchang on 7/8/16.
 */
public class RSACoder {
    public static final String KEY_ALGORITHM="RSA";

    public static final String SIGNATURE_ALGORITHM="MD5withRSA";

    private static final String PUBLIC_KEY="RSAPublicKey";

    private static final String PRIVATE_KEY="RSAPrivatekey";

    private static int KEY_SIZE=1024;


    public static byte[] sign(byte[] data ,byte[] privateKey)throws  Exception{
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec=new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey=keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return signature.sign();
    }


    public static boolean verify(byte[] data,byte[] publicKey,byte[] sign) throws Exception{
        X509EncodedKeySpec x509EncodedKeySpec=new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory= KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey1=keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature=Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey1);
        signature.update(data);
        return signature.verify(sign);
    }

    public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
        Key key=(Key)keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    public static byte[] getPublicKey(Map<String,Object>keyMap) throws Exception{
         Key key=(Key)keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    public static Map<String,Object> initKey() throws Exception{
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGenerator.initialize(KEY_SIZE);

        KeyPair keyPair=keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey=(RSAPublicKey)keyPair.getPublic();

        RSAPrivateKey privateKey=(RSAPrivateKey)keyPair.getPrivate();

        Map<String,Object> keyMap=new HashMap<String,Object>(2);

        keyMap.put(PRIVATE_KEY,privateKey);
        keyMap.put(PUBLIC_KEY,publicKey);

        return keyMap;
    }
}

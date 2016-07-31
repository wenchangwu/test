package com.oriental.finance;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by wuwenchang on 31/7/16.
 */
public class RSACoderTest {

    private byte[] publicKey;
    private byte[] privateKey;


    @Before
    public void initKey() throws Exception{
        Map<String,Object> keyMap=RSACoder.initKey();
        publicKey=RSACoder.getPublicKey(keyMap);
        privateKey=RSACoder.getPrivateKey(keyMap);
    }

    @Test
    public void test()throws  Exception{
        String inputStr="RSA加密算法";
        byte[] data1=inputStr.getBytes();
        System.out.println("原文 是：   "+inputStr);

        byte[] encodeData1=RSACoder.encryptByPrivateKey(data1,privateKey);
        System.out.println("加密后：    "+ Base64.encodeBase64String(encodeData1));

        byte[] decodeData1=RSACoder.decryptByPublicKey(encodeData1,publicKey);
        System.out.println("解密后：    "+new String(decodeData1));
    }
}


package com.oriental.finance.digital;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by wuwenchang on 7/8/16.
 */
public class RSACoderTest {

    private byte[] publicKey;

    private byte[] privateKey;

    @Before
    public void initKey() throws Exception{
        Map<String,Object> keyMap=RSACoder.initKey();

        publicKey= RSACoder.getPublicKey(keyMap);
        privateKey=RSACoder.getPrivateKey(keyMap);

        System.out.println("the public key is :"+ Base64.encodeBase64String(publicKey));
        System.out.println("the private key is :"+Base64.encodeBase64String(privateKey));
    }


    @Test
    public void testSign() throws Exception{
        String inputStr="RSA digital signature";
        byte[] data=inputStr.getBytes();
        byte[] sign=RSACoder.sign(data,privateKey);
        System.out.println("this signature is :"+Hex.encodeHexString(sign));

        boolean status=RSACoder.verify(data,publicKey,sign);

        System.out.println("this status is :"+status);

        Assert.assertTrue(status);
    }
}

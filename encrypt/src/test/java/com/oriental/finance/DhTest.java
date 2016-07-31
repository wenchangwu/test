package com.oriental.finance;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by wuwenchang on 31/7/16.
 */
public class DhTest {

    private byte[] publicKey1;

    private byte[] privateKey1;

    private byte[] key1;

    private byte[] publicKey2;

    private byte[] privatekey2;

    private byte[] key2;

    @Before
    public void initKey() throws Exception{
        //generate key 1
        Map<String,Object> keyMap1=Dh.initKey();

        publicKey1=Dh.getPublicKey(keyMap1);

        privateKey1=Dh.getPrivateKey(keyMap1);

        System.out.println("1方公钥：   "+ Base64.encodeBase64String(publicKey1));

        System.out.println("1方密钥：   "+Base64.encodeBase64String(privateKey1));

        //generate key 2 from key1 public key

        Map<String,Object> keyMap2=Dh.initKey(publicKey1);

        publicKey2=Dh.getPublicKey(keyMap2);

        privatekey2=Dh.getPrivateKey(keyMap2);

        System.out.println("2方公钥：   "+Base64.encodeBase64String(publicKey2));

        System.out.println("2方密钥：   "+Base64.encodeBase64String(privatekey2));

        key1=Dh.getSecretKey(publicKey2,privateKey1);

        System.out.println("1方密钥：   "+Base64.encodeBase64String(key1));

        key2=Dh.getSecretKey(publicKey1,privatekey2);

        System.out.println("2方密钥：   "+Base64.encodeBase64String(key2));

        Assert.assertArrayEquals(key1,key2);
    }

    @Test
    public void test() throws Exception{
        System.out.println("1发送加密数据....");
        String input1="密码交换算法";
        System.out.println("原文: "+input1);
        System.out.println("使用1本地密钥进行加密....");
        byte[] code1=Dh.encrypt(input1.getBytes(),key1);
        System.out.println("DES加密后数据 ：  "+Base64.encodeBase64String(code1));

        //接收方2来解密
        byte[] decode2=Dh.decrypt(code1,key2);
        System.out.println("2解密后数据为：    "+new String(decode2));
    }
}

package com.oriental.finance;

import java.io.*;
import java.net.Socket;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimeClient {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 8080);
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
            os.write("xiao_dingo is great".getBytes());
            os.flush();
            int size = is.available();
            byte[] a = new byte[size];
            is.read(a);
            String strque = new String(a);
            System.out.println("from server: "+strque);
        } catch (Exception e) {

        }
    }
}

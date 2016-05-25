package com.oriental.finance;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimerServerHandler implements Runnable {

    private Socket socket;

    public TimerServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            os.write("of course ,xiao_dingo is great!".getBytes());
            os.flush();
            int size = is.available();
            byte[] a = new byte[size];
            is.read(a);
            String strque = new String(a);
            System.out.println("from client: "+strque);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

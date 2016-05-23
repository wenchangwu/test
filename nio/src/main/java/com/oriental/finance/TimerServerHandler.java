package com.oriental.finance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimerServerHandler implements Runnable {

    private Socket socket;

    public TimerServerHandler(Socket socket){
        this.socket=socket;
    }

    public void run() {
        BufferedReader in=null;
        PrintWriter out=null;
        try{
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(this.socket.getOutputStream());
            String currentTime=null;
            String body=null;
            while(true){
                body=in.readLine();
                if(body==null){
                    break;
                }
                System.out.println("the time server receive order:"+body);
                currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date().toString():"BAD ORDER";
                out.print(currentTime);
            }
        }catch(Exception e){

        }
    }
}

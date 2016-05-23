package com.oriental.finance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimeClient {

    public static void main(String[] args){
        Socket socket=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try{
        socket=new Socket("127.0.0.1",8080);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            System.out.println("send order server succeed");
            String resp=in.readLine();
            System.out.println("now is:"+resp);
        }catch(Exception e){

        }
    }
}

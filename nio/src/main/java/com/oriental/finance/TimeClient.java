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
            in=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readline=in.readLine();
            while(!readline.equals("quit")){
                out=new PrintWriter(socket.getOutputStream());
                out.println("QUERY TIME ORDER");
                out.flush();
                System.out.println("send order server :"+readline);
                String resp=is.readLine();
                System.out.println("server is:"+resp);
                readline=in.readLine();
            }



        }catch(Exception e){

        }
    }
}

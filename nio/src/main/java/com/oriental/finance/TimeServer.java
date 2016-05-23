package com.oriental.finance;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimeServer {

    public static void main(String[] args) throws Exception{
        ServerSocket server=null;
        try{
            server=new ServerSocket(8080);
            System.out.println("the time server is start in port 8080");
            Socket socket=null;
            while(true){
                socket=server.accept();
                new TimerServerHandler(socket).run();
            }
        }catch (Exception e){

        }finally {
            if(server!=null){
                server.close();
                server=null;
            }
        }
    }
}

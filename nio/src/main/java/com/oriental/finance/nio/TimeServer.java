package com.oriental.finance.nio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wuwenchang on 23/5/16.
 */
public class TimeServer {

    public static void main(String[] args) throws Exception{
        ServerSocket server=null;
        try{
            while(true){
                new MultipTimerServer(8080).run();
            }
        }catch (Exception e){

        }finally {
            if(server!=null){
                server.close();
            }
        }
    }
}

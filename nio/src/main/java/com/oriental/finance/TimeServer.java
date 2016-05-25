package com.oriental.finance;

import org.springframework.scheduling.commonj.TimerManagerTaskScheduler;

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
            TimeServerHandlerExecutePool singleExecutor=new TimeServerHandlerExecutePool(50,10000);
            while(true){
                socket=server.accept();
                singleExecutor.execute(new TimerServerHandler(socket));
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

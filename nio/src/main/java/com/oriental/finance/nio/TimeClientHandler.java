package com.oriental.finance.nio;

import com.oriental.finance.bio.TimeClient;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.sql.Time;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: wenchang.wu Date: 2016/5/26 ProjectName:test Version:
 */
public class TimeClientHandler implements Runnable {

    private String host;
    private int port ;
    private Selector selector;
    private SocketChannel socketChannel;
    private boolean stop;

    public TimeClientHandler(String host,int port){
        this.host="127.0.0..1";
        this.port=port;
        try{
            selector=Selector.open();
            socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);
        }catch(Exception e){

        }
    }


    public void run() {
        try{
            doConnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private void doConnect()throws Exception{
        if(socketChannel.connect(new InetSocketAddress(host,port))){

        }
    }
}



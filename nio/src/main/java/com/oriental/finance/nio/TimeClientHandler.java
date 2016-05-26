package com.oriental.finance.nio;

import com.oriental.finance.bio.TimeClient;
import com.sun.deploy.uitoolkit.SynthesizedEventListener;

import java.io.BufferedReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.sql.Time;
import java.util.Iterator;
import java.util.Set;

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
        this.host="127.0.0.1";
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
        while(!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeySet=selector.selectedKeys();
                Iterator<SelectionKey> it=selectionKeySet.iterator();
                SelectionKey selectionKey=null;
                while(it.hasNext()){
                    selectionKey=it.next();
                    it.remove();
                    try{
                        handleInput(selectionKey);
                    }catch (Exception e){
                        if(selectionKey!=null){
                            selectionKey.cancel();
                            if(selectionKey.channel()!=null){
                                selectionKey.channel().close();
                            }
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }


    private void handleInput(SelectionKey key)throws Exception{
        if(key.isValid()){
            SocketChannel sc=(SocketChannel)key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ);
                    doWrite(sc);
                }else{
                    System.exit(1);
                }
            }
        }

        if(key.isReadable()){
            ByteBuffer readBuffer=ByteBuffer.allocate(1024);
            int readBytes= socketChannel.read(readBuffer);
            if(readBytes>0){
                readBuffer.flip();
                byte[] bytes=new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body=new String(bytes,"UTF-8");
                System.out.println("Now is : "+body);
                this.stop=true;
            }else if(readBytes<0){
                key.cancel();
                socketChannel.close();
            }else{

            }
        }
    }


    private void doConnect()throws Exception{
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else{
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }


    private void doWrite(SocketChannel sc)throws  Exception{
        byte[] req="QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer=ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            System.out.println("send order to Server succeed");
        }
    }
}



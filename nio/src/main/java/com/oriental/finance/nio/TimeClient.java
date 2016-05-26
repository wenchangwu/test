package com.oriental.finance.nio;

/**
 * Created by wuwenchang on 26/5/16.
 */
public class TimeClient {
    public static void main(String[] args)throws Exception{
        new TimeClientHandler("127.0.0.1",8080).run();

        Thread.sleep(1111111111);
    }
}

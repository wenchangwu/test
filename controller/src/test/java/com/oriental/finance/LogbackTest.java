package com.oriental.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/5/14 0014.
 */
public class LogbackTest {

    private static Logger logger= LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args){
        logger.info("this is just a demo");
    }
}

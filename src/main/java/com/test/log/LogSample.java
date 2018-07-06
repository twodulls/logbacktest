package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSample {

    private static final Logger logger = LoggerFactory.getLogger(LogSample.class);

    public static void main(String args[]){
    	String stringMsg = "Test";
    	int integerMsg = 123;
    	
        logger.trace("trace");
        logger.debug("debug");
        logger.debug("debug {} {}", stringMsg, integerMsg);
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}

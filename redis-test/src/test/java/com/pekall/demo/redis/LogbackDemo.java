package com.pekall.demo.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackDemo {

    Logger logger = LoggerFactory.getLogger(LogbackDemo.class.getName());

    @Test
    public void smoke() {
        logger.info("hahaha");
    }
}

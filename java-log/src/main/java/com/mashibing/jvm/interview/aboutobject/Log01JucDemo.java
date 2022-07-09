package com.mashibing.jvm.interview.aboutobject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log01JucDemo {
    /**
     * Application->Logger->Handler->filter->formatter
     *   Logger是应用程序的入口
     *   Appenders/Handler: 日志功能的具体实现，如控制台，文件，网络
     *   layouts: formatters, 负责日志的具体格式
     *   filter: 过滤器
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Log01JucDemo.class.getCanonicalName());
        logger.info("hello juc");

        logger.log(Level.INFO, "hahaha");

        logger.log(Level.INFO, "wuhuhu, {0}, {1}",
                new Object[]{"haha", "huhu"});

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }



}

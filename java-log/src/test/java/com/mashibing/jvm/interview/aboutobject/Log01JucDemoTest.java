package com.mashibing.jvm.interview.aboutobject;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

import static org.junit.Assert.*;

public class Log01JucDemoTest {

    @Test
    public void name() throws IOException {
        Logger logger = Logger.getLogger(Log01JucDemo.class.getCanonicalName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        SimpleFormatter formatter = new SimpleFormatter();

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        FileHandler fileHandler = new FileHandler("./jul.log");
        fileHandler.setFormatter(formatter);
        fileHandler.setLevel(Level.WARNING);
        logger.addHandler(fileHandler);

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    /**
     * logger的层级结构是通过name来实现的
     *  a.b.c为a.b.c.d的parent
     *  子logger继承父亲的配置，handler, filter, formatter
     * @throws IOException
     */
    @Test
    public void loggerHierachy() throws IOException {
        Logger logger = Logger.getLogger("com.mashibing.jvm");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        Logger childLogger = Logger.getLogger("com.mashibing.jvm.interview.aboutobject");
        childLogger.setUseParentHandlers(true);

        SimpleFormatter formatter = new SimpleFormatter();

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        FileHandler fileHandler = new FileHandler("./jul.log");
        fileHandler.setFormatter(formatter);
        fileHandler.setLevel(Level.WARNING);
        logger.addHandler(fileHandler);

        logger.info("root log is: " + logger.getParent());

        childLogger.severe("severe");
        childLogger.warning("warning");
        childLogger.info("info");
        childLogger.fine("fine");
        childLogger.finer("finer");
        childLogger.finest("finest");
    }

    @Test
    public void config() throws IOException {
        // 默认配置文件在 ${java.home}/lib/logging.properties中

        // 定制的配置文件需要手动加载
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(Log01JucDemoTest.class.getClassLoader()
                .getResourceAsStream("logging.properties"));

        Logger logger = Logger.getLogger(Log01JucDemoTest.class.getCanonicalName());
        logger.finest("controll by logging.properties");
    }
}
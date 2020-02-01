package com.java.examples;

import org.junit.Test;

public class SystemPropertiesDemo {

    @Test
    public void smoke() {
        // 在哪里执行java命令，user.dir返回的就是哪里
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
    }

}

package com.java.examples.stream;

import java.io.*;

public class BufferedOutputStreamDemo {
    public static void main(String[] args) {
        try (BufferedOutputStream fos = new BufferedOutputStream(
                     new FileOutputStream(new File("/tmp/abcd.txt")), 1024)) {
            fos.write('a');
            fos.write("你好1234\n".getBytes());
            for (int i = 0; i < 100; i++) {
                fos.write('\n'); // 比单纯write快得多！
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

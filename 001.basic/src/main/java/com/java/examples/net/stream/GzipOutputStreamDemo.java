package com.java.examples.net.stream;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GzipOutputStreamDemo {
    public static void main(String[] args) {
        try (BufferedOutputStream fos =
                     new BufferedOutputStream(new GZIPOutputStream(
                             new FileOutputStream(new File("/tmp/a.gz"))))) {
            fos.write('a');
            fos.write("你好1234\n".getBytes());
            for (int i = 0; i < 10; i++) {
                fos.write('a');
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

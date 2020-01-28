package com.java.examples.net.stream;

import java.io.*;
import java.nio.charset.Charset;

public class BufferedInputStreamDemo {
    public static void main(String[] args) {
        try(BufferedInputStream fin =
                new BufferedInputStream(
                        new FileInputStream(new File("/tmp/abcd.txt")), 1024)) {

            int available = 0;
            StringBuilder sb = new StringBuilder();
            while ((available = fin.available()) > 0) {
                int bytesRead = 0;
                
                byte[] bytes = new byte[available];
                while (true) {
                    int read = fin.read(bytes, bytesRead, available - bytesRead);
                    if (read > 0) {
                        bytesRead += read;
                    } else {
                        break;
                    }
                }
                sb.append(new String(bytes, Charset.defaultCharset()));
            }
            System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

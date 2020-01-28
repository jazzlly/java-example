package com.java.examples.net.stream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

public class GzipInputStreamDemo {
    public static void main(String[] args) {
        try(BufferedInputStream fin =
                new BufferedInputStream(new GZIPInputStream(
                        new FileInputStream(new File("/tmp/a.gz"))))) {

            final int available = 1000;
            StringBuilder sb = new StringBuilder();
            int bytesRead = 0;

            byte[] bytes = new byte[available];
            while (true) {
                int read = fin.read(bytes, bytesRead, available - bytesRead);
                if (read > 0) {
                    bytesRead += read;
                }
                // No data
                if (read == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // EOF
                if (read == -1) {
                    break;
                }
            }
            sb.append(new String(bytes, Charset.defaultCharset()));
            System.out.println(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

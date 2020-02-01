package com.java.examples.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class InputStreamDemo {
    public static void main(String[] args) {
        try(FileInputStream fin =
                    new FileInputStream(new File("/tmp/abcd.txt"))) {

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

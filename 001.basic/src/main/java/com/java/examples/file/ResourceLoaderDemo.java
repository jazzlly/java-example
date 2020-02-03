package com.java.examples.file;

import java.io.*;

public class ResourceLoaderDemo {
    public static void main(String[] args) {
        InputStream inputStream = ResourceLoaderDemo.class.getResourceAsStream("text.txt");
        try {
            InputStreamReader streamReader = new InputStreamReader(inputStream, "utf8");
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

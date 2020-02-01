package com.java.examples.stream.writerreader;

import java.io.*;
import java.nio.charset.Charset;

public class DemoWriter {
    public static void main(String[] args) {
        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("/tmp/writer.txt"),
                        Charset.forName("UTF-8")))) {
            char[] chars = "是".toCharArray();
            writer.write(chars);
            writer.write('是');
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("/tmp/writer.txt"),
                        Charset.forName("UTF-8"))
        )) {
            char[] chars = new char[32];
            int readCnt;
            while((readCnt = reader.read(chars)) != -1) {
                System.out.println(chars);
                chars = new char[32];
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("/tmp/writer-utf16"),
                        Charset.forName("UTF-16")))) {
            writer.write('是');
            writer.write('是');
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("/tmp/writer-utf16"),
                        Charset.forName("UTF-16")))) {
            char[] chars = new char[32];
            int readCnt;
            while((readCnt = reader.read(chars)) != -1) {
                System.out.println(chars);
                chars = new char[32];
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

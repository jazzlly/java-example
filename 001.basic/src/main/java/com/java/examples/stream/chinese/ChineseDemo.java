package com.java.examples.stream.chinese;

import java.io.*;
import java.nio.charset.Charset;

public class ChineseDemo {
    public static void main(String[] args) {

        // Java内置的String是 16 bits unicode 编码!
        try (DataOutputStream stream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("/tmp/chinese-unicode")))) {
            stream.writeChar('是');
//            String foo  = "是";
//            stream.writeChars(foo);
            // debug: '是' 26159 = 0x662f
            stream.flush();
            // 使用data outputstream 写入文件后，就是big endian的utf-16编码
            // $ xxd ./chinese-unicode // big endian unicode
            //00000000: 662f                                     f/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(DataInputStream stream = new DataInputStream(
                new FileInputStream("/tmp/chinese-unicode")
        )) {
            System.out.println(stream.readChar());
            // output: 是
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 一个中文utf-8字符是3个byte
        try (BufferedOutputStream fos = new BufferedOutputStream(
                     new FileOutputStream(new File("/tmp/chinese.txt")), 1024)) {
            fos.write("是".getBytes(Charset.forName("UTF-8")));
            fos.write(new byte[13]); // 补0

            fos.write("是是".getBytes(Charset.forName("UTF-8")));
            fos.write(new byte[10]); // 补0
            fos.write(new byte[16]); // 补0
            // $ xxd ./chinese.txt
            // 00000000: e698 af00 0000 0000 0000 0000 0000 0000  ................
            // 00000010: e698 afe6 98af 0000 0000 0000 0000 0000  ................
            // 00000020: 0000 0000 0000 0000 0000 0000 0000 0000  ................
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream("/tmp/chinese.txt")
        )) {
            byte[] bytes = new byte[3];
            stream.read(bytes);
            System.out.println(new String(bytes, Charset.forName("UTF-8")));
            // output 是
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("/tmp/chinese1.txt"))) {
            String foo = "是";
            writer.write(foo);
            // $ xxd ./chinese1.txt  // 自动转化为utf-8
            // 00000000: e698 af
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader("/tmp/chinese1.txt")
        )) {
            char[] chars = new char[1];
            reader.read(chars);
            System.out.println(chars);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

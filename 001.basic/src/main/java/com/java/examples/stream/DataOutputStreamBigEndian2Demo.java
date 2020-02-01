package com.java.examples.stream;

import java.io.*;

public class DataOutputStreamBigEndian2Demo {
    public static void main(String[] args) {
        try (DataOutputStream dos =
                     new DataOutputStream(new BufferedOutputStream(
                     new FileOutputStream(new File("/tmp/chinese.txt")), 1024))) {
            // data output stream是 big endian的
            dos.writeBytes("是");
            dos.write(new byte[15]); // utf-16 big endian low bits
            // 00000000: 2f00 0000 0000 0000 0000 0000 0000 0000  /...............

            dos.writeChars("是");
            dos.write(new byte[14]); // utf-16 big endian bits
            // 00000010: 662f 0000 0000 0000 0000 0000 0000 0000  f/..............

            dos.writeUTF("是"); // 5 bytes? utf-8的变体形式
            dos.write(new byte[11]);
            dos.writeUTF("是是是");
            // 00000020: 0003 e698 af00 0000 0000 0000 0000 0000  ................
            // 00000030: 0009 e698 afe6 98af e698 af              ...........

            dos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

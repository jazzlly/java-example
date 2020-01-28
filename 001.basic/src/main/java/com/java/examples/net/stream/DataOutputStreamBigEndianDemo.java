package com.java.examples.net.stream;

import java.io.*;

public class DataOutputStreamBigEndianDemo {
    public static void main(String[] args) {
        try (DataOutputStream dos =
                     new DataOutputStream(new BufferedOutputStream(
                     new FileOutputStream(new File("/tmp/short.txt")), 1024))) {
            // data output stream是 big endian的

            dos.writeBoolean(true);  // 1 byte 01
            dos.writeBoolean(false); // 1 byte 00
            dos.write('\r');
            dos.writeBoolean(true);
            dos.writeChar('\r');
            dos.writeChar('\n');
            // dos.writeBoolean(true);

            dos.writeShort(9);  // 2 byte: 0009 大端，低字节在高位
            dos.writeShort(0x1234); // 2 byte: 1234
            dos.writeInt(5);
            dos.write(255);
            dos.write(255);
            dos.write(255);
            dos.write(255);
            dos.writeLong(15);
            dos.flush();

            // 小倒，大正; xxd的坐标系
            // big endian: 低字节在高位
            // $ xxd short.txt
            //00000000: 0100 0d01 0009 1234 0000 0005 ffff ffff  .......4........
            //00000010: 0000 0000 0000 000f

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.java.examples.net.stream;

import java.io.*;

public class DataInputStreamDemo {
    public static void main(String[] args) {
        try(DataInputStream fin =
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(new File("/tmp/dataout.txt")), 1024))) {
            System.out.println(fin.readFloat());
            System.out.println(fin.readBoolean());
            System.out.println(fin.read());
            System.out.println(fin.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

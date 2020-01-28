package com.java.examples.net.stream;

import java.io.*;

public class DataOutputStreamDemo {
    public static void main(String[] args) {
        try (DataOutputStream fos =
                     new DataOutputStream(new BufferedOutputStream(
                     new FileOutputStream(new File("/tmp/dataout.txt")), 1024))) {
            fos.writeFloat(38.0F);
            fos.writeBoolean(true);
            fos.write('\n');
            fos.writeUTF("我试试");
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

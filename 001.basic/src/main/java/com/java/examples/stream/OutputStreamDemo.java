package com.java.examples.stream;

import java.io.*;

public class OutputStreamDemo {
    public static void main(String[] args) {
        try (FileOutputStream fos =
                     new FileOutputStream(new File("/tmp/abcd.txt"))){
            fos.write('a');
            fos.write("你好1234\n".getBytes());
            for (int i = 0; i < 10000000; i++) {
                fos.write('\n');
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void foo() {
        try(DataOutputStream dataOutputStream = new DataOutputStream(
                new FileOutputStream(new File("/tmp/xxx")))) {
            dataOutputStream.writeInt(80);
            dataOutputStream.writeFloat(90.5F);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

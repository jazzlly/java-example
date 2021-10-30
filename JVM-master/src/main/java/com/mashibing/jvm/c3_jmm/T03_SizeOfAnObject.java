package com.mashibing.jvm.c3_jmm;

//import com.mashibing.jvm.agent.ObjectSizeAgent;

import com.mashibing.jvm.agent.ObjectSizeAgent;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class T03_SizeOfAnObject {
    public static void main(String[] args) {
        /*
        java -XX:+PrintCommandLineFlags -version
-XX:InitialHeapSize=263976256 -XX:MaxHeapSize=4223620096 -XX:+PrintCommandLineFlags
-XX:+UseCompressedClassPointers -XX:+UseCompressedOops
-XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
         */

        // https://www.baeldung.com/java-memory-layout
        System.out.println(VM.current().details());
        /*
# Running 64-bit HotSpot VM.
# Using compressed klass with 3-bit shift.
# Using compressed oop with 3-bit shift.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
references take 4 bytes, booleans and bytes take 1 byte, shorts and chars take 2 bytes,
 ints and floats take 4 bytes, and finally, longs and doubles take 8 bytes
         */

        System.out.println(ClassLayout.parseClass(SimpleInt.class).toPrintable());
/*
com.mashibing.jvm.c3_jmm.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0    12        (object header)                           N/A
     12     4    int SimpleInt.state                           N/A
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

object header: 8 bytes mark and 4 bytes klass
 */
        /*
The hashCode() is one of the common methods for all Java objects.
When we don't declare a hashCode() method for a class, Java will use the identity hash code for it.

The identity hash code won't change for an object during its lifetime.
Therefore, the HotSpot JVM stores this value in the mark word once it's computed.
         */
        SimpleInt simpleInt = new SimpleInt(255);

        // 还没有计算hashcode
        System.out.println(ClassLayout.parseInstance(simpleInt).toPrintable());
        /*
 com.mashibing.jvm.c3_jmm.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           82 32 01 f8 (10000010 00110010 00000001 11111000) (-134139262)
     12     4    int SimpleInt.state                           0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println("The identity hash code is:");
        System.out.println(System.identityHashCode(simpleInt));
        System.out.println(ClassLayout.parseInstance(simpleInt).toPrintable());
        /*
The identity hash code is: 1174290147 (45 fe 3e e3 [little endian])
com.mashibing.jvm.c3_jmm.SimpleInt object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 e3 3e fe (00000001 11100011 00111110 11111110) (-29433087)
      4     4        (object header)                           45 00 00 00 (01000101 00000000 00000000 00000000) (69)
      8     4        (object header)                           82 32 01 f8 (10000010 00110010 00000001 11111000) (-134139262)
     12     4    int SimpleInt.state                           255
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
        System.out.println("lock object");
        //
        synchronized (simpleInt) {
            System.out.println(ClassLayout.parseInstance(simpleInt).toPrintable());
            /*
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           30 f5 9a 02 (00110000 11110101 10011010 00000010) (43709744)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           82 32 01 f8 (10000010 00110010 00000001 11111000) (-134139262)
     12     4    int SimpleInt.state                           255
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
             */
        }

        // padding:
        SimpleLong aLong = new SimpleLong(255L);
        System.out.println(System.identityHashCode(aLong));
        System.out.println(ClassLayout.parseInstance(aLong).toPrintable());
        /*
        hashcode: 1289696681 (4c df 35 a9)
com.mashibing.jvm.c3_jmm.SimpleLong object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 a9 35 df (00000001 10101001 00110101 11011111) (-550131455)
      4     4        (object header)                           4c 00 00 00 (01001100 00000000 00000000 00000000) (76)
      8     4        (object header)                           e1 34 01 f8 (11100001 00110100 00000001 11111000) (-134138655)
     12     4        (alignment/padding gap)
     16     8   long SimpleLong.state                          255
Instance size: 24 bytes
Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
         */

        System.out.println(ClassLayout.parseClass(SimpleField.class).toPrintable());
        /*
        com.mashibing.jvm.c3_jmm.SimpleField object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0    12           (object header)                           N/A
     12     4       int SimpleField.fourth                        N/A
     16     8    double SimpleField.third                         N/A
     24     2      char SimpleField.second                        N/A
     26     1   boolean SimpleField.first                         N/A
     27     1   boolean SimpleField.fifth                         N/A
     28     4           (loss due to the next object alignment)
Instance size: 32 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */


//        System.out.println(ObjectSizeAgent.sizeOf(new Object()));
//        System.out.println(ObjectSizeAgent.sizeOf(new int[] {}));
//        System.out.println(ObjectSizeAgent.sizeOf(new P()));
    }


    //一个Object占多少个字节
    // -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
    // Oops = ordinary object pointers
    private static class P {
                        //8 _markword
                        //4 _class pointer
        int id;         //4
        String name;    //4
        int age;        //4

        byte b1;        //1
        byte b2;        //1

        Object o;       //4
        byte b3;        //1

    }
}

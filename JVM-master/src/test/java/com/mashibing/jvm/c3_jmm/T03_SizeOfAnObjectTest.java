package com.mashibing.jvm.c3_jmm;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * https://shipilev.net/jvm/objects-inside-out/
 * https://www.baeldung.com/java-memory-layout
 * https://hg.openjdk.java.net/code-tools/jol/file/tip/jol-samples/src/main/java/org/openjdk/jol/samples/
 */
public class T03_SizeOfAnObjectTest {

    public static class A {
        boolean aBoolean;
    }

    public static class AA extends A {
        long aLong;
    }

    public static class AAA extends AA {
        short aShort;
    }

    public static class Along {
        long aLong;
    }

    public static class Data {
        long aLong;
        boolean aBoolean;
        int anInt;
    }

    public static class Data2 {
        boolean bo1, bo2;
        byte b1, b2;
        char c1, c2;
        double d1, d2;
        float f1, f2;
        int i1, i2;
        long l1, l2;
        short s1, s2;
    }

    /*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes;
     *   b) how fields are laid out;
     *   c) how the external alignment beefs up the object size
     */
    @Test
    public void basicVmInfo() {
        System.out.println(VM.current().details());
        /*
# Running 64-bit HotSpot VM.
# Using compressed oop with 3-bit shift.
# Using compressed klass with 3-bit shift.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

references take 4 bytes, booleans and bytes take 1 byte, shorts and chars take 2 bytes,
 ints and floats take 4 bytes, and finally, longs and doubles take 8 bytes
         */
    }

    @Test
    public void classInfo() {
        System.out.println(ClassLayout.parseClass(A.class).toPrintable());
        /*
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$A object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0    12           (object header)                           N/A
     12     1   boolean A.aBoolean                                N/A
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
    }

    @Test
    public void objectLayout() {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        /*
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$A object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           77 16 01 f8 (01110111 00010110 00000001 11111000) (-134146441)
     12     1   boolean A.aBoolean                                false
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
         */
    }

    @Test
    public void objectAligment() {
        Along along = new Along();
        System.out.println(ClassLayout.parseInstance(along).toPrintable());
        /* long类型数据开始位置也是8byte对齐的
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$Along object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           8d 16 01 f8 (10001101 00010110 00000001 11111000) (-134146419)
     12     4        (alignment/padding gap)
     16     8   long Along.aLong                               0
Instance size: 24 bytes
Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
         */
    }

    @Test
    public void objectAligment3() {
        Data2 data2 = new Data2();
        System.out.println(ClassLayout.parseInstance(data2).toPrintable());
        /* 为了对齐对象，虚拟机会自动变换成员变量的位置
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$Data2 object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           8d 16 01 f8 (10001101 00010110 00000001 11111000) (-134146419)
     12     4     float Data2.f1                                  0.0
     16     8    double Data2.d1                                  0.0
     24     8    double Data2.d2                                  0.0
     32     8      long Data2.l1                                  0
     40     8      long Data2.l2                                  0
     48     4     float Data2.f2                                  0.0
     52     4       int Data2.i1                                  0
     56     4       int Data2.i2                                  0
     60     2      char Data2.c1
     62     2      char Data2.c2
     64     2     short Data2.s1                                  0
     66     2     short Data2.s2                                  0
     68     1   boolean Data2.bo1                                 false
     69     1   boolean Data2.bo2                                 false
     70     1      byte Data2.b1                                  0
     71     1      byte Data2.b2                                  0
Instance size: 72 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
         */
    }

    @Test
    public void inheritance() {
        AAA aaa = new AAA();
        System.out.println(ClassLayout.parseInstance(aaa).toPrintable());
        /* 有继承关系的类的成员变量的顺序不会变化
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$AAA object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           09 17 01 f8 (00001001 00010111 00000001 11111000) (-134146295)
     12     1   boolean A.aBoolean                                false
     13     3           (alignment/padding gap)
     16     8      long AA.aLong                                  0
     24     2     short AAA.aShort                                0
     26     6           (loss due to the next object alignment)
Instance size: 32 bytes
Space losses: 3 bytes internal + 6 bytes external = 9 bytes total
         */
    }

    public static class B {
        long a;
    }

    public static class BB extends B {
        long b;
    }

    public static class BBB extends BB {
        long c;
        int d;
    }

    @Test
    public void inheritance2() {
        BBB b = new BBB();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }

    public static class C {
        boolean a;
    }

    public static class CC extends C {
        boolean b;
    }

    public static class CCC extends CC {
        boolean c;
    }

    @Test
    public void inheritance3() {
        CCC ccc = new CCC();
        System.out.println(ClassLayout.parseInstance(ccc).toPrintable());
        /*
         * HotSpot rounds up the instance field block up to reference size.
         * That unfortunately yields the artificial gaps at the end of the class.
         *
        com.mashibing.jvm.c3_jmm.T03_SizeOfAnObjectTest$CCC object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           06 17 01 f8 (00000110 00010111 00000001 11111000) (-134146298)
     12     1   boolean C.a                                       false
     13     3           (alignment/padding gap)
     16     1   boolean CC.b                                      false
     17     3           (alignment/padding gap)
     20     1   boolean CCC.c                                     false
     21     3           (loss due to the next object alignment)
Instance size: 24 bytes
Space losses: 6 bytes internal + 3 bytes external = 9 bytes total
         */
    }


    public static class E {
    }

    public static class EE extends E {
    }

    @Test
    public void inheritance5() {
        // 可以看到class 指针的不同
        System.out.println(VM.current().details());
        E e = new E();
        EE ee = new EE();
        System.out.println(ClassLayout.parseInstance(e).toPrintable());
        System.out.println(ClassLayout.parseInstance(ee).toPrintable());
    }

    /*
     * This one is the example of thin (displaced) lock. The data
     * in mark word when lock is acquired is the reference to the
     * displaced object header, allocated on stack. Once we leave
     * the lock, the displaced header is discarded, and mark word
     * is reverted to the default value.
     */
    @Test
    public void thinLocking() {
        E e = new E();

        ClassLayout layout = ClassLayout.parseInstance(e);

        System.out.println("before locking~");
        System.out.println(layout.toPrintable());

        synchronized (e) {
            System.out.println("after locking~");
            System.out.println(layout.toPrintable());
        }

        System.out.println("after unlocking~");
        System.out.println(layout.toPrintable());
    }


    /*
     * In order to demonstrate this, we first need to sleep for >5 seconds
     * to pass the grace period of biased locking. Then, we do the same
     * trick as the example before. You may notice that the mark word
     * had not changed after the lock was released. That is because
     * the mark word now contains the reference to the thread this object
     * was biased to.
     */
    @Test
    public void biasedLocking() throws InterruptedException {
        final E e = new E();

        TimeUnit.SECONDS.sleep(8);

        ClassLayout layout = ClassLayout.parseInstance(e);

        System.out.println("before locking~");
        System.out.println(layout.toPrintable());

        synchronized (e) {
            System.out.println("after locking~");
            System.out.println(layout.toPrintable());
        }

        System.out.println("after unlocking~");
        System.out.println(layout.toPrintable());
    }

    /*
     * If VM detects contention on thread, it needs to delegate the
     * access arbitrage to OS. That involves associating the object
     * with the native lock, i.e. "inflating" the lock.
     *
     * In this example, we need to simulate the contention, this is
     * why we have the additional thread. You can see the fresh object
     * has the default mark word, the object before the lock was already
     * acquired by the auxiliary thread, and when the lock was finally
     * acquired by main thread, it had been inflated. The inflation stays
     * there after the lock is released. You can also see the lock is
     * "deflated" after the GC (the lock cleanup proceeds in safepoints,
     * actually).
     */
    @Test
    public void fatLocking() throws InterruptedException {
        final E e = new E();
        ClassLayout layout = ClassLayout.parseInstance(e);

        System.out.println("fresh object~");
        System.out.println(layout.toPrintable());

        Thread thread = new Thread(() -> {
            synchronized (e) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("before locking~");
        System.out.println(layout.toPrintable());

        synchronized (e) {
            System.out.println("with the locking~");
            System.out.println(layout.toPrintable());
        }

        System.out.println("after unlocking~");
        System.out.println(layout.toPrintable());

        System.gc();

        System.out.println("after gc~");
        System.out.println(layout.toPrintable());
    }

    /*
     * The identity hash code, once computed, should stay the same.
     * HotSpot opts to store the hash code in the mark word as well.
     * You can clearly see the hash code bytes in the header once
     * it was computed.
     */
    @Test
    public void identityHashcode() {
        E e = new E();
        ClassLayout layout = ClassLayout.parseInstance(e);

        System.out.println("fresh object~");
        System.out.println(layout.toPrintable());

        System.out.println("hash code: " + Integer.toHexString(e.hashCode()));
        System.out.println();

        System.out.println("after identityHashCode()");
        System.out.println(layout.toPrintable());
    }


    /**
     * 显示对象的内存占用情况
     */
    @Test
    public void listTest() {
        ArrayList<Integer> al = new ArrayList<>();
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            Integer io = i; // box once
            al.add(io);
            ll.add(io);
        }

        al.trimToSize();

        PrintWriter pw = new PrintWriter(System.out);
        pw.println(GraphLayout.parseInstance(al).toFootprint());
        pw.println(GraphLayout.parseInstance(ll).toFootprint());
        pw.println(GraphLayout.parseInstance(al, ll).toFootprint());
        pw.close();

        /*
        java.util.ArrayList@27f8302dd footprint:
     COUNT       AVG       SUM   DESCRIPTION
         1      4016      4016   [Ljava.lang.Object;
      1000        16     16000   java.lang.Integer
         1        24        24   java.util.ArrayList
      1002               20040   (total)

java.util.LinkedList@166fa74dd footprint:
     COUNT       AVG       SUM   DESCRIPTION
      1000        16     16000   java.lang.Integer
         1        32        32   java.util.LinkedList
      1000        24     24000   java.util.LinkedList$Node
      2001               40032   (total)

         */
    }

    /**
     * This example shows the addresses of newly allocated objects
     * grow linearly in HotSpot. This is because the allocation in
     * parallel collectors is linear. We can also see it rewinds back
     * to the same offsets -- that's the start of some GC generation.
     * The address of the generation is changing, while GC adjusts
     * for the allocation rate.
     */
    @Test
    public void allocation() {
        PrintWriter pw = new PrintWriter(System.out, true);

        long last = VM.current().addressOf(new Object());
        for (int l = 0; l < 1000 * 1000 * 1000; l++) {
            long current = VM.current().addressOf(new Object());

            long distance = Math.abs(current - last);
            if (distance > 16 * 1024) {
                pw.printf("Jumping from %x to %x (distance = %d bytes, %dK, %dM)%n",
                        last,
                        current,
                        distance,
                        distance / 1024,
                        distance / 1024 / 1024);
            }

            last = current;
        }

        pw.close();
    }

    /*
     * In this example, we see that under collisions, HashMap
     * degrades to the linked list. With JDK 8, we can also see
     * it further "degrades" to the tree.
     */
    @Test
    public void layouts() {
        PrintWriter pw = new PrintWriter(System.out, true);

        Map<Dummy, Void> map = new HashMap<>();

        map.put(new Dummy(1), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);
        map.put(new Dummy(2), null);

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        for (int c = 0; c < 12; c++) {
            map.put(new Dummy(2), null);
        }

        System.gc();
        pw.println(GraphLayout.parseInstance(map).toPrintable());

        pw.close();
    }

    /**
     * Dummy class which controls the hashcode and is decently Comparable.
     */
    public static class Dummy implements Comparable<Dummy> {
        static int ID;
        final int id = ID++;
        final int hc;

        public Dummy(int hc) {
            this.hc = hc;
        }

        @Override
        public boolean equals(Object o) {
            return (this == o);
        }

        @Override
        public int hashCode() {
            return hc;
        }

        @Override
        public int compareTo(Dummy o) {
            return (id < o.id) ? -1 : ((id == o.id) ? 0 : 1);
        }
    }

    static volatile Object sink;

    /*
     * Once the object survives the garbage collections, it is getting
     * promoted to another generation. In this example, we can track
     * the addresses of the objects, as it changes over time.
     *
     * VM also needs to record the "age" (that is, the number of GC
     * cycles the object had survived) of the object somewhere, and
     * it is stored in mark word as well. See how particular mark word
     * bits change with each promotion.
     */
    @Test
    public void objectAddressChange() {
        PrintWriter pw = new PrintWriter(System.out, true);

        Object o = new Object();

        ClassLayout layout = ClassLayout.parseInstance(o);

        long lastAddr = VM.current().addressOf(o);
        pw.printf("Fresh object is at %x%n", lastAddr);

        int moves = 0;
        for (int i = 0; i < 100000; i++) {
            long cur = VM.current().addressOf(o);
            if (cur != lastAddr) {
                moves++;
                pw.printf("*** Move %2d, object is at %x%n", moves, cur);
                System.out.println(layout.toPrintable());
                lastAddr = cur;
            }

            // make garbage
            for (int c = 0; c < 10000; c++) {
                sink = new Object();
            }
        }

        pw.close();
    }

    public interface L {
        L link();
        void bind(L l);
    }

    public static abstract class AL implements L {
        L l;
        public L link() { return l; }
        public void bind(L l) { this.l = l; }
    }

    public static class L1 extends AL {}
    public static class L2 extends AL {}
    public static class L3 extends AL {}
    public static class L4 extends AL {}
    public static class L5 extends AL {}
    public static class L6 extends AL {}

    /*
     * During the GC, object reachability graph should be traversed
     * starting from somewhere. The root set is the set of intrinsically
     * reachable objects. Static fields are the part of root set, local
     * variables are the part of root set as well.
     *
     * In this example, we build the "ring" of objects, and reference
     * only the single link from that ring from the local variable.
     * This will have the effect of having the different parts of ring
     * in the root set, which will, in the end, change the ring layout
     * in memory.
     */
    @Test
    public void root() {
        PrintWriter pw = new PrintWriter(System.out, true);

        // create links
        L l1 = new L1();
        L l2 = new L2();
        L l3 = new L3();
        L l4 = new L4();
        L l5 = new L5();
        L l6 = new L6();

        // bind the ring
        l1.bind(l2);
        l2.bind(l3);
        l3.bind(l4);
        l4.bind(l5);
        l5.bind(l6);
        l6.bind(l1);

        // current root
        L r = l1;

        // break all other roots
        l1 = l2 = l3 = l4 = l5 = l6 = null;

        long lastAddr = VM.current().addressOf(r);
        pw.printf("Fresh object is at %x%n", lastAddr);

        int moves = 0;
        for (int i = 0; i < 100000; i++) {

            // scan for L1 and determine it's address
            L s = r;
            while (!((s = s.link()) instanceof L1)) ;

            long cur = VM.current().addressOf(s);
            s = null;

            // if L1 had moved, then probably the entire ring had also moved
            if (cur != lastAddr) {
                moves++;
                pw.printf("*** Move %2d, L1 is at %x%n", moves, cur);
                pw.println("*** Root is " + r.getClass());

                pw.println(GraphLayout.parseInstance(r).toPrintable());

                // select another link
                Random random = new Random();
                for (int c = 0; c < random.nextInt(100); c++) {
                    r = r.link();
                }

                lastAddr = cur;
            }

            // make garbage
            for (int c = 0; c < 10000; c++) {
                sink = new Object();
            }
        }

        pw.close();
    }

    @Test
    public void arrayGC() {
        PrintWriter pw = new PrintWriter(System.out, true);

        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Integer(i);
        }

        String last = null;
        for (int c = 0; c < 100; c++) {
            String current = GraphLayout.parseInstance((Object) arr).toPrintable();

            if (last == null || !last.equalsIgnoreCase(current)) {
                pw.println(current);
                last = current;
            }

            System.gc();
        }

        pw.close();
    }
}
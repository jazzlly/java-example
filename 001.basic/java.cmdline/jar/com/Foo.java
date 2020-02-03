package com;

public class Foo {

    int msg(String msg) {
        System.out.println("foo: " + msg);
        return 0;
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.msg("wahaha!");
    }
}

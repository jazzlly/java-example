package com.java.examples.design.pattern.d002strategy;

@FunctionalInterface
public interface MyComparator<T> {
    int compare(T t1, T t2);
}

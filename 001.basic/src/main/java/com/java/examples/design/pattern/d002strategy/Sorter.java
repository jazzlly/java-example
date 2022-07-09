package com.java.examples.design.pattern.d002strategy;

public class Sorter<T> {
    public void sort(T[] arr, MyComparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j+1]) > 0) {
                    T temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}

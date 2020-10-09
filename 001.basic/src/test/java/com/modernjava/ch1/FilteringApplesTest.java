package com.modernjava.ch1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FilteringApplesTest {

    @Test
    public void integerStreamFilteredByNumberPredicate() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Number> numberPredicate = number -> number.doubleValue() > 3.0;

        integers.stream().filter(numberPredicate).forEach(integer -> System.out.println(integer));
    }

    @Test
    public void numberStream_NOT_FilterByIntegerStream() {
        List<Number> numbers = Arrays.asList(1, 2, 3, 4, 9.0, 8L);
        Predicate<Integer> integerPredicate = integer -> integer > 3;

        // error:
        // numbers.stream().forEach(integerPredicate);
    }

    @Test
    public void numberStreamMapToStringStream() {
        List<Number> numbers = Arrays.asList(1.2, 3L, 5, 13.8f, 23);

        numbers.stream().map(new Function<Number, Number>() {
            @Override
            public Integer apply(Number number) {
                return number.intValue();
            }
        }).forEach(new Consumer<Object>() {
            @Override
            public void accept(Object s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void name() {
        Function<? super Integer, ? extends Number> function = new Function<Number, Number>() {
            @Override
            public Integer apply(Number integer) {
                return integer.intValue();
            }
        };

        Function<? super Integer, ? extends Number> function1 = new Function<Number, Number>() {
            @Override
            public Double apply(Number integer) {
                return integer.doubleValue();
            }
        };
    }

    @Test
    public void name1() {
        // Stream<T> sorted(Comparator<? super T> comparator);
        // 可以使用一个 Comparator<Number> 来比较一个<Integer>的stream的
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().sorted(new Comparator<Number>() {
            @Override
            public int compare(Number o1, Number o2) {
                return (int) (o1.doubleValue() - o2.doubleValue());
            }
        });
    }

    @Test
    public void testReduce() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(
                integers.stream().reduce((integer, integer2) -> integer + integer2));
        System.out.println(
                integers.stream().reduce(10, (integer, integer2) -> integer + integer2));
    }

    @Test
    public void streamOf() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
        Stream<Number> numberStream = Stream.of(1.0, 2.0f, 3L, 50);

    }
}
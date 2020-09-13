package com.geektime.pitfall;


import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

public class P010Java8FeatureTest {

    @Test
    public void smoke() {
        Supplier<String> ok = () -> "OK";

        test(ok);
        test(() -> "wahaha");
        test(() -> "kakaka");
        test(() -> "hahah");

        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt(100);
        System.out.println(random.get());
        System.out.println(random.get());
        System.out.println(random.get());
    }

    void test(Supplier<String> stringSupplier) {
        System.out.println(stringSupplier.get());
    }

    @Test
    public void predicateTest() {
        Predicate<Integer> positivePredicate = integer -> integer > 0;
        assertThat(positivePredicate.test(5)).isTrue();
        assertThat(positivePredicate.test(0)).isFalse();

        Predicate<Integer> evenNumber = integer -> integer % 2 == 0;
        assertThat(evenNumber.test(0)).isTrue();
        assertThat(evenNumber.test(2)).isTrue();
        assertThat(evenNumber.test(9)).isFalse();
    }

    @Test
    public void consumerTest() {
        Consumer<String> println = System.out::println;
        Consumer<String> println2 = s -> {
            System.out.println("hahah");
            System.out.println(s);
        };

        println.andThen(println2).accept("foo");
    }

    @Test
    public void functionTest() {
        Function<String, String> uppercase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        assertThat(uppercase.andThen(duplicate).apply("foo")).isEqualTo("FOOFOO");
    }

    @Test
    public void binaryOpTest() {
        BinaryOperator<Integer> add = (integer, integer2) -> integer + integer2;
        BinaryOperator<Integer> add2 = Integer::sum;
        BinaryOperator<Integer> sub = (integer, integer2) -> integer - integer2;
        assertThat(add2.apply(5, 4)).isEqualTo(9);
        assertThat(sub.apply(3, 9)).isEqualTo(-6);
    }

    @Test
    public void optional() {
        assertThat(Optional.of(1).get()).isEqualTo(1);
        assertThat(Optional.ofNullable(null).isPresent()).isFalse();
        assertThat(Optional.ofNullable(null).orElse("hahaha")).isEqualTo("hahaha");

        assertThat(OptionalDouble.empty().isPresent()).isFalse();
        assertThat(OptionalInt.empty().isPresent()).isFalse();
        assertThat(Optional.of(1).filter(integer -> integer % 2 == 0).isPresent()).isFalse();

        Optional.of(2).filter(i -> i % 2 == 0).ifPresent(System.out::println);
    }

    @Test(expected = IllegalArgumentException.class)
    public void optionalException() {
        Optional.empty().orElseThrow(IllegalArgumentException::new);
    }

    @Test(expected = NoSuchElementException.class)
    public void optionalError() {
        Optional.ofNullable(null).get();
    }
}
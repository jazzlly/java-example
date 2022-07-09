package com.java.examples.design.pattern.d002strategy;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Cat {
    Integer weight;
    Integer size;

    public static void main(String[] args) {
        Cat[] cats = {
                Cat.builder()
                        .weight(10)
                        .size(29)
                        .build(),
                Cat.builder()
                        .weight(20)
                        .size(15)
                        .build(),
                Cat.builder()
                        .weight(5)
                        .size(32)
                        .build()
        };
        Sorter<Cat> sorter = new Sorter<>();

        // 按照weight排序
        sorter.sort(cats, (t1, t2) -> t1.weight.compareTo(t2.weight));
        System.out.println(Arrays.toString(cats));

        // 按照size排序
        sorter.sort(cats, ((t1, t2) -> t1.size.compareTo(t2.size)));
        System.out.println(Arrays.toString(cats));
    }
}

package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Example {

    public static void main(String[] args) {
        Function<String, Supplier<Number>> factory = factoryKit(builder -> {
            builder.accept("Integer", () -> Integer.parseInt("1"));
            builder.accept("Double", () -> Double.parseDouble("1"));
        }, name -> { throw new IllegalArgumentException("unkown number " + name); });

        Number n1 = factory.apply("Integer").get();
        Number n2 = factory.apply("Double").get();
        System.out.println("hello");
    }

    static <K, T> Function<K, T> factoryKit(Consumer<BiConsumer<K, T>> consumer, Function<K, T> ifAbsent) {
        Map<K, T> map = new HashMap<>();
        consumer.accept(map::put);
        return key -> map.computeIfAbsent(key, ifAbsent);
    }
}

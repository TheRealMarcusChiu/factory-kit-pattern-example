package com.example.demo.testsuite;

import com.example.demo.model.Base;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.builder.DefaultBuilderPrototype;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FabricFactoryKit {

    public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> baseFactory1() {
        return baseFactoryKit(builder -> {
            builder.accept(Edge1.class, DefaultBuilderPrototype::edge1);
            builder.accept(Node1.class, DefaultBuilderPrototype::node1);
            builder.accept(Node2.class, DefaultBuilderPrototype::node2);
        }, clazz -> {
            throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
        });
    }

    public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> baseFactory2() {
        return baseFactoryKit(builder -> {
            builder.accept(Edge1.class, () -> DefaultBuilderPrototype.edge1().edge1Description("edge1 - baseFactory2"));
            builder.accept(Node1.class, () -> DefaultBuilderPrototype.node1().node1Description("node1 - baseFactory2"));
            builder.accept(Node2.class, () -> DefaultBuilderPrototype.node2().node2Description("node2 - baseFactory2"));
        }, clazz -> {
            throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
        });
    }

    public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> baseFactoryKit(
            Consumer<BiConsumer<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>>> consumer,
            Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> ifAbsent) {
        return genericFactoryKit(consumer, ifAbsent);
    }

    private static <K, T> Function<K, T> genericFactoryKit(Consumer<BiConsumer<K, T>> consumer, Function<K, T> ifAbsent) {
        Map<K, T> map = new HashMap<>();
        consumer.accept(map::put);
        return key -> map.computeIfAbsent(key, ifAbsent);
    }
}

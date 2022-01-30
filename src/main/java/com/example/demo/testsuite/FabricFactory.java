package com.example.demo.testsuite;

import com.example.demo.model.Base;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.model.visitor.BaseVisitorReturn;
import com.example.demo.testsuite.builder.DefaultBuilderPrototype;
import com.example.demo.testsuite.visitors.UuidBaseVisitor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
public class FabricFactory implements Function<Class<? extends Base>, Base.BaseBuilder<? extends Base, ?>> {

    private Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> factory;
    private Function<Base, String> uuidFunction = UuidFunction.random();

    public static FabricFactory.FabricFactoryBuilder<?, ?> builder() {
        return new FabricFactory().toBuilder();
    }

    @Override
    public Base.BaseBuilder<? extends Base, ?> apply(Class<? extends Base> aClass) {
        Base base = factory.apply(aClass).get().build();
        return base.toBuilder().uuid(uuidFunction.apply(base));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FactoryKit {

        public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> factory1() {
            return customFactory(builder -> {
                builder.accept(Edge1.class, DefaultBuilderPrototype::edge1);
                builder.accept(Node1.class, DefaultBuilderPrototype::node1);
                builder.accept(Node2.class, DefaultBuilderPrototype::node2);
            }, clazz -> {
                throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
            });
        }

        public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> factory2() {
            return customFactory(builder -> {
                builder.accept(Edge1.class, () -> DefaultBuilderPrototype.edge1().edge1Description("edge1 - baseFactory2"));
                builder.accept(Node1.class, () -> DefaultBuilderPrototype.node1().node1Description("node1 - baseFactory2"));
                builder.accept(Node2.class, () -> DefaultBuilderPrototype.node2().node2Description("node2 - baseFactory2"));
            }, clazz -> {
                throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
            });
        }

        public static Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> customFactory(
                Consumer<BiConsumer<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>>> consumer,
                Function<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> ifAbsent) {
            Map<Class<? extends Base>, Supplier<? extends Base.BaseBuilder<?, ?>>> map = new HashMap<>();
            consumer.accept(map::put);
            return key -> map.computeIfAbsent(key, ifAbsent);
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UuidFunction {
        public static Function<Base, String> random() {
            return v -> UUID.randomUUID().toString();
        }

        public static Function<Base, String> fixed(final String staticUuid) {
            return v -> staticUuid;
        }

        public static Function<Base, String> visitor(BaseVisitorReturn<String> visitorReturn) {
            return base -> base.accept(visitorReturn);
        }
    }
}

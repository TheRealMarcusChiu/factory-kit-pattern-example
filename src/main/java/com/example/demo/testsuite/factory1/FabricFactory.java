package com.example.demo.testsuite.factory1;

import com.example.demo.model.Asset;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.model.visitor.AssetVisitorReturn;
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
public class FabricFactory implements Function<Class<? extends Asset>, Asset.AssetBuilder<? extends Asset, ?>> {

    private Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> factory;
    private Function<Asset, String> uuidFunction = UuidFunction.random();

    public static FabricFactory.FabricFactoryBuilder<?, ?> builder() {
        return new FabricFactory().toBuilder();
    }

    @Override
    public Asset.AssetBuilder<? extends Asset, ?> apply(Class<? extends Asset> aClass) {
        Asset asset = factory.apply(aClass).get().build();
        return asset.toBuilder().uuid(uuidFunction.apply(asset));
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FactoryKit {

        public static Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> factory1() {
            return customFactory(builder -> {
                builder.accept(Edge1.class, DefaultBuilderPrototype::edge1);
                builder.accept(Node1.class, DefaultBuilderPrototype::node1);
                builder.accept(Node2.class, DefaultBuilderPrototype::node2);
            }, clazz -> {
                throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
            });
        }

        public static Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> factory2() {
            return customFactory(builder -> {
                builder.accept(Edge1.class, () -> DefaultBuilderPrototype.edge1().edge1Description("edge1 - baseFactory2"));
                builder.accept(Node1.class, () -> DefaultBuilderPrototype.node1().node1Description("node1 - baseFactory2"));
                builder.accept(Node2.class, () -> DefaultBuilderPrototype.node2().node2Description("node2 - baseFactory2"));
            }, clazz -> {
                throw new IllegalArgumentException("unknown class " + clazz.getCanonicalName());
            });
        }

        public static Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> customFactory(
                Consumer<BiConsumer<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>>> consumer,
                Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> ifAbsent) {
            Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> map = new HashMap<>();
            consumer.accept(map::put);
            return key -> map.computeIfAbsent(key, ifAbsent);
        }

        public static Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> customFactory(
                Consumer<BiConsumer<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>>> consumer) {
            Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> map = new HashMap<>();
            consumer.accept(map::put);
            return map::get;
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UuidFunction {
        public static Function<Asset, String> random() {
            return v -> UUID.randomUUID().toString();
        }

        public static Function<Asset, String> fixed(final String staticUuid) {
            return v -> staticUuid;
        }

        public static Function<Asset, String> visitor(AssetVisitorReturn<String> visitorReturn) {
            return base -> base.accept(visitorReturn);
        }
    }
}

package com.example.demo.testsuite.factory3.variant30.pojo.impl.function;

import com.example.demo.model.Asset;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.modifiers.AssetModify;
import com.example.demo.testsuite.factory3.modifiers.BaseModify;
import com.example.demo.testsuite.factory3.util.FluentHashMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
public class FabricFactoryKit implements Function<Class<? extends Asset>, Asset> {

    private Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> assetPrototypeMap = AssetPrototypes.defaultMap();
    private Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> ifAbsentAssetPrototype = defaultIfAbsentAssetPrototype();
    private List<BaseModify> modifierChain = Modifiers.defaultChain();

    public static FabricFactoryKitBuilder<?, ?> builder() {
        return new FabricFactoryKit().toBuilder();
    }

    @Override
    public Asset apply(Class<? extends Asset> clazz) {
        Asset asset = assetPrototypeMap.computeIfAbsent(clazz, this.ifAbsentAssetPrototype).get().build();
        for (BaseModify modifier : modifierChain) {
            asset = asset.accept(modifier);
        }
        return asset;
    }

    public abstract static class FabricFactoryKitBuilder<C extends FabricFactoryKit, B extends FabricFactoryKitBuilder<C, B>> {
        public B assetPrototypeMap(final Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> assetPrototypeMap) {
            this.assetPrototypeMap = new HashMap<>(assetPrototypeMap);
            return this.self();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AssetPrototypes {
        public static FluentHashMap<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> defaultMap() {
            FluentHashMap<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> t = new FluentHashMap<>();
            t.with(Edge1.class, com.example.demo.testsuite.factory3.variant10.builder.impl.function.FabricFactoryKit.AssetPrototypes::defaultEdge1)
                    .with(Node1.class, com.example.demo.testsuite.factory3.variant10.builder.impl.function.FabricFactoryKit.AssetPrototypes::defaultNode1)
                    .with(Node2.class, com.example.demo.testsuite.factory3.variant10.builder.impl.function.FabricFactoryKit.AssetPrototypes::defaultNode2);
            return t;
        }

        public static Edge1.Edge1Builder<?, ?> defaultEdge1() {
            return Edge1.builder().assetDescription("default asset description").edge1Description("edge1 - baseFactory2");
        }

        public static Node1.Node1Builder<?, ?> defaultNode1() {
            return Node1.builder().assetDescription("default asset description").node1Description("node1 - baseFactory2");
        }

        public static Node2.Node2Builder<?, ?> defaultNode2() {
            return Node2.builder().assetDescription("default asset description").node2Description("node2 - baseFactory2");
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Modifiers {

        public static List<BaseModify> defaultChain() {
            return List.of(assetSetUuidRandom());
        }

        public static AssetModify assetSetUuidRandom() {
            return new AssetModify(AssetModify.setUuidRandom());
        }
    }

    public static <T extends Class<? extends Asset>> Function<T, Supplier<? extends Asset.AssetBuilder<?, ?>>> defaultIfAbsentAssetPrototype() {
        return T -> {
            throw new IllegalArgumentException("unknown class " + T.getCanonicalName());
        };
    }
}

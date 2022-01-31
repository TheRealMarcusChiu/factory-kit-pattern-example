package com.example.demo.testsuite.factory3.variant11.removed.ifabsent;

import com.example.demo.model.Asset;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.modifiers.BaseModify;
import com.example.demo.testsuite.factory3.modifiers.AssetModify;
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
public class FabricFactoryKit implements Function<Class<? extends Asset>, Asset.AssetBuilder<?, ?>> {

    private Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> assetPrototypeMap = AssetPrototypes.defaultMap();
    private List<BaseModify> modifierChain = Modifiers.defaultChain();

    public static FabricFactoryKitBuilder<?, ?> builder() {
        return new FabricFactoryKit().toBuilder();
    }

    @Override
    public Asset.AssetBuilder<?, ?> apply(Class<? extends Asset> clazz) {
        Asset asset = assetPrototypeMap.get(clazz).get().build();
        for (BaseModify modifier : modifierChain) {
            asset = asset.accept(modifier);
        }
        return asset.toBuilder();
    }

    public abstract static class FabricFactoryKitBuilder<C extends FabricFactoryKit, B extends FabricFactoryKitBuilder<C, B>> {
        public B assetPrototypeMap(final Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> assetPrototypeMap) {
            this.assetPrototypeMap = new HashMap<>(assetPrototypeMap);
            return this.self();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AssetPrototypes {
        public static Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> defaultMap() {
            return new HashMap<>(Map.of(
                    Edge1.class, AssetPrototypes::defaultEdge1,
                    Node1.class, AssetPrototypes::defaultNode1,
                    Node2.class, AssetPrototypes::defaultNode2
            ));
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
}

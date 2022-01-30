package com.example.demo.testsuite.factory3;

import com.example.demo.model.Asset;
import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.AssetModify;
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

    private Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> suppliers = Suppliers.default1();
    private Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> ifAbsent = IfAbsent.default1();
    private List<BaseModify> modifiers = Modifiers.default1();

    public static FabricFactoryKit.FabricFactoryKitBuilder<?, ?> builder() {
        return new FabricFactoryKit().toBuilder();
    }

    @Override
    public Asset.AssetBuilder<?, ?> apply(Class<? extends Asset> aClass) {
        Asset asset = suppliers.computeIfAbsent(aClass, this.ifAbsent).get().build();
        for (BaseModify modifier : modifiers) {
            asset = asset.accept(modifier);
        }
        return asset.toBuilder();
    }

    public abstract static class FabricFactoryKitBuilder<C extends FabricFactoryKit, B extends FabricFactoryKit.FabricFactoryKitBuilder<C, B>> {
        public B suppliers(final Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> suppliers) {
            this.suppliers = new HashMap<>(suppliers);
            return this.self();
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Suppliers {
        public static Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> default1() {
            return new HashMap<>(Map.of(
                    Edge1.class, () -> Edge1.builder().assetDescription("default asset description").edge1Description("edge1 - baseFactory2"),
                    Node1.class, () -> Node1.builder().assetDescription("default asset description").node1Description("node1 - baseFactory2"),
                    Node2.class, () -> Node2.builder().assetDescription("default asset description").node2Description("node2 - baseFactory2")
            ));
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class IfAbsent {
        public static <T extends Class<? extends Asset>> Function<T, Supplier<? extends Asset.AssetBuilder<?, ?>>> default1() {
            return T -> {
                throw new IllegalArgumentException("unknown class " + T.getCanonicalName());
            };
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Modifiers {

        public static List<BaseModify> default1() {
            return List.of(assetSetUuidRandom());
        }

        public static AssetModify assetSetUuidRandom() {
            return new AssetModify(AssetModify.setUuidRandom());
        }
    }
}

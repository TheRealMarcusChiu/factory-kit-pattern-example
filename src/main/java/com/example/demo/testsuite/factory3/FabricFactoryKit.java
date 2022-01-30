package com.example.demo.testsuite.factory3;

import com.example.demo.model.Asset;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.AssetModify;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
public class FabricFactoryKit implements Function<Class<? extends Asset>, Asset.AssetBuilder<?, ?>> {

    private Map<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> map = new HashMap<>();
    private Function<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>> ifAbsent = IfAbsent.default1();
    private List<BaseModify> modifiers = List.of(
            Modifier.assetSetUuidRandom()
    );

    @Override
    public Asset.AssetBuilder<?, ?> apply(Class<? extends Asset> aClass) {
        Asset asset = map.computeIfAbsent(aClass, this.ifAbsent).get().build();
        for (BaseModify modifier : modifiers) {
            asset = asset.accept(modifier);
        }
        return asset.toBuilder();
    }

    public static FabricFactoryKit.FabricFactoryKitBuilder<?, ?> builder() {
        return new FabricFactoryKit().toBuilder();
    }

    public abstract static class FabricFactoryKitBuilder<C extends FabricFactoryKit, B extends FabricFactoryKit.FabricFactoryKitBuilder<C, B>> {
        public B suppliers(Consumer<BiConsumer<Class<? extends Asset>, Supplier<? extends Asset.AssetBuilder<?, ?>>>> consumer) {
            this.map = new HashMap<>();
            consumer.accept(map::put);
            return this.self();
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
    public static class Modifier {
        public static AssetModify assetSetUuidRandom() {
            return new AssetModify(AssetModify.setUuidRandom());
        }
    }
}

package com.example.demo.testsuite.factory3.visitors.subclasses;

import com.example.demo.model.Asset;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class AssetModify extends BaseModify {

    private final Consumer<Asset> consumer;

    @Override
    public Asset visitBase(Asset asset) {
        consumer.accept(asset);
        return asset;
    }

    public static Consumer<Asset> setUuidRandom() {
        return base -> base.setUuid(UUID.randomUUID().toString());
    }

    public static Consumer<Asset> setUuidFixed(String fixedUuid) {
        return base -> base.setUuid(fixedUuid);
    }
}
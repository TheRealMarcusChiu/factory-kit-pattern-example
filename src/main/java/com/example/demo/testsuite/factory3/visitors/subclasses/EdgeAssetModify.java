package com.example.demo.testsuite.factory3.visitors.subclasses;

import com.example.demo.model.Asset;
import com.example.demo.model.EdgeAsset;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class EdgeAssetModify extends BaseModify {

    private final Consumer<EdgeAsset> consumer;

    @Override
    public Asset visitEdgeBase(EdgeAsset edgeAsset) {
        consumer.accept(edgeAsset);
        return edgeAsset;
    }

    public static Consumer<EdgeAsset> setEdgeAssetDescriptionFixed(String fixedEdgeAssetDescription) {
        return edgeBase -> edgeBase.setEdgeAssetDescription(fixedEdgeAssetDescription);
    }
}

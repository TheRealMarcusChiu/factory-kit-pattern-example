package com.example.demo.testsuite.factory3.modifiers;

import com.example.demo.model.Asset;
import com.example.demo.model.EdgeAsset;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class EdgeAssetModify extends BaseModify {

    private final Consumer<EdgeAsset> consumer;

    @Override
    public Asset visitEdgeAsset(EdgeAsset edgeAsset) {
        consumer.accept(edgeAsset);
        return edgeAsset;
    }

    public static Consumer<EdgeAsset> setEdgeAssetDescriptionFixed(String fixedEdgeAssetDescription) {
        return edgeBase -> edgeBase.setEdgeAssetDescription(fixedEdgeAssetDescription);
    }
}

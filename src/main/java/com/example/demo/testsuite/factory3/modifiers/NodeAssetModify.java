package com.example.demo.testsuite.factory3.modifiers;

import com.example.demo.model.Asset;
import com.example.demo.model.NodeAsset;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class NodeAssetModify extends BaseModify {

    private final Consumer<NodeAsset> consumer;

    @Override
    public Asset visitNodeAsset(NodeAsset nodeAsset) {
        consumer.accept(nodeAsset);
        return nodeAsset;
    }

    public static Consumer<NodeAsset> setNodeAssetDescriptionFixed(String fixedNodeAssetDescription) {
        return nodeBase -> nodeBase.setNodeAssetDescription(fixedNodeAssetDescription);
    }
}

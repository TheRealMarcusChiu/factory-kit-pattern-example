package com.example.demo.testsuite.factory3.visitors.subclasses;

import com.example.demo.model.Asset;
import com.example.demo.model.NodeAsset;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class NodeAssetModify extends BaseModify {

    private final Consumer<NodeAsset> consumer;

    @Override
    public Asset visitNodeBase(NodeAsset nodeBase) {
        consumer.accept(nodeBase);
        return nodeBase;
    }

    public static Consumer<NodeAsset> setNodeAssetDescriptionFixed(String fixedNodeAssetDescription) {
        return nodeBase -> nodeBase.setNodeAssetDescription(fixedNodeAssetDescription);
    }
}

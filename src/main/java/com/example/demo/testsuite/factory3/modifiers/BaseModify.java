package com.example.demo.testsuite.factory3.modifiers;

import com.example.demo.model.*;
import com.example.demo.model.visitor.AssetVisitorReturn;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseModify implements AssetVisitorReturn<Asset> {

    @Override
    public Asset visitAsset(Asset asset) {
        return asset;
    }

    @Override
    public Asset visitNodeAsset(NodeAsset nodeAsset) {
        return visitAsset(nodeAsset);
    }

    @Override
    public Asset visitEdgeAsset(EdgeAsset edgeAsset) {
        return visitAsset(edgeAsset);
    }

    @Override
    public Asset visitEdge1(Edge1 edge1) {
        return visitEdgeAsset(edge1);
    }

    @Override
    public Asset visitNode1(Node1 node1) {
        return visitNodeAsset(node1);
    }

    @Override
    public Asset visitNode2(Node2 node2) {
        return visitNodeAsset(node2);
    }
}

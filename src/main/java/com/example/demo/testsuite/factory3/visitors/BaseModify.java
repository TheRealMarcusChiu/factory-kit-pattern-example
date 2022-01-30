package com.example.demo.testsuite.factory3.visitors;

import com.example.demo.model.*;
import com.example.demo.model.visitor.AssetVisitorReturn;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
public class BaseModify implements AssetVisitorReturn<Asset> {

    @Override
    public Asset visitBase(Asset asset) {
        return asset;
    }

    @Override
    public Asset visitNodeBase(NodeAsset nodeBase) {
        return visitBase(nodeBase);
    }

    @Override
    public Asset visitEdgeBase(EdgeAsset edgeBase) {
        return visitBase(edgeBase);
    }

    @Override
    public Asset visitEdge1(Edge1 edge1) {
        return visitBase(edge1);
    }

    @Override
    public Asset visitNode1(Node1 node1) {
        return visitBase(node1);
    }

    @Override
    public Asset visitNode2(Node2 node2) {
        return visitBase(node2);
    }
}

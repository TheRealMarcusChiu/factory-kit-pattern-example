package com.example.demo.testsuite.factory1.visitors;

import com.example.demo.model.*;
import com.example.demo.model.visitor.AssetVisitorReturn;

public class UuidAssetVisitor implements AssetVisitorReturn<String> {

    @Override
    public String visitBase(Asset asset) {
        return asset.getAssetDescription();
    }

    @Override
    public String visitNodeBase(NodeAsset nodeBase) {
        return nodeBase.getNodeAssetDescription();
    }

    @Override
    public String visitEdgeBase(EdgeAsset edgeBase) {
        return edgeBase.getEdgeAssetDescription();
    }

    @Override
    public String visitEdge1(Edge1 edge1) {
        return edge1.getEdge1Description();
    }

    @Override
    public String visitNode1(Node1 node1) {
        return node1.getNode1Description();
    }

    @Override
    public String visitNode2(Node2 node2) {
        return node2.getNode2Description();
    }
}

package com.example.demo.testsuite.factory1.modifiers;

import com.example.demo.model.*;
import com.example.demo.model.visitor.AssetVisitorReturn;

public class UuidAssetVisitor implements AssetVisitorReturn<String> {

    @Override
    public String visitAsset(Asset asset) {
        return asset.getAssetDescription();
    }

    @Override
    public String visitNodeAsset(NodeAsset nodeAsset) {
        return nodeAsset.getNodeAssetDescription();
    }

    @Override
    public String visitEdgeAsset(EdgeAsset edgeAsset) {
        return edgeAsset.getEdgeAssetDescription();
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

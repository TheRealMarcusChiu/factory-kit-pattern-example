package com.example.demo.model.visitor;

import com.example.demo.model.*;

public interface AssetVisitorVoid {
    void visitAsset(Asset asset);
    void visitNodeAsset(NodeAsset nodeAsset);
    void visitEdgeAsset(EdgeAsset edgeAsset);
    void visitEdge1(Edge1 edge1);
    void visitNode1(Node1 node1);
    void visitNode2(Node2 node2);
}

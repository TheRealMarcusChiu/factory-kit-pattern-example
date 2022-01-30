package com.example.demo.model.visitor;

import com.example.demo.model.*;

public interface AssetVisitorReturn<T> {
    T visitAsset(Asset asset);
    T visitNodeAsset(NodeAsset nodeAsset);
    T visitEdgeAsset(EdgeAsset edgeAsset);
    T visitEdge1(Edge1 edge1);
    T visitNode1(Node1 node1);
    T visitNode2(Node2 node2);
}

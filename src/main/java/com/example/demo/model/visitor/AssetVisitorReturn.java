package com.example.demo.model.visitor;

import com.example.demo.model.*;

public interface AssetVisitorReturn<T> {
    T visitBase(Asset asset);
    T visitNodeBase(NodeAsset nodeBase);
    T visitEdgeBase(EdgeAsset edgeBase);
    T visitEdge1(Edge1 edge1);
    T visitNode1(Node1 node1);
    T visitNode2(Node2 node2);
}

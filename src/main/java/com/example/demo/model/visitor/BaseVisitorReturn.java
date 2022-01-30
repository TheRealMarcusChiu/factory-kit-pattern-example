package com.example.demo.model.visitor;

import com.example.demo.model.*;

public interface BaseVisitorReturn<T> {
    T visitBase(Base base);
    T visitNodeBase(NodeBase nodeBase);
    T visitEdgeBase(EdgeBase edgeBase);
    T visitEdge1(Edge1 edge1);
    T visitNode1(Node1 node1);
    T visitNode2(Node2 node2);
}

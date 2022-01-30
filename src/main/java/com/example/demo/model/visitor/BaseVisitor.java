package com.example.demo.model.visitor;

import com.example.demo.model.*;

public interface BaseVisitor {
    void visitBase(Base base);
    void visitNodeBase(NodeBase nodeBase);
    void visitEdgeBase(EdgeBase edgeBase);
    void visitEdge1(Edge1 edge1);
    void visitNode1(Node1 node1);
    void visitNode2(Node2 node2);
}

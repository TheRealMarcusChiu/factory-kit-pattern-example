package com.example.demo.testsuite.visitors;

import com.example.demo.model.*;
import com.example.demo.model.visitor.BaseVisitorReturn;

public class UuidBaseVisitor implements BaseVisitorReturn<String> {

    @Override
    public String visitBase(Base base) {
        return base.getBaseDescription();
    }

    @Override
    public String visitNodeBase(NodeBase nodeBase) {
        return nodeBase.getNodeBaseDescription();
    }

    @Override
    public String visitEdgeBase(EdgeBase edgeBase) {
        return edgeBase.getEdgeBaseDescription();
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

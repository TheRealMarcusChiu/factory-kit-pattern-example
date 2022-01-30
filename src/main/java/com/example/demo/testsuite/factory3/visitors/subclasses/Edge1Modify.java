package com.example.demo.testsuite.factory3.visitors.subclasses;

import com.example.demo.model.Asset;
import com.example.demo.model.Edge1;
import com.example.demo.testsuite.factory3.visitors.BaseModify;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class Edge1Modify extends BaseModify {

    private final Consumer<Edge1> consumer;

    @Override
    public Asset visitEdge1(Edge1 edgeBase) {
        consumer.accept(edgeBase);
        return edgeBase;
    }

    public static Consumer<Edge1> setEdge1DescriptionFixed(String fixedEdge1Description) {
        return edgeBase -> edgeBase.setEdge1Description(fixedEdge1Description);
    }
}

package com.example.demo.testsuite.factory3.modifiers;

import com.example.demo.model.Asset;
import com.example.demo.model.Node1;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class Node1Modify extends BaseModify {

    private final Consumer<Node1> consumer;

    @Override
    public Asset visitNode1(Node1 nodeBase) {
        consumer.accept(nodeBase);
        return nodeBase;
    }

    public static Consumer<Node1> setNode1DescriptionFixed(String fixedNode1Description) {
        return node1 -> node1.setNode1Description(fixedNode1Description);
    }
}

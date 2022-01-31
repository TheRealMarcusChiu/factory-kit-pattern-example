package com.example.demo.testsuite.factory3.modifiers;

import com.example.demo.model.Asset;
import com.example.demo.model.Node2;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class Node2Modify extends BaseModify {

    private final Consumer<Node2> consumer;

    @Override
    public Asset visitNode2(Node2 nodeBase) {
        consumer.accept(nodeBase);
        return nodeBase;
    }

    public static Consumer<Node2> setNode2DescriptionFixed(String fixedNode2Description) {
        return node2 -> node2.setNode2Description(fixedNode2Description);
    }
}

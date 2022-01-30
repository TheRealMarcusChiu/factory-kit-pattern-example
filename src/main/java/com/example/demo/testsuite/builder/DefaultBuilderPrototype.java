package com.example.demo.testsuite.builder;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;

public class DefaultBuilderPrototype {

    public static Edge1.Edge1Builder<?, ?> edge1() {
        return Edge1.builder().edge1Description("edge1 description");
    }

    public static Node1.Node1Builder<?, ?> node1() {
        return Node1.builder().node1Description("node1 description");
    }

    public static Node2.Node2Builder<?, ?> node2() {
        return Node2.builder().node2Description("node2 description");
    }
}

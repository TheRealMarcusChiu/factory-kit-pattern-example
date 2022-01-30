package com.example.demo.testsuite.factory1;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DefaultBuilderPrototype {

    public static Edge1.Edge1Builder<?, ?> edge1() {
        return Edge1.builder();
    }

    public static Node1.Node1Builder<?, ?> node1() {
        return Node1.builder();
    }

    public static Node2.Node2Builder<?, ?> node2() {
        return Node2.builder();
    }
}

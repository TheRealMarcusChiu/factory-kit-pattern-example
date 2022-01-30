package com.example.demo.testsuite.builder;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;

public interface BuilderPrototypes {
    Edge1.Edge1Builder<?, ?> edge1();
    Node1.Node1Builder<?, ?> node1();
    Node2.Node2Builder<?, ?> node2();
}

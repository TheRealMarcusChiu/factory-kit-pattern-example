package com.example.demo.testsuite.factory1;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory1.modifiers.UuidAssetVisitor;

public class Main {

    public static void main(String[] args) {
        FabricFactory factory = FabricFactory.builder()
                .factory(FabricFactory.FactoryKit.factory1())
                .uuidFunction(FabricFactory.UuidFunction.fixed(null))
                .build();
        testFabricFactory(factory);

        factory = FabricFactory.builder()
                .factory(FabricFactory.FactoryKit.factory1())
                .uuidFunction(FabricFactory.UuidFunction.visitor(new UuidAssetVisitor()))
                .build();
        testFabricFactory(factory);

        factory = FabricFactory.builder()
                .factory(FabricFactory.FactoryKit.factory2())
                .uuidFunction(FabricFactory.UuidFunction.visitor(new UuidAssetVisitor()))
                .build();
        testFabricFactory(factory);
    }

    private static void testFabricFactory(final FabricFactory fabricFactory) {
        // use custom factory method object to create number objects
        Edge1 e1 = (Edge1) fabricFactory.apply(Edge1.class).build();
        Node1 n1 = (Node1) fabricFactory.apply(Node1.class).build();
        Node2 n2 = (Node2) fabricFactory.apply(Node2.class).build();

        System.out.println(e1.getUuid());
        System.out.println(n1.getUuid());
        System.out.println(n2.getUuid());
        System.out.println();
    }
}

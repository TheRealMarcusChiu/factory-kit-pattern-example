package com.example.demo.testsuite.factory3;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.visitors.subclasses.AssetModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.Node1Modify;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // create custom factory at runtime
        FabricFactoryKit factory = FabricFactoryKit.builder()
                .suppliers(builder -> {
                    builder.accept(Edge1.class, () -> Edge1.builder().edge1Description("edge1 - baseFactory2"));
                    builder.accept(Node1.class, () -> Node1.builder().node1Description("node1 - baseFactory2"));
                    builder.accept(Node2.class, () -> Node2.builder().node2Description("node2 - baseFactory2"));
                })
                .modifiers(List.of(
                        new AssetModify(AssetModify.setUuidRandom()),
                        new AssetModify(asset -> asset.setAssetDescription("some asset description")),
                        new Node1Modify(node1 -> node1.setAssetDescription("override asset description"))
                ))
                .build();

        testFabricFactory(factory);
    }

    private static void testFabricFactory(final FabricFactoryKit fabricFactoryKit) {
        Edge1 e1 = (Edge1) fabricFactoryKit.apply(Edge1.class).build();
        Node1 n1 = (Node1) fabricFactoryKit.apply(Node1.class).build();
        Node2 n2 = (Node2) fabricFactoryKit.apply(Node2.class).build();

        System.out.println(e1.getAssetDescription());
        System.out.println(n1.getAssetDescription());
        System.out.println(n2.getAssetDescription());
        System.out.println("");
    }
}

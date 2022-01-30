package com.example.demo.testsuite.factory3.variant1;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.visitors.subclasses.AssetModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.EdgeAssetModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.Node1Modify;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        factoryKit1();
//        factoryKit2();
        factoryKit3();
    }

    private static void factoryKit1() {
        // create custom factory at runtime
        FabricFactoryKit factory = FabricFactoryKit.builder()
                .build();

        testFabricFactory(factory);
    }

    private static void factoryKit2() {
        // create custom factory at runtime
        FabricFactoryKit factory = FabricFactoryKit.builder()
                .prototypes(Map.of(
                        Edge1.class, () -> Edge1.builder().assetDescription("edge1 - baseFactory2"),
                        Node1.class, () -> Node1.builder().assetDescription("node1 - baseFactory2"),
                        Node2.class, () -> Node2.builder().assetDescription("node2 - baseFactory2")
                ))
                .build();

        testFabricFactory(factory);
    }

    private static void factoryKit3() {
        // create custom factory at runtime
        FabricFactoryKit factory = FabricFactoryKit.builder()
                .prototypes(Map.of(
                        Edge1.class, () -> Edge1.builder().assetDescription("edge1 - baseFactory2"),
                        Node1.class, () -> Node1.builder().assetDescription("node1 - baseFactory2"),
                        Node2.class, () -> Node2.builder().assetDescription("node2 - baseFactory2")
                ))
                .modifiers(List.of(
                        new AssetModify(asset -> asset.setUuid("fixed uuid")),
                        new AssetModify(asset -> asset.setAssetDescription("modified - AssetModify")),
                        new Node1Modify(node1 -> node1.setAssetDescription("modified - Node1Modify")),
                        new EdgeAssetModify(edgeAsset -> edgeAsset.setAssetDescription("modified - EdgeAssetModify"))
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

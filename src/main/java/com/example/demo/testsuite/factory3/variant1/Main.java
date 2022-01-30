package com.example.demo.testsuite.factory3.variant1;

import com.example.demo.model.Edge1;
import com.example.demo.model.Node1;
import com.example.demo.model.Node2;
import com.example.demo.testsuite.factory3.visitors.subclasses.AssetModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.EdgeAssetModify;
import com.example.demo.testsuite.factory3.visitors.subclasses.Node1Modify;
import com.example.demo.testsuite.factory3.visitors.subclasses.Node2Modify;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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
                .assetPrototypeMap(Map.of(
                        Edge1.class, () -> Edge1.builder().assetDescription("edge1 - baseFactory2"),
                        Node1.class, () -> Node1.builder().assetDescription("node1 - baseFactory2"),
                        Node2.class, () -> Node2.builder().assetDescription("node2 - baseFactory2")
                ))
                .build();

        testFabricFactory(factory);
    }

    private static void factoryKit3() {
        AtomicReference<Integer> i = new AtomicReference<>(0);
        // create custom factory at runtime
        FabricFactoryKit factory = FabricFactoryKit.builder()
                .assetPrototypeMap(Map.of(
                        Edge1.class, () -> Edge1.builder().assetDescription("edge1 - baseFactory2"),
                        Node1.class, () -> Node1.builder().assetDescription("node1 - baseFactory2"),
                        Node2.class, () -> Node2.builder().assetDescription("node2 - baseFactory2")
                ))
                .modifierChain(List.of(
                        // modify assetDescription
                        new AssetModify(asset -> asset.setAssetDescription("modified - AssetModify")),
                        new Node1Modify(node1 -> node1.setAssetDescription("modified - Node1Modify")),
                        new EdgeAssetModify(edgeAsset -> edgeAsset.setAssetDescription("modified - EdgeAssetModify")),
                        // modify uuid
                        new AssetModify(asset -> asset.setUuid("fixed uuid")),
                        new Node1Modify(node1 -> {
                            i.getAndSet(i.get() + 1);
                            node1.setUuid("mock-uuid-" + i);
                        }),
                        new Node2Modify(node2 -> node2.setUuid(UUID.randomUUID().toString()))
                ))
                .build();

        testFabricFactory(factory);
    }

    private static void testFabricFactory(final FabricFactoryKit factory) {
        Edge1 e1 = (Edge1) factory.apply(Edge1.class).build();
        Node1 n1 = (Node1) factory.apply(Node1.class).build();
        Node2 n2 = (Node2) factory.apply(Node2.class).build();

        System.out.println("edge1 assetDescription = " + e1.getAssetDescription());
        System.out.println("node1 assetDescription = " + n1.getAssetDescription());
        System.out.println("node2 assetDescription = " + n2.getAssetDescription());
        System.out.println("edge1 uuid = " + e1.getUuid());
        System.out.println("node1 uuid = " + n1.getUuid());
        System.out.println("node2 uuid = " + n2.getUuid());
        System.out.println("");
    }
}

package com.example.demo.model;

import com.example.demo.model.visitor.AssetVisitorReturn;
import com.example.demo.model.visitor.AssetVisitorVoid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Edge1 extends EdgeAsset {
    private String edge1Description;

    private Node1 node1;
    private Node2 node2;

    public static Edge1.Edge1Builder<?, ?> builder() {
        return new Edge1().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitEdge1(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitEdge1(this);
    }
}

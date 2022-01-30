package com.example.demo.model;

import com.example.demo.model.visitor.AssetVisitorVoid;
import com.example.demo.model.visitor.AssetVisitorReturn;
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
public class Node2 extends NodeAsset {
    private String node2Description;
    private Edge1 edge1;

    public static Node2.Node2Builder<?, ?> builder() {
        return new Node2().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitNode2(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitNode2(this);
    }
}

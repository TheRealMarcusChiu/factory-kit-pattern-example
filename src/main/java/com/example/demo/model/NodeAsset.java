package com.example.demo.model;

import com.example.demo.model.visitor.AssetVisitorVoid;
import com.example.demo.model.visitor.AssetVisitorReturn;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class NodeAsset extends Asset {
    private String nodeAssetDescription;

    public static NodeAsset.NodeAssetBuilder<?, ?> builder() {
        return new NodeAsset().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitNodeBase(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitNodeBase(this);
    }
}

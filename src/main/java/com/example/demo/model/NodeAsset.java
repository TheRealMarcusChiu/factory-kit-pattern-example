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
public class NodeAsset extends Asset {
    private String nodeAssetDescription;

    public static NodeAsset.NodeAssetBuilder<?, ?> builder() {
        return new NodeAsset().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitNodeAsset(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitNodeAsset(this);
    }
}

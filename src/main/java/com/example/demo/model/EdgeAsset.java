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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EdgeAsset extends Asset {
    private String edgeAssetDescription;

    public static EdgeAsset.EdgeAssetBuilder<?, ?> builder() {
        return new EdgeAsset().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitEdgeBase(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitEdgeBase(this);
    }
}

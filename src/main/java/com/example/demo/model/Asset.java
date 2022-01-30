package com.example.demo.model;

import com.example.demo.model.visitor.AcceptAssetVisitorVoid;
import com.example.demo.model.visitor.AcceptAssetVisitorReturn;
import com.example.demo.model.visitor.AssetVisitorVoid;
import com.example.demo.model.visitor.AssetVisitorReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Asset implements AcceptAssetVisitorVoid, AcceptAssetVisitorReturn {
    private String uuid;
    private String assetDescription;

    public static Asset.AssetBuilder<?, ?> builder() {
        return new Asset().toBuilder();
    }

    @Override
    public void accept(AssetVisitorVoid visitor) {
        visitor.visitBase(this);
    }

    @Override
    public <T> T accept(AssetVisitorReturn<T> visitor) {
        return visitor.visitBase(this);
    }
}

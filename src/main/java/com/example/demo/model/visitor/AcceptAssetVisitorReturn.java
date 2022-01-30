package com.example.demo.model.visitor;

public interface AcceptAssetVisitorReturn {
    <T> T accept(AssetVisitorReturn<T> visitor);
}

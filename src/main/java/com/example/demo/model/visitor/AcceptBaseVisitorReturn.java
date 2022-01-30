package com.example.demo.model.visitor;

public interface AcceptBaseVisitorReturn {
    <T> T accept(BaseVisitorReturn<T> visitor);
}

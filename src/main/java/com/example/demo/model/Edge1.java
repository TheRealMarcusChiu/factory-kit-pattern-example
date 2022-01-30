package com.example.demo.model;

import com.example.demo.model.visitor.BaseVisitor;
import com.example.demo.model.visitor.BaseVisitorReturn;
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
public class Edge1 extends EdgeBase {
    private String edge1Description;

    private Node1 node1;
    private Node2 node2;

    public static Edge1.Edge1Builder<?, ?> builder() {
        return new Edge1().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitEdge1(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitEdge1(this);
    }
}

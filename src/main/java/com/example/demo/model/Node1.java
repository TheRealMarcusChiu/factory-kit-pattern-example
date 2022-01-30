package com.example.demo.model;

import com.example.demo.model.visitor.BaseVisitor;
import com.example.demo.model.visitor.BaseVisitorReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Node1 extends NodeBase {
    private String node1Description;
    private List<Edge1> edge1s;

    public static Node1.Node1Builder<?, ?> builder() {
        return new Node1().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitNode1(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitNode1(this);
    }
}


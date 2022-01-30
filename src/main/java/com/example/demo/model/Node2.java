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
public class Node2 extends NodeBase {
    private String node2Description;
    private Edge1 edge1;

    public static Node2.Node2Builder<?, ?> builder() {
        return new Node2().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitNode2(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitNode2(this);
    }
}

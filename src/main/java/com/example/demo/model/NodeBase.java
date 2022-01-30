package com.example.demo.model;

import com.example.demo.model.visitor.BaseVisitor;
import com.example.demo.model.visitor.BaseVisitorReturn;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class NodeBase extends Base {
    private String nodeBaseDescription;

    public static NodeBase.NodeBaseBuilder<?, ?> builder() {
        return new NodeBase().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitNodeBase(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitNodeBase(this);
    }
}

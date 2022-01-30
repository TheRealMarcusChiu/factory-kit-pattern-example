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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EdgeBase extends Base {
    private String edgeBaseDescription;

    public static EdgeBase.EdgeBaseBuilder<?, ?> builder() {
        return new EdgeBase().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitEdgeBase(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitEdgeBase(this);
    }
}

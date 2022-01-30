package com.example.demo.model;

import com.example.demo.model.visitor.AcceptBaseVisitor;
import com.example.demo.model.visitor.AcceptBaseVisitorReturn;
import com.example.demo.model.visitor.BaseVisitor;
import com.example.demo.model.visitor.BaseVisitorReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Base implements AcceptBaseVisitor, AcceptBaseVisitorReturn {
    private String uuid;
    private String baseDescription;

    public static Base.BaseBuilder<?, ?> builder() {
        return new Base().toBuilder();
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visitBase(this);
    }

    @Override
    public <T> T accept(BaseVisitorReturn<T> visitor) {
        return visitor.visitBase(this);
    }
}

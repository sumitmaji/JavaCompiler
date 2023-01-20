package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class FieldNode  extends ExprNode{
    public FieldNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class GreaterThanNode  extends ExprNode{
    public GreaterThanNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

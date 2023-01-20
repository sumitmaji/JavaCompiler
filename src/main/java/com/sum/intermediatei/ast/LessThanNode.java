package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class LessThanNode  extends ExprNode{
    public LessThanNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

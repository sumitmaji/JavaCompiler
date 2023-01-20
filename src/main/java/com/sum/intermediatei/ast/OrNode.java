package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class OrNode  extends ExprNode{
    public OrNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

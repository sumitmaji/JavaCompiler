package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class AndNode extends ExprNode{
    public AndNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

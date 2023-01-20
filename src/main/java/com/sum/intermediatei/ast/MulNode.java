package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class MulNode  extends ExprNode{
    public MulNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

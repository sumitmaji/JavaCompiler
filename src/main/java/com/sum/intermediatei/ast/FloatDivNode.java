package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class FloatDivNode  extends ExprNode{
    public FloatDivNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

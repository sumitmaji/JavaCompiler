package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class BooleanConstNode extends ExprNode{
    public BooleanConstNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class StringConstNode  extends ExprNode{
    public StringConstNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

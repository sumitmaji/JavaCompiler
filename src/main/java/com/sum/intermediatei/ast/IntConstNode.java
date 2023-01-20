package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class IntConstNode  extends ExprNode{
    public IntConstNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return  token.getType() + " value=" + token.getText();
    }
}

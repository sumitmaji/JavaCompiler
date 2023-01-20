package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class GreaterThanEqualToNode  extends ExprNode{
    public GreaterThanEqualToNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

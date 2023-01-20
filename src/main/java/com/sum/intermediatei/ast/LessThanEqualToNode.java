package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class LessThanEqualToNode  extends ExprNode{
    public LessThanEqualToNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

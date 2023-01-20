package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class NotEqualToNode  extends ExprNode{
    public NotEqualToNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

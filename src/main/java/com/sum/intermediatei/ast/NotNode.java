package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class NotNode  extends ExprNode{
    ExprNode left;
    public NotNode(Token payload, ExprNode left) {
        super(payload);
        this.left = left;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

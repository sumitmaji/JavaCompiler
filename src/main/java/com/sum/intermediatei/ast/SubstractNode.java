package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class SubstractNode  extends ExprNode{
    public SubstractNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

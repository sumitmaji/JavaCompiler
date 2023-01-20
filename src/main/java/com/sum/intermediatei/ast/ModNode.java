package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class ModNode  extends ExprNode{
    public ModNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

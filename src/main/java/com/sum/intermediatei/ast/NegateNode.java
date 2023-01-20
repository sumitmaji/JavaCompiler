package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class NegateNode  extends ExprNode{
    public NegateNode(Token payload, ExprNode rootNode) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

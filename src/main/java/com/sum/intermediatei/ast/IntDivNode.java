package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class IntDivNode  extends ExprNode{
    public IntDivNode(Token payload) {
        super(payload);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

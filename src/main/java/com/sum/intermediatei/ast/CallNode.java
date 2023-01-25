package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class CallNode extends ExprNode{
    Node parameters;

    public CallNode(Token payload, Node parameters) {
        super(payload);
        this.parameters = parameters;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

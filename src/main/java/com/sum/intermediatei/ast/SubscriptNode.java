package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class SubscriptNode extends Node{
    public SubscriptNode(Token token) {
        super(token);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

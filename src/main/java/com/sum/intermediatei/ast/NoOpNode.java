package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class NoOpNode extends Node{
    public NoOpNode() {
        super(null);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }
}

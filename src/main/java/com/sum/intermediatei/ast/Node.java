package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public abstract class Node {
    Token token;

    public Node(Token token) {
        this.token = token;
    }

    public abstract void visit(NodeVisitor visitor);
}

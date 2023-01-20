package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class AddNode extends ExprNode{
    ExprNode left, right;

    public AddNode(ExprNode left, Token addToken, ExprNode right) {
        super(addToken);
        this.left = left;
        this.right = right;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return  token.getType().toString();
    }
}

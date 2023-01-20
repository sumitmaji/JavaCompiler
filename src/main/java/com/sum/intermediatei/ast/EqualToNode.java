package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class EqualToNode extends ExprNode{
    ExprNode left;
    ExprNode right;
    public EqualToNode(ExprNode left, Token payload, ExprNode right) {
        super(payload);
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

package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class AssignNode extends Node{
    VariableNode variableNode;
    ExprNode exprNode;
    public AssignNode(VariableNode variableNode, Token token, ExprNode exprNode) {
        super(token);
        this.variableNode = variableNode;
        this.exprNode = exprNode;
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

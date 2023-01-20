package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public class VariableNode extends ExprNode {

    public VariableNode(Token addToken) {
        super(addToken);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return  token.getType() + " id=" + token.getText();
    }
}

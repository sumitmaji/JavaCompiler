package com.sum.intermediatei.ast;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaTokenType;

import java.util.List;

public class ParametersNode extends ExprNode{
    List<Node> parameters;

    public ParametersNode(List<Node> parameters) {
        super(new Token(JavaTokenType.PARAMETER, "compound"));
        this.parameters = parameters;
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public List<Node> getParameters() {
        return parameters;
    }
}

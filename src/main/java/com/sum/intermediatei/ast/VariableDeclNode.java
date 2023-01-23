package com.sum.intermediatei.ast;

public class VariableDeclNode extends ExprNode {

    public VariableDeclNode(Node typeNode, Node... variables ) {
        super(null);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return  "VARIABLE_DECL";
    }
}

package com.sum.intermediatei.ast;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaTokenType;

import java.util.ArrayList;
import java.util.List;

public class CompoundNode extends Node{

    List<Node> childNodes = new ArrayList<>();
    public CompoundNode() {
        super(new Token(JavaTokenType.COMPOUND, "compound"));
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public void addChildNode(Node node){
        childNodes.add(node);
    }

    @Override
    public String toString() {
        return  token.getType().toString();
    }
}

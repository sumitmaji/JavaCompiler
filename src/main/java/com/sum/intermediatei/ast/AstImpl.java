package com.sum.intermediatei.ast;

public class AstImpl implements Ast{
    private Node root;
    @Override
    public Node setRoot(Node node) {
        return this.root = node;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }
}

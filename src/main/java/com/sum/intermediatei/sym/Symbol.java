package com.sum.intermediatei.sym;

import com.sum.intermediatei.ast.Node;

public class Symbol {
    public String name;
    public Type type;
    public Scope scope;
    public Node node;

    public Symbol(String name, Type type, Scope scope) {
        this(name, scope);
        this.type = type;
    }

    public Symbol(String name, Scope scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Scope getScope() {
        return scope;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String toString(){
        if(type == null) return '<' + getName() + ":" + type + '>';
        return getName();
    }
}

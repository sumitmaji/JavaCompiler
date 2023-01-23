package com.sum.intermediatei.sym;

public class ScalarTypeSymbol extends Symbol implements Type{

    public ScalarTypeSymbol(String name, Scope scope) {
        super(name, scope);
    }

    @Override
    public String getName() {
        return name;
    }
}

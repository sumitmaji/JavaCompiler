package com.sum.intermediatei.sym;

public class VariableSymbol extends Symbol{
    public VariableSymbol(String name, Type type, Scope scope) {
        super(name, type, scope);
        scope.define(this);
    }
}

package com.sum.intermediatei.sym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodSymbol extends ScopedSymbol {

    List<Symbol> args = new ArrayList<>();
    public MethodSymbol(String name, Type type, Scope enclosingScope) {
        super(name, type, enclosingScope);
        enclosingScope.define(this);
    }

    @Override
    public String getScopeName() {
        return "method";
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
    }

    @Override
    public void define(Symbol sym) {
        members.put(sym.name, sym);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol s = members.get(name);
        if(s != null) return s;
        return getEnclosingScope().resolve(name);
    }

    public void setArgs(List<Symbol> args) {
        this.args = args;
    }

    public List<Symbol> getArgs() {
        return args;
    }
}

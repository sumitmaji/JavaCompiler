package com.sum.intermediatei.sym;

import java.util.HashMap;

public class LocalScope extends BaseScope{

    Scope enclosingScope;

    public LocalScope(Scope enclosingScope) {
        this.enclosingScope = enclosingScope;
    }

    @Override
    public String getScopeName() {
        return "local";
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    @Override
    public void define(Symbol sym) {
        put(sym.name, sym);
    }

    @Override
    public Symbol resolve(String name) {
        Symbol s = get(name);
        if(s != null) return s;
        return getEnclosingScope().resolve(name);

    }
}

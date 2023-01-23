package com.sum.intermediatei.sym;

public class ProgramSymbol extends ScopedSymbol{

    public ProgramSymbol(String name, Type type, Scope enclosingScope) {
        super(name, type, enclosingScope);
        enclosingScope.define(this);
    }

    @Override
    public String getScopeName() {
        return "program";
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

}

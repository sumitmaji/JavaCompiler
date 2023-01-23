package com.sum.intermediatei.sym;

import java.util.HashMap;

public abstract class ScopedSymbol extends Symbol implements Scope{
    protected HashMap<String, Symbol> members = new HashMap<>();

    public ScopedSymbol(String name, Type type, Scope scope) {
        super(name, type, scope);
    }
}

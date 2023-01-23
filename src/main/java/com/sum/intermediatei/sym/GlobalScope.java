package com.sum.intermediatei.sym;

import java.util.HashMap;

public class GlobalScope extends BaseScope {

    static Symbol intSym;
    static Symbol floatSym;
    static Symbol booleanSym;
    static Symbol falseSym;
    static Symbol trueSym;

    public GlobalScope(){
        initTypesSystem();
    }

    protected void initTypesSystem() {
        define(intSym = new ScalarTypeSymbol("int", this));
        define(floatSym = new ScalarTypeSymbol("float", this));
        define(booleanSym = new EnumerationTypeSymbol("boolean", this));
        define(falseSym = new EnumConstantSymbol("true", (Type) booleanSym, this, new Integer(1)));
        define(trueSym = new EnumConstantSymbol("false", (Type) booleanSym, this, new Integer(0)));
        ((EnumerationTypeSymbol)booleanSym).setEnumConstantSym(falseSym, trueSym);
    }

    @Override
    public String getScopeName() {
        return "global";
    }

    @Override
    public Scope getEnclosingScope() {
        return null;
    }

    @Override
    public void define(Symbol sym) {
        put(sym.name, sym);
    }

    @Override
    public Symbol resolve(String name) {
        return get(name);
    }
}

package com.sum.intermediatei.sym;

public class EnumConstantSymbol extends Symbol{
    Object constValue;
    public EnumConstantSymbol(String name, Type type, Scope scope, Object constValue) {
        super(name, type, scope);
        this.constValue = constValue;
    }
}

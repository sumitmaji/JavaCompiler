package com.sum.intermediatei.sym;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class EnumerationTypeSymbol extends Symbol implements Type{

    List<Symbol> enumConstantSym;
    public EnumerationTypeSymbol(String name, Scope scope) {
        super(name, scope);
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Symbol> getEnumConstantSym() {
        return enumConstantSym;
    }

    public void setEnumConstantSym(Symbol... enumConstantSym) {
        this.enumConstantSym = Arrays.asList(enumConstantSym);
    }
}

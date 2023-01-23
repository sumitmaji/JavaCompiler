package com.sum.intermediatei.sym;

import static com.sum.intermediatei.sym.ScopeTypeImpl.BLOCK;
import static com.sum.intermediatei.sym.ScopeTypeImpl.METHOD;

public class ScopeTreeImpl {

    public Scope currentScope;
    private Symbol programId;

    public ScopeTreeImpl() {
        this.currentScope = new GlobalScope();
    }

    public Symbol resolve(String name){
        return currentScope.resolve(name);
    }

    public void define(Symbol symbol){
        currentScope.define(symbol);
    }

    public void push(Scope scope){
        currentScope = scope;
    }

    public Scope pop(){
        return currentScope = currentScope.getEnclosingScope();
    }

    public Symbol getProgramId() {
        return programId;
    }


    public void setProgramId(Symbol programId) {
        this.programId = programId;
    }
}

package com.sum.intermediatei.ast;

import com.sum.frontend.Token;

public abstract class ExprNode extends Node {
    public ExprNode(Token payload){
        super(payload);
    }
}

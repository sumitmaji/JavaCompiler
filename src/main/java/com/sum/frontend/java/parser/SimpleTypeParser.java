package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;

public class SimpleTypeParser extends JavaParserTD {
    public SimpleTypeParser(JavaParserTD parent) {
        super(parent);
    }

    public Type parser(Token token) throws Exception {

        switch ((JavaTokenType) LA(1)) {
            case IDENTIFIER:
                String name = LT(1).getText().toLowerCase();
                Symbol id = scopeTree.resolve(name);
                if (id != null) {
                    consume(); // consume the type
                    if (id instanceof Type) {
                        return (Type) id;
                    }
                }
            default:
                return null;
        }
    }
}

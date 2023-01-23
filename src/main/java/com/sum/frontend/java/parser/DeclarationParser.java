package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;

import static com.sum.frontend.java.JavaTokenType.*;

public class DeclarationParser extends JavaParserTD {
    public DeclarationParser(JavaParserTD parent) {
        super(parent);
    }

    public void parser(Token token) throws Exception {

        while (isCurrentToken(IDENTIFIER)) {
            Symbol typeSymbol = scopeTree.resolve(token.getText().toLowerCase());
            if (typeSymbol != null && typeSymbol instanceof Type) {
                if (LA(2) == IDENTIFIER) {
                    if (LA(3) == SEMICOLON || LA(3) == COMMA) {
                        VariableDeclarationParser declarationParser = new VariableDeclarationParser(this);
                        declarationParser.parser(token);
                    }

                    if (LA(3) == LEFT_PAREN) {
                        DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
                        declaredMethodParser.parser(token);
                    }
                }
            }else{
                //No declarations are pending
                break;
            }
        }
        if(isCurrentToken(MAIN)){
            DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
            declaredMethodParser.parser(token);
        }
    }
}
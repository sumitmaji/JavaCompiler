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

    public void parse(Token token) throws Exception {

        while (isCurrentToken(IDENTIFIER)) {
            Symbol typeSymbol = scopeTree.resolve(currentToken().getText().toLowerCase());
            if (typeSymbol != null && typeSymbol instanceof Type) {
                if (LA(2) == IDENTIFIER) {
                    if (LA(3) == SEMICOLON || LA(3) == COMMA) {
                        VariableDeclarationParser declarationParser = new VariableDeclarationParser(this);
                        declarationParser.parse(currentToken());
                    }

                    if (LA(3) == LEFT_PAREN) {
                        DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
                        declaredMethodParser.parse(currentToken());
                    }
                }
            }else{
                //No declarations are pending
                break;
            }
        }
        if(isCurrentToken(MAIN)){
            DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
            declaredMethodParser.parse(currentToken());
        }
    }
}

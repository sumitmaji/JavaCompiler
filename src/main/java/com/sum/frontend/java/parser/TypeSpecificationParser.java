package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;

import static com.sum.frontend.java.JavaTokenType.*;

public class TypeSpecificationParser extends JavaParserTD {
    public TypeSpecificationParser(JavaParserTD parent) {
        super(parent);
    }

    public Type parser(Token token) throws Exception {

        switch ((JavaTokenType)LA(1)){
            default:
                SimpleTypeParser simpleTypeParser = new SimpleTypeParser(this);
                return simpleTypeParser.parser(token);
        }
    }
}

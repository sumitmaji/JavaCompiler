package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;

public class ProgramParser extends DeclarationParser {
    public ProgramParser(JavaParserTD parent) {
        super(parent);
    }

    public void parse(Token token) throws Exception {
        DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
        declaredMethodParser.parse(token);
    }
}

package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;
import com.sum.intermediatei.sym.VariableSymbol;

import static com.sum.frontend.java.JavaTokenType.*;

public class ProgramParser extends DeclarationParser {
    public ProgramParser(JavaParserTD parent) {
        super(parent);
    }

    public void parser(Token token) throws Exception {
        DeclaredMethodParser declaredMethodParser = new DeclaredMethodParser(this);
        declaredMethodParser.parser(token);
    }
}

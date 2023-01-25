package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.ast.Node;

public class BlockParser extends JavaParserTD {
    public BlockParser(JavaParserTD parent) {
        super(parent);
    }

    public Node parse(Token token) throws Exception {
        DeclarationParser declarationParser = new DeclarationParser(this);
        declarationParser.parse(currentToken());

        CompoundStatementParser statementParser = new CompoundStatementParser(this);
        return statementParser.parse(currentToken());
    }
}

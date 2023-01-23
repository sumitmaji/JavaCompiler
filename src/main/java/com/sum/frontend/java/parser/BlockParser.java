package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.ast.Node;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;

import static com.sum.frontend.java.JavaTokenType.*;

public class BlockParser extends JavaParserTD {
    public BlockParser(JavaParserTD parent) {
        super(parent);
    }

    public Node parser(Token token) throws Exception {
        DeclarationParser declarationParser = new DeclarationParser(this);
        declarationParser.parser(token);

        CompoundStatementParser statementParser = new CompoundStatementParser(this);
        return statementParser.parse(currentToken());
    }
}

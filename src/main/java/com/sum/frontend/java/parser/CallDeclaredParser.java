package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.intermediatei.ast.CallNode;
import com.sum.intermediatei.ast.Node;

public class CallDeclaredParser extends CallParser {
    public CallDeclaredParser(StatementParser parent) {
        super(parent);
    }

    public Node parse(Token token) throws Exception {

        Node node = parseActualParameters(currentToken());
        CallNode callNode = new CallNode(token, node);
        return callNode;
    }
}

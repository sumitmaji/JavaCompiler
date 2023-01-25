package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediatei.ast.ExprNode;
import com.sum.intermediatei.ast.Node;
import com.sum.intermediatei.ast.ParametersNode;

import java.util.ArrayList;
import java.util.List;

import static com.sum.frontend.java.JavaTokenType.*;

public class CallParser extends StatementParser {
    public CallParser(JavaParserTD parent) {
        super(parent);
    }

    public Node parse(Token token) throws Exception {
        CallDeclaredParser callDeclaredParser = new CallDeclaredParser(this);
        return callDeclaredParser.parse(token);
    }

    public Node parseActualParameters(Token token) throws Exception {
        ExpressionParser expressionParser = new ExpressionParser(this);
        List<Node> parameters = new ArrayList<>();
        match(LEFT_PAREN);
        while(LA(1) != RIGHT_PAREN){
            ExprNode node = expressionParser.parse(currentToken());
            parameters.add(node);

            if(LA(1) == COMMA)
                match(COMMA);
        }
        match(RIGHT_PAREN);
        ParametersNode rootNode = new ParametersNode(parameters);
        return rootNode;
    }
}

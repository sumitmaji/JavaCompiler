package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.ast.AssignNode;
import com.sum.intermediatei.ast.ExprNode;
import com.sum.intermediatei.ast.Node;
import com.sum.intermediatei.ast.VariableNode;
import com.sum.intermediatei.sym.Symbol;

import static com.sum.frontend.java.JavaErrorCode.MISSING_COLON_EQUALS;
import static com.sum.frontend.java.JavaTokenType.COLON_EQUALS;
import static com.sum.frontend.java.JavaTokenType.IDENTIFIER;

public class VariableParser extends StatementParser {

	public VariableParser(JavaParserTD parent) {
		super(parent);
	}

	/**
	 * Parse an assignment statement.
	 * 
	 * @param token
	 *            the initial token.
	 * @return the root node of the generated parse tree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public Node parse(Token token) throws Exception {
		VariableNode variableNode = null;
		String name = token.getText().toLowerCase();
		Symbol resolve = scopeTree.resolve(name);
		if(resolve != null){
			variableNode = new VariableNode(token);
		}
		match(IDENTIFIER);
		return variableNode;
	}
}

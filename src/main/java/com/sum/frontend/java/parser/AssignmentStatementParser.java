package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediate.SymTabEntry;
import com.sum.intermediatei.ast.AssignNode;
import com.sum.intermediatei.ast.ExprNode;
import com.sum.intermediatei.ast.Node;
import com.sum.intermediatei.ast.VariableNode;

import static com.sum.frontend.java.JavaErrorCode.MISSING_COLON_EQUALS;
import static com.sum.frontend.java.JavaErrorCode.MISSING_EQUALS;
import static com.sum.frontend.java.JavaTokenType.*;
import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.ASSIGN;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.VARIABLE;

public class AssignmentStatementParser extends StatementParser {

	public AssignmentStatementParser(JavaParserTD parent) {
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

		VariableParser variableParser = new VariableParser(this);
		Node variableNode = variableParser.parse(currentToken());

//		variableNode.setAttribute(ID, targetId);
		// The ASSIGN node adopts the variable node as its first child.
		// Look for the = token.
		if (isCurrentToken(EQUALS)) {
			token = currentToken();
			match(EQUALS); // consume the =
		} else {
			errorHandler.flag(token, MISSING_EQUALS, this);
		}

		// Parse the expression. The ASSIGN node adopts the expression's
		// node as its second child.
		ExpressionParser expressionParser = new ExpressionParser(this);
		ExprNode exprNode = expressionParser.parse(currentToken());

		// Create the ASSIGN node.
		AssignNode assignNode = new AssignNode((VariableNode) variableNode, token, exprNode);
		return assignNode;
	}
}

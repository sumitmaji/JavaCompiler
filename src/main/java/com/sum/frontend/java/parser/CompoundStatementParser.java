package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediatei.ast.CompoundNode;
import com.sum.intermediatei.ast.Node;

import static com.sum.frontend.java.JavaErrorCode.MISSING_END;
import static com.sum.frontend.java.JavaTokenType.BEGIN;
import static com.sum.frontend.java.JavaTokenType.END;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.COMPOUND;

public class CompoundStatementParser extends StatementParser {

	public CompoundStatementParser(JavaParserTD parent) {
		super(parent);
	}

	/**
	 * Parse a compound statement.
	 * 
	 *            the initial token.
	 * @return the root node of the generated parse tree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public Node parseCompound() throws Exception {
		Token token = currentToken();
		match(BEGIN); // consume the BEGIN
		// Create the COMPOUND node.
		CompoundNode compoundNode = new CompoundNode(token);
		// Parse the statement list terminated by the END token.
		StatementParser statementParser = new StatementParser(this);
		statementParser.parseList(compoundNode, END, MISSING_END);
		return compoundNode;
	}
}

package com.sum.frontend.java.parser;

import com.sum.frontend.EofToken;
import com.sum.frontend.Token;
import com.sum.frontend.TokenType;
import com.sum.frontend.java.JavaErrorCode;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediatei.ast.CompoundNode;
import com.sum.intermediatei.ast.NoOpNode;
import com.sum.intermediatei.ast.Node;

import static com.sum.frontend.java.JavaErrorCode.MISSING_SEMICOLON;
import static com.sum.frontend.java.JavaErrorCode.UNEXPECTED_TOKEN;
import static com.sum.frontend.java.JavaTokenType.IDENTIFIER;
import static com.sum.frontend.java.JavaTokenType.SEMICOLON;
import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.LINE;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.NO_OP;

public class StatementParser extends JavaParserTD {
	/**
	 * Constructor.
	 * 
	 * @param parent
	 *            the parent parser.
	 */
	public StatementParser(JavaParserTD parent) {
		super(parent);
	}

	/**
	 * Parse a statement. To be overridden by the specialized statement parser
	 * subclasses.
	 * 
	 * @param token
	 *            the initial token.
	 * @return the root node of the generated parse tree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public Node parse(Token token) throws Exception {

		Node statementNode = null;
		switch ((JavaTokenType) token.getType()) {
		case BEGIN: {
			CompoundStatementParser compoundParser = new CompoundStatementParser(
					this);
			statementNode = compoundParser.parseCompound();
			break;
		}
			// An assignment statement begins with a variable's identifier.
		case IDENTIFIER: {
			AssignmentStatementParser assignmentParser = new AssignmentStatementParser(
					this);
			statementNode = assignmentParser.parse(currentToken());
			break;
		}
		default: {
			statementNode = new NoOpNode();
			break;
		}
		}
		// Set the current line number as an attribute.
		setLineNumber(statementNode, token);
		return statementNode;
	}

	/**
	 * Set the current line number as a statement node attribute.
	 * 
	 * @param node
	 *            ICodeNode
	 * @param token
	 *            Token
	 */
	protected void setLineNumber(Node node, Token token) {
		if (node != null) {
//			node.setAttribute(LINE, token.getLineNumber());
		}
	}

	/**
	 * Parse a statement list.
	 * 
	 *            the curent token.
	 * @param parentNode
	 *            the parent node of the statement list.
	 * @param terminator
	 *            the token type of the node that terminates the list.
	 * @param errorCode
	 *            the error code if the terminator token is missing.
	 * @throws Exception
	 *             if an error occurred.
	 */
	protected void parseList(CompoundNode parentNode,
							 JavaTokenType terminator, JavaErrorCode errorCode)
			throws Exception {
		Token token = currentToken();
		// Loop to parse each statement until the END token
		// or the end of the source file.
		while (!(token instanceof EofToken) && (token.getType() != terminator)) {
			// Parse a statement. The parent node adopts the statement node.
			Node statementNode = parse(currentToken());
			parentNode.addChildNode(statementNode);
			token = currentToken();
			TokenType tokenType = token.getType();
			// Look for the semicolon between statements.
			if (tokenType == SEMICOLON) {
				match(SEMICOLON); // consume the ;
			}
			// If at the start of the next assignment statement,
			// then missing a semicolon.
			else if (tokenType == IDENTIFIER) {
				errorHandler.flag(token, MISSING_SEMICOLON, this);
			}
			// Unexpected token.
			else if (tokenType != terminator) {
				errorHandler.flag(token, UNEXPECTED_TOKEN, this);
				consume(); // consume the unexpected token
			}

			token = currentToken();
		}
		// Look for the terminator token.
		if (token.getType() == terminator) {
			consume(); // consume the terminator token
		} else {
			errorHandler.flag(token, errorCode, this);
		}
	}
}

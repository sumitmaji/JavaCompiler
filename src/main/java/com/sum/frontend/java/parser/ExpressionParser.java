package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.TokenType;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediate.ICodeNodeType;
import com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl;
import com.sum.intermediatei.ast.*;

import java.util.EnumSet;
import java.util.HashMap;

import static com.sum.frontend.java.JavaErrorCode.MISSING_RIGHT_PAREN;
import static com.sum.frontend.java.JavaErrorCode.UNEXPECTED_TOKEN;
import static com.sum.frontend.java.JavaTokenType.*;
import static com.sum.frontend.java.JavaTokenType.NOT;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.*;

public class ExpressionParser extends JavaParserTD {

	public ExpressionParser(JavaParserTD parent) {
		super(parent);
	}

	/**
	 * Parse an expression.
	 * 
	 *            the initial token.
	 * @return the root node of the generated parse tree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public ExprNode parse(Token token) throws Exception {
		return parseExpression();
	}

	// Set of relational operators.
	private static final EnumSet<JavaTokenType> REL_OPS = EnumSet.of(EQUALS,
			NOT_EQUALS, LESS_THAN, LESS_EQUALS, GREATER_THAN, GREATER_EQUALS);
	// Map relational operator tokens to node types.
	private static final HashMap<JavaTokenType, ICodeNodeType> REL_OPS_MAP = new HashMap<JavaTokenType, ICodeNodeType>();
	static {
		REL_OPS_MAP.put(EQUALS, EQ);
		REL_OPS_MAP.put(NOT_EQUALS, NE);
		REL_OPS_MAP.put(LESS_THAN, LT);
		REL_OPS_MAP.put(LESS_EQUALS, LE);
		REL_OPS_MAP.put(GREATER_THAN, GT);
		REL_OPS_MAP.put(GREATER_EQUALS, GE);
	};

	/**
	 * Parse an expression.
	 * 
	 *            the initial token.
	 * @return the root of the generated parse subtree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	private ExprNode parseExpression() throws Exception {
		// Parse a simple expression and make the root of its tree
		// the root node.
		ExprNode rootNode = parseSimpleExpression();
		Token token = currentToken(); // operator token
		TokenType tokenType = token.getType();
		// Look for a relational operator.
		if (REL_OPS.contains(tokenType)) {
			match(tokenType); // consume the operator

			// Parse the second simple expression. The operator node adopts
			// the simple expression's tree as its second child.
			ExprNode rightNode = parseSimpleExpression();

			// Create a new operator node and adopt the current tree
			// as its first child.
			ExprNode opNode = null;
			if(tokenType == EQUALS){
				opNode = new EqualToNode(rootNode, token, rightNode); //TODO binary operation
			}else if(tokenType == NOT_EQUALS){
				opNode = new NotEqualToNode(token); //TODO
			}else if (tokenType == LESS_THAN){
				opNode = new LessThanNode(token); //TODO
			}else if(tokenType == LESS_EQUALS){
				opNode = new LessThanEqualToNode(token); //TODO
			}else if(tokenType == GREATER_THAN){
				opNode = new GreaterThanNode(token); //TODO
			}else if(tokenType == GREATER_EQUALS){
				opNode = new GreaterThanEqualToNode(token); //TODO
			}

			// The operator node becomes the new root node.
			rootNode = opNode;
		}
		return rootNode;
	}

	// Set of additive operators.
	private static final EnumSet<JavaTokenType> ADD_OPS = EnumSet.of(PLUS,
			MINUS, JavaTokenType.OR);
	// Map additive operator tokens to node types.
	private static final HashMap<JavaTokenType, ICodeNodeTypeImpl> ADD_OPS_OPS_MAP = new HashMap<JavaTokenType, ICodeNodeTypeImpl>();
	static {
		ADD_OPS_OPS_MAP.put(PLUS, ADD);
		ADD_OPS_OPS_MAP.put(MINUS, SUBTRACT);
		ADD_OPS_OPS_MAP.put(JavaTokenType.OR, ICodeNodeTypeImpl.OR);
	};

	/**
	 * Parse a simple expression.
	 * 
	 *            the initial token.
	 * @return the root of the generated parse subtree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	private ExprNode parseSimpleExpression() throws Exception {
		Token token = currentToken();
		TokenType signType = null; // type of leading sign (if any)
		// Look for a leading + or - sign.
		TokenType tokenType = token.getType();
		if ((isCurrentToken(PLUS)) || (isCurrentToken(MINUS))) {
			signType = tokenType;
			match(tokenType); // consume the + or -
		}
		// Parse a term and make the root of its tree the root node.
		token = currentToken();
		ExprNode rootNode = parseTerm();
		// Was there a leading - sign?
		if (signType == MINUS) {
			// Create a NEGATE node and adopt the current tree
			// as its child. The NEGATE node becomes the new root node.
			ExprNode negateNode = new NegateNode(token, rootNode);
			rootNode = negateNode;
		}
		token = currentToken(); // operator token
		tokenType = token.getType();
		// Loop over additive operators.
		while (ADD_OPS.contains(tokenType)) {
			match(tokenType); // consume the add operators

			// Parse another term. The operator node adopts
			// the term's tree as its second child.
			ExprNode rightNode = parseTerm();

			// Create a new operator node and adopt the current tree
			// as its first child.
			ExprNode opNode = null;
			if(tokenType == PLUS){
				opNode = new AddNode(rootNode, token,rightNode);
			}else if(tokenType == MINUS){
				opNode = new SubstractNode(token); //TODO
			}

			// The operator node becomes the new root node.
			rootNode = opNode;
			token = currentToken();
			tokenType = token.getType();
		}
		return rootNode;
	}

	// Set of multiplicative operators.
	private static final EnumSet<JavaTokenType> MULT_OPS = EnumSet.of(STAR,
			SLASH, DIV, JavaTokenType.MOD, JavaTokenType.AND);
	// Map multiplicative operator tokens to node types.
	private static final HashMap<JavaTokenType, ICodeNodeType> MULT_OPS_OPS_MAP = new HashMap<JavaTokenType, ICodeNodeType>();
	static {
		MULT_OPS_OPS_MAP.put(STAR, MULTIPLY);
		MULT_OPS_OPS_MAP.put(SLASH, FLOAT_DIVIDE);
		MULT_OPS_OPS_MAP.put(DIV, INTEGER_DIVIDE);
		MULT_OPS_OPS_MAP.put(JavaTokenType.MOD, ICodeNodeTypeImpl.MOD);
		MULT_OPS_OPS_MAP.put(JavaTokenType.AND, ICodeNodeTypeImpl.AND);
	};

	/**
	 * Parse a term.
	 * 
	 *            the initial token.
	 * @return the root of the generated parse subtree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	private ExprNode parseTerm() throws Exception {

		// Parse a factor and make its node the root node.
		ExprNode rootNode = parseFactor();
		Token token = currentToken(); // operator token
		TokenType tokenType = token.getType();
		// Loop over multiplicative operators.
		while (MULT_OPS.contains(tokenType)) {
			match(tokenType); // consume operator token

			// Parse another factor. The operator node adopts
			// the term's tree as its second child.
			ExprNode righNode = parseFactor();

			// Create a new operator node and adopt the current tree
			// as its first child.
			ExprNode opNode = null;
			if(tokenType == STAR){
				opNode = new MulNode(token); //TODO
			}else if(tokenType == SLASH){
				opNode = new FloatDivNode(token); //TODO
			}else if(tokenType == DIV){
				opNode = new IntDivNode(token); //TODO
			}else if(tokenType == JavaTokenType.MOD){
				opNode = new ModNode(token); //TODO
			}else if(tokenType == JavaTokenType.AND){
				opNode = new AndNode(token); //TODO
			}

			// The operator node becomes the new root node.
			rootNode = opNode;
			token = currentToken();
			tokenType = token.getType();
		}
		return rootNode;
	}

	/**
	 * Parse a factor.
	 * 
	 *            the initial token.
	 * @return the root of the generated parse subtree.
	 * @throws Exception
	 *             if an error occurred.
	 */
	private ExprNode parseFactor() throws Exception {
		TokenType tokenType = currentToken().getType();
		Token token = currentToken();
		ExprNode rootNode = null;
		switch ((JavaTokenType) tokenType) {
		case IDENTIFIER: {
			// Look up the identifier in the symbol table stack.
			// Flag the identifier as undefined if it's not found.
//			String name = token.getText().toLowerCase();
//			SymTabEntry id = symTabStack.lookup(name);
//			if (id == null) {
//				errorHandler.flag(token, IDENTIFIER_UNDEFINED, this);
//				id = symTabStack.enterLocal(name);
//			}
			rootNode = new VariableNode(token);
//			rootNode.setAttribute(ID, id);
//			id.appendLineNumber(token.getLineNumber());
			match(IDENTIFIER); // consume the identifier
			break;
		}
		case INTEGER: {
			// Create an INTEGER_CONSTANT node as the root node.
			rootNode = new IntConstNode(token);
//			rootNode.setAttribute(VALUE, token.getValue());
			match(INTEGER); // consume the number
			break;
		}
		case REAL: {
			// Create an REAL_CONSTANT node as the root node.
			rootNode = new ReadConstNode(token);
//			rootNode.setAttribute(VALUE, token.getValue());
			match(REAL); // consume the number
			break;
		}
		case STRING: {
			String value = (String) token.getValue();
			// Create a STRING_CONSTANT node as the root node.
			rootNode = new StringConstNode(token);
//			rootNode.setAttribute(VALUE, value);
			match(STRING); // consume the string
			break;
		}
		case NOT: {
			// token is NOT token
			match(NOT); // consume the NOT token

			// Parse the factor. The NOT node adopts the
			// factor node as its child.
			ExprNode childNode = parseFactor();

			// Create a NOT node as the root node.
			rootNode = new NotNode(token, childNode);
			match(NOT);// consume the NOT
			break;
		}
		case LEFT_PAREN: {
			match(LEFT_PAREN); // consume the (
			// Parse an expression and make its node the root node.
			rootNode = parseExpression();
			// Look for the matching ) token.
			token = currentToken();
			if (isCurrentToken(RIGHT_PAREN)) {
				match(RIGHT_PAREN); // consume the )
			} else {
				errorHandler.flag(token, MISSING_RIGHT_PAREN, this);
			}
			break;
		}
		default: {
			errorHandler.flag(token, UNEXPECTED_TOKEN, this);
			break;
		}
		}
		return rootNode;
	}
}

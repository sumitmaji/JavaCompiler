package com.sum.frontend.java;

import com.sum.frontend.TokenType;

import java.util.HashSet;
import java.util.Hashtable;

public enum JavaTokenType implements TokenType {

	// Reserved words.
	CLASS, PUBLIC, STATIC, VOID, ENUM, IMPLEMENTS, INTERFACE, ABSTRACT,
	IMPORT, PACKAGE,BEGIN, END, OR, NOT, DIV, MOD, AND, EXTENDS,MAIN,
	WHILE,

	// Special symbols.
	PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), COLON_EQUALS(":="), DOT("."), COMMA(
			","), SEMICOLON(";"), COLON(":"), QUOTE("'"), EQUALS("="), NOT_EQUALS(
			"<>"), LESS_THAN("<"), LESS_EQUALS("<="), GREATER_EQUALS(">="), GREATER_THAN(
			">"), LEFT_PAREN("("), RIGHT_PAREN(")"), LEFT_BRACKET("["), RIGHT_BRACKET(
			"]"), LEFT_BRACE("{"), RIGHT_BRACE("}"), UP_ARROW("^"), DOT_DOT(
			".."),

	IDENTIFIER, INTEGER, INT, REAL, STRING, COMPOUND, ERROR, END_OF_FILE;
	private static final int FIRST_RESERVED_INDEX = CLASS.ordinal();
	private static final int LAST_RESERVED_INDEX = WHILE.ordinal();
	private static final int FIRST_SPECIAL_INDEX = PLUS.ordinal();
	private static final int LAST_SPECIAL_INDEX = DOT_DOT.ordinal();
	private String text; // token text

	/**
	 * Constructor.
	 */
	JavaTokenType() {
		this.text = this.toString().toLowerCase();
	}

	JavaTokenType(String text) {
		this.text = text;
	}

	/**
	 * Getter.
	 * 
	 * @return the token text.
	 */
	public String getText() {
		return text;
	}

	// Set of lower-cased Pascal reserved word text strings.
	public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
	static {
		JavaTokenType values[] = JavaTokenType.values();
		for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
			RESERVED_WORDS.add(values[i].getText().toLowerCase());
		}
	}
	// Hash table of Pascal special symbols. Each special symbol's text
	// is the key to its Pascal token type.
	public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS = new Hashtable<String, JavaTokenType>();
	static {
		JavaTokenType values[] = JavaTokenType.values();
		for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
			SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
		}
	}
}

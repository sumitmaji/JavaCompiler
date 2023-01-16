package com.sum.frontend.java;

import com.sum.frontend.EofToken;
import com.sum.frontend.Scanner;
import com.sum.frontend.Source;
import com.sum.frontend.Token;
import com.sum.frontend.java.tokens.*;

import static com.sum.frontend.Source.EOF;
import static com.sum.frontend.java.JavaErrorCode.INVALID_CHARACTER;
import static com.sum.frontend.java.JavaTokenType.END_OF_FILE;

/**
 * <h1>PascalScanner</h1>
 * 
 * <p>
 * The Pascal scanner.
 * </p>
 */
public class JavaScanner extends Scanner {
	/**
	 * Constructor
	 * 
	 * @param source
	 *            the source to be used with this scanner.
	 */
	public JavaScanner(Source source) {
		super(source);
	}

	/**
	 * Extract and return the next Pascal token from the source.
	 * 
	 * @return the next token.
	 * @throws Exception
	 *             if an error occurred.
	 */
	protected Token extractToken() throws Exception {

		skipWhiteSpace();
		Token token;
		char currentChar = currentChar();
		// Construct the next token. The current character determines the
		// token type.
		if (currentChar == EOF) {
			token = new EofToken(source, END_OF_FILE);
		}else if (Character.isDigit(currentChar)) {
			token = new JavaNumberToken(source);
		}
		else if (currentChar == '\'') {
		token = new JavaStringToken(source);
		} 
		else if (Character.isLetter(currentChar)) {
			token = new JavaWordToken(source);
		}else if(JavaTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))){
			token = new JavaSymbolToken(source);
		}
		else {
			token = new JavaErrorToken(source, INVALID_CHARACTER,
					Character.toString(currentChar));
			nextChar(); // consume character
		}
		return token;
	}

	/**
	 * Skip whitespace characters by consuming them. A comment is whitespace.
	 * 
	 * @throws Exception
	 *             if an error occurred.
	 */
	private void skipWhiteSpace() throws Exception {
		char currentChar = currentChar();
		while (Character.isWhitespace(currentChar) || currentChar == '/') {

				if(currentChar == '/'){
					if(peekChar() == '*'){
						nextChar(); // consume /
						currentChar = nextChar(); // consume *
						while(true){
							if(currentChar == '*' && peekChar() == '/'){
								nextChar(); // consume *
								currentChar = nextChar(); // consume /
								break;
							}
							currentChar = nextChar();
						}
					}else{
						return;
					}
				}else {
					currentChar = nextChar(); // consume whitespace character
				}
		}
	}
}
package com.sum.frontend;

import static com.sum.frontend.ParsingStrategy.MULTIPLE_LOOKAHEAD;
import static com.sum.frontend.ParsingStrategy.SINGLE_LOOKAHEAD;

/**
 * <h1>Scanner</h1>
 * 
 * <p>
 * A language-independent framework class. This abstract scanner class will be
 * implemented by language-specific subclasses.
 * </p>
 */
public abstract class Scanner {
	protected Source source; // source
	private Token currentToken; // current token

	private ParsingStrategyI parsingStrategy;

	/**
	 * Constructor
	 * 
	 * @param source
	 *            the source to be used with this scanner.
	 */
	public Scanner(Source source) {
		this.source = source;
		if(SINGLE_LOOKAHEAD.getType().equals(System.getProperty("parser.type"))){
			parsingStrategy = new SingleLookaheadParsingStrategyImpl(this);
		}else if(MULTIPLE_LOOKAHEAD.getType().equals(System.getProperty("parser.type"))){
			parsingStrategy = new MultipleLookaheadParsingStrategyImpl(this);
		}

	}

	/**
	 * @return the current token.
	 */
	public Token currentToken() {

		if(parsingStrategy == null)
			return currentToken;
		else
			return parsingStrategy.getCurrentToken();
	}

	/**
	 * Return next token from the source.
	 * 
	 * @return the next token.
	 * @throws Exception
	 *             if an error occurred.
	 */
	Token nextToken() throws Exception {
		currentToken = extractToken();
		return currentToken;
	}

	/**
	 * Do the actual work of extracting and returning the next token from the
	 * source. Implemented by scanner subclasses.
	 * 
	 * @return the next token.
	 * @throws Exception
	 *             if an error occurred.
	 */
	protected abstract Token extractToken() throws Exception;

	/**
	 * Call the source's currentChar() method.
	 * 
	 * @return the current character from the source.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public char currentChar() throws Exception {
		return source.currentChar();
	}

	/**
	 * Call the source's nextChar() method.
	 * 
	 * @return the next character from the source.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public char nextChar() throws Exception {
		return source.nextChar();
	}

	public char peekChar() throws Exception{
		return source.peekChar();
	}

	public void consume() throws Exception{
		parsingStrategy.consume();
	}

	public Token match(TokenType type) throws Exception{
		return parsingStrategy.match(type);
	}

	public TokenType LA(int i) throws Exception {
		return parsingStrategy.LA(i);
	}

	public Token LT(int i) throws Exception {
		return parsingStrategy.LT(i);
	}

	public boolean isCurrentToken(TokenType type){
		return parsingStrategy.checkCurrentTokenIs(type);
	}
}
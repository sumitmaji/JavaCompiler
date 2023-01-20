package com.sum.frontend.java;

import com.sum.frontend.Parser;
import com.sum.frontend.Scanner;
import com.sum.frontend.Token;
import com.sum.frontend.java.parser.StatementParser;
import com.sum.intermediatei.ast.Node;
import com.sum.message.Message;

import static com.sum.frontend.java.JavaErrorCode.*;
import static com.sum.frontend.java.JavaTokenType.BEGIN;
import static com.sum.frontend.java.JavaTokenType.DOT;
import static com.sum.message.MessageType.PARSER_SUMMARY;

/**
 * <h1>PascalParserTD</h1>
 *
 * <p>
 * The top-down Pascal parser.
 * </p>
 */
public class JavaParserTD extends Parser {
    /**
     * Constructor.
     *
     * @param scanner the scanner to be used with this parser.
     */
    public JavaParserTD(Scanner scanner) {
        super(scanner);
    }

    public JavaParserTD(JavaParserTD parent) {
        super(parent.getScanner());
    }

    protected static JavaErrorHandler errorHandler = new JavaErrorHandler();

    /**
     * Parse a Pascal source program and generate the symbol table and the
     * intermediate code.
     */
    public void parse() throws Exception {
        long startTime = System.currentTimeMillis();

        try {

            Token token = currentToken();
            Node rootNode = null;
            // Look for the BEGIN token to parse a compound statement.
            if (token.getType() == BEGIN) {
                StatementParser statementParser = new StatementParser(this);
                rootNode = statementParser.parse(token);
                token = currentToken();
            } else {
                errorHandler.flag(token, UNEXPECTED_TOKEN, this);
            }
            // Look for the final period.
            if (token.getType() != DOT) {
                errorHandler.flag(token, MISSING_PERIOD, this);
            }
            token = currentToken();

            // Set the parse tree root node.
            if (rootNode != null) {
                ast.setRoot(rootNode);
            }
            // Send the parser summary message.
            float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
            sendMessage(new Message(PARSER_SUMMARY, new Number[]{
                    currentToken().getLineNumber(), getErrorCount(), elapsedTime}));
        } catch (java.io.IOException ex) {
            errorHandler.abortTranslation(IO_ERROR, this);
        }
    }

    /**
     * Return the number of syntax errors found by the parser.
     *
     * @return the error count.
     */
    public int getErrorCount() {
        return errorHandler.getErrorCount();
    }
}
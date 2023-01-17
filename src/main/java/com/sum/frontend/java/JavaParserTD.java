package com.sum.frontend.java;

import com.sum.frontend.Parser;
import com.sum.frontend.Scanner;
import com.sum.frontend.java.parser.ClassParser;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediate.SymTabEntry;
import com.sum.message.Message;

import static com.sum.frontend.java.JavaErrorCode.IO_ERROR;
import static com.sum.frontend.java.JavaTokenType.*;
import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.OBJECT;
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

            SymTabEntry objectId = symTabStack.lookup("object");
            if (objectId == null) {
                objectId = symTabStack.enterLocal("object");
            }

            ICodeNode iCodeNode = ICodeFactory.lookupICodeNode(iCode.getRoot(), objectId.getName());
            if (iCodeNode == null) {
                ICodeNode rootNode = ICodeFactory.createICodeNode(OBJECT);
                rootNode.setAttribute(ID, objectId);
                iCode.setRoot(rootNode);
            }

            while (!isCurrentToken(CLASS) && !isCurrentToken(INTERFACE) && !isCurrentToken(ENUM)) {
                consume();
            }

            consume(); //consume the class token
            ClassParser classParser = new ClassParser(this);
            classParser.parse(iCode);

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
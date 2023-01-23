package com.sum.frontend;

import com.sum.intermediate.SymTabFactory;
import com.sum.intermediate.SymTabStack;
import com.sum.intermediatei.ast.Ast;
import com.sum.intermediatei.ast.NodeFactory;
import com.sum.intermediatei.sym.ScopeFactory;
import com.sum.intermediatei.sym.ScopeTreeImpl;
import com.sum.message.Message;
import com.sum.message.MessageHandler;
import com.sum.message.MessageListener;
import com.sum.message.MessageProducer;

/**
 * <h1>Parser</h1>
 *
 * <p>
 * A language-independent framework class. This abstract parser class will be
 * implemented by language-specific subclasses.
 * </p>
 */
public abstract class Parser implements MessageProducer {
    protected static SymTabStack symTabStack; // generated symbol table
    protected static ScopeTreeImpl scopeTree;
    protected static Ast ast; // intermediate code generated by this parser
    protected static MessageHandler messageHandler; // message handler delegate

    static {
        symTabStack = SymTabFactory.createSymTabStack();
        scopeTree = ScopeFactory.createScopeTree();
        messageHandler = new MessageHandler();
        ast = NodeFactory.createAst();
    }

    public ScopeTreeImpl getScopeTree() {
        return scopeTree;
    }

    protected Scanner scanner; // scanner used with this parser

    /**
     * Constructor.
     *
     * @param scanner the scanner to be used with this parser.
     */
    protected Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Parse a source program and generate the intermediate code and the symbol
     * table. To be implemented by a language-specific parser subclass.
     *
     * @throws Exception if an error occurred.
     */
    public abstract void parse() throws Exception;

    /**
     * Return the number of syntax errors found by the parser. To be implemented
     * by a language-specific parser subclass.
     *
     * @return the error count.
     */

    public abstract int getErrorCount();

    /**
     * Call the scanner's currentToken() method.
     *
     * @return the current token.
     */
    public Token currentToken() {
        return scanner.currentToken();
    }

    public Ast getAst(){
        return ast;
    }

    /**
     * Add a parser message listener.
     *
     * @param listener the message listener to add.
     */
    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    /**
     * Remove a parser message listener.
     *
     * @param listener the message listener to remove.
     */
    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    /**
     * Notify listeners after setting the message.
     *
     * @param message the message to set.
     */
    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

    public static SymTabStack getSymTabStack() {
        return symTabStack;
    }

    public static void setSymTabStack(SymTabStack symTabStack) {
        Parser.symTabStack = symTabStack;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void consume() throws Exception {
        scanner.consume();
    }

    public Token match(TokenType type) throws Exception {
        return scanner.match(type);
    }

    public TokenType LA(int i) throws Exception {
        return scanner.LA(i);
    }

    public Token LT(int i) throws Exception {
        return scanner.LT(i);
    }

    public boolean isCurrentToken(TokenType type) {
        return scanner.isCurrentToken(type);
    }
}
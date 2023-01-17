package com.sum.frontend;

public class SingleLookaheadParsingStrategyImpl implements ParsingStrategyI{
    private Parser parser;
    private Scanner scanner;
    private Token  lookaheadToken;
    public SingleLookaheadParsingStrategyImpl(Parser parser, Scanner scanner) {
        this.parser = parser;
        this.scanner = scanner;
        try {
            lookaheadToken = scanner.nextToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void consume() throws Exception {
        lookaheadToken = scanner.nextToken();
    }

    @Override
    public void match(TokenType type) throws Exception {
        if(lookaheadToken.getType() == type) consume();
        else throw new Exception("Expecting " + type + " but found " + lookaheadToken.getType());
    }

    @Override
    public TokenType LA(int i) throws Exception {
        throw new Exception("No implementation provided");
    }

    @Override
    public Token LT(int i) throws Exception {
        throw new Exception("No implementation provided");
    }

    @Override
    public Token getCurrentToken() {
        return scanner.currentToken();
    }
}

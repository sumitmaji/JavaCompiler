package com.sum.frontend;

public class SingleLookaheadParsingStrategyImpl implements ParsingStrategyI {
    private Scanner scanner;
    private Token lookaheadToken;

    public SingleLookaheadParsingStrategyImpl(Scanner scanner, Source source) {
        this.scanner = scanner;
        try {
            lookaheadToken = scanner.nextToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Token consume() throws Exception {
        lookaheadToken = scanner.nextToken();
        return lookaheadToken;
    }

    @Override
    public Token match(TokenType type) throws Exception {
        if (lookaheadToken.getType() == type) {
            Token currentToken = lookaheadToken;
            consume();
            return currentToken;
        }
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
    public Token currentToken() throws Exception {
        return lookaheadToken;
    }

    @Override
    public Token nextToken() throws Exception {
        consume();
        return lookaheadToken;
    }

    @Override
    public Token getCurrentToken() {
        return lookaheadToken;
    }
}

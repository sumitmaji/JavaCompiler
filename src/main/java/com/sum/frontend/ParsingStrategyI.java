package com.sum.frontend;

public interface ParsingStrategyI {
    void consume() throws Exception;
    Token match(TokenType type) throws Exception;

    Token getCurrentToken();
    default boolean checkCurrentTokenIs(TokenType type){
        return getCurrentToken().getType() == type;
    }

    TokenType LA(int i) throws Exception;

    Token LT(int i) throws Exception;

    public Token currentToken() throws Exception;

    public Token nextToken() throws Exception;
}

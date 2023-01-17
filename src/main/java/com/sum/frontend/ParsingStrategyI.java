package com.sum.frontend;

public interface ParsingStrategyI {
    void consume() throws Exception;
    void match(TokenType type) throws Exception;

    TokenType LA(int i) throws Exception;

    Token LT(int i) throws Exception;
}

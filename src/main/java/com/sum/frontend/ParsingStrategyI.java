package com.sum.frontend;

public interface ParsingStrategyI {
    void consume() throws Exception;
    void match(TokenType type) throws Exception;
}

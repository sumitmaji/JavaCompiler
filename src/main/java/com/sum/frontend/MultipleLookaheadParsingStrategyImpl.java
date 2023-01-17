package com.sum.frontend;

public class MultipleLookaheadParsingStrategyImpl implements ParsingStrategyI {
    private Parser parser;
    private Scanner scanner;
    private Token[] lookaheadToken; //circular lookahead buffer
    private int k; //how many lookahead symbol
    int p = 0; //circular index of next token position to fill

    public MultipleLookaheadParsingStrategyImpl(Parser parser, Scanner scanner) {
        this.parser = parser;
        this.scanner = scanner;
        try {
            lookaheadToken = new Token[k];
            for (int i = 1; i <= k; i++) consume();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void consume() throws Exception {
        lookaheadToken[p] = scanner.nextToken();
        p = (p + 1) % k;
    }

    public Token LT(int i){
        return lookaheadToken[(p + i -1)%k];
    }

    public TokenType LA(int i){
        return LT(i).getType();
    }
    @Override
    public void match(TokenType type) throws Exception {
        if (LA(1) == type) consume();
        else throw new Exception("Expecting " + type + " but found " + LA(1));
    }

    @Override
    public Token getCurrentToken() {
        return scanner.currentToken();
    }
}

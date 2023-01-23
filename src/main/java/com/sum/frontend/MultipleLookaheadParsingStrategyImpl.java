package com.sum.frontend;

public class MultipleLookaheadParsingStrategyImpl implements ParsingStrategyI {
    private Scanner scanner;
    private Token[] lookaheadToken; //circular lookahead buffer
    private int k = 3; //how many lookahead symbol
    int p = 0; //circular index of next token position to fill

    public MultipleLookaheadParsingStrategyImpl(Scanner scanner) {
        this.scanner = scanner;
        try {
            lookaheadToken = new Token[k];
            for (int i = 0; i < k; i++) consume();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * It adds the next token in the scanner at the end of the circular buffer which is pointed by p
     * then p index point to the current token being read by the parser
     * @throws Exception
     */
    @Override
    public void consume() throws Exception {
        lookaheadToken[p] = scanner.nextToken();
        p = (p + 1) % k;
    }

    public Token LT(int i){
        return lookaheadToken[(p + i -1)%k];
    }

    @Override
    public Token currentToken() throws Exception {
        return LT(1);
    }

    @Override
    public Token nextToken() throws Exception {
        consume();
        return LT(1);
    }

    public TokenType LA(int i){
        return LT(i).getType();
    }
    @Override
    public Token match(TokenType type) throws Exception {
        if (LA(1) == type) {
            Token currenToken = LT(1);
            consume();
            return currenToken;
        }
        else throw new Exception("Expecting " + type + " but found " + LA(1));
    }

    @Override
    public Token getCurrentToken() {
        return LT(1);
    }
}

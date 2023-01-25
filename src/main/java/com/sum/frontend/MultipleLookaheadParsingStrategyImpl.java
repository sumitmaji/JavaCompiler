package com.sum.frontend;

import com.sum.message.Message;
import com.sum.message.MessageType;

import static com.sum.message.MessageType.TOKEN;

public class MultipleLookaheadParsingStrategyImpl implements ParsingStrategyI {
    private Scanner scanner;
    private Source source;
    private Token[] lookaheadToken; //circular lookahead buffer
    private int k = 3; //how many lookahead symbol
    int p = 0; //circular index of next token position to fill

    public MultipleLookaheadParsingStrategyImpl(Scanner scanner, Source source) {
        this.scanner = scanner;
        this.source = source;
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
     *
     * @return
     * @throws Exception
     */
    @Override
    public Token consume() throws Exception {
        lookaheadToken[p] = scanner.nextToken();
        p = (p + 1) % k;
        return LT(1);
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
            Token currentToken = LT(1);
            consume();
            return currentToken;
        }
        else throw new Exception("Expecting " + type + " but found " + LA(1));
    }

    @Override
    public Token getCurrentToken() {
        return LT(1);
    }
}

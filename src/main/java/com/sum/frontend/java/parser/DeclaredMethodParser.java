package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.ast.Node;
import com.sum.intermediatei.sym.*;

import java.util.List;

import static com.sum.frontend.java.JavaTokenType.*;

public class DeclaredMethodParser extends DeclarationParser {
    public DeclaredMethodParser(JavaParserTD parent) {
        super(parent);
    }

    public void parser(Token token) throws Exception {

        Scope scope = null;
        if(LA(1) == IDENTIFIER){
            Symbol symbol = scopeTree.resolve(token.getText().toLowerCase());
            if(symbol != null && symbol instanceof Type){
                if(LA(2) == IDENTIFIER) {
                    scope = new MethodSymbol(LT(2).getText().toLowerCase(), (Type) symbol, scopeTree.currentScope);
                    match(IDENTIFIER);
                    match(IDENTIFIER);
                    scopeTree.push(scope);
                    List<Symbol> symbols = parseMethodParams(token);
                    ((MethodSymbol)scope).setArgs(symbols);
                }
            }else{
                scope = new ProgramSymbol(LT(1).getText().toLowerCase(), null, scopeTree.currentScope);
                match(IDENTIFIER);
                scopeTree.push(scope);
            }
        }else if(LA(1) == MAIN){
            scope = new MethodSymbol(LT(1).getText().toLowerCase(), null, scopeTree.currentScope);
            match(MAIN);
            scopeTree.push(scope);
            List<Symbol> symbols = parseMethodParams(token);
            ((MethodSymbol)scope).setArgs(symbols);
        }

        match(LEFT_BRACE);
        BlockParser blockParser = new BlockParser(this);
        Node rootNode = blockParser.parser(currentToken());
        match(RIGHT_BRACE);

        ((Symbol)scope).setNode(rootNode);
    }

    private List<Symbol> parseMethodParams(Token token) throws Exception {
        match(LEFT_PAREN);
        VariableDeclarationParser variableDeclarationParser = new VariableDeclarationParser(this);
        List<Symbol> symbols = variableDeclarationParser.parseSublist(token);
        match(RIGHT_PAREN);
        return symbols;
    }
}

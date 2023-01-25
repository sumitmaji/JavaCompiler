package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediatei.sym.Symbol;
import com.sum.intermediatei.sym.Type;
import com.sum.intermediatei.sym.VariableSymbol;

import java.util.ArrayList;
import java.util.List;

import static com.sum.frontend.java.JavaTokenType.*;

public class VariableDeclarationParser extends DeclarationParser {
    public VariableDeclarationParser(JavaParserTD parent) {
        super(parent);
    }

    public void parser(Token token) throws Exception {
        Symbol typeSymbol = scopeTree.resolve(token.getText().toLowerCase());
        match(IDENTIFIER);

        do{
            token = currentToken();
            new VariableSymbol(token.getText().toLowerCase(), (Type) typeSymbol, scopeTree.currentScope);
            match(IDENTIFIER);
            if(LA(1) == COMMA)
                match(COMMA);
        }while(LA(1) != SEMICOLON);

        match(SEMICOLON);
    }

    protected Type parseTypeSpecification(Token token) throws Exception {
        TypeSpecificationParser typeSpecificationParser = new TypeSpecificationParser(this);
        return typeSpecificationParser.parser(token);
    }

    public List<Symbol> parseSublist(Token token) throws Exception {

        List<Symbol> list = new ArrayList<>();
        while(LA(1) != RIGHT_PAREN){
            if(LA(1) == IDENTIFIER){
                TypeSpecificationParser typeSpecificationParser = new TypeSpecificationParser(this);
                Type type = typeSpecificationParser.parser(currentToken());

                Token identifierToken = match(IDENTIFIER);
                list.add(new VariableSymbol(identifierToken.getText().toLowerCase(), type, scopeTree.currentScope));
                if(LA(1) == COMMA)
                    match(COMMA);
            }
        }

        return list;
    }
}

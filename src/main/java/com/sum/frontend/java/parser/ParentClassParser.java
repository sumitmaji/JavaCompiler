package com.sum.frontend.java.parser;

import com.sum.Java;
import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.intermediate.ICode;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediate.SymTabEntry;

import java.io.File;

import static com.sum.frontend.java.JavaTokenType.*;
import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.CLASS_NAME;

public class ParentClassParser extends JavaParserTD {
    public ParentClassParser(JavaParserTD parent) {
        super(parent);
    }

    public void parse(ICodeNode childNode, ICode iCode) throws Exception {
        consume(); //consume extends
        Token token = currentToken();
        while((!isCurrentToken(IMPLEMENTS) && !isCurrentToken(EXTENDS) && !isCurrentToken(LEFT_BRACE))){
            if(token.getType() == COMMA){
                token = nextToken(); //consume ,
                continue;
            }

            if(token.getType() == LESS_THAN){
                token = nextToken();
                while(token.getType() != GREATER_THAN){
                    token = nextToken();
                }
                token = nextToken();
                continue;
            }

            String className = token.getText().toLowerCase();
            ICodeNode classNameNode = null;
            SymTabEntry classNameId = symTabStack.lookup(className);
            if(classNameId == null){
                Java.listFiles(new File("src/main/resources/files"), className);
                classNameId = symTabStack.lookup(className);
                if(classNameId == null) {
                    classNameId = symTabStack.enterLocal(className);
                    classNameId.appendLineNumber(token.getLineNumber());
                    classNameNode = ICodeFactory.createICodeNode(CLASS_NAME);
                    classNameNode.setAttribute(ID, classNameId);

                    ICodeNode object = ICodeFactory.lookupICodeNode(iCode.getRoot(), "object");
                    object.addChild(classNameNode);
                }else{
                    classNameNode = ICodeFactory.lookupICodeNode(iCode.getRoot(), classNameId.getName());
                }

            }else{
                classNameNode = ICodeFactory.lookupICodeNode(iCode.getRoot(), classNameId.getName());
            }

            classNameNode.addChild(childNode);
            token = nextToken(); //consume className
        }

        if(token.getType() == IMPLEMENTS || token.getType() == EXTENDS){
            ImplementsParser implementsParser = new ImplementsParser(this);
            implementsParser.parse(token, childNode, iCode);
        }
    }
}

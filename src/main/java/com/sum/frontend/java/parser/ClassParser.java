package com.sum.frontend.java.parser;

import com.sum.frontend.Token;
import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaTokenType;
import com.sum.intermediate.ICode;
import com.sum.intermediate.ICodeFactory;
import com.sum.intermediate.ICodeNode;
import com.sum.intermediate.SymTabEntry;

import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.ID;
import static com.sum.intermediate.icodeimpl.ICodeNodeTypeImpl.CLASS_NAME;

public class ClassParser extends JavaParserTD {
    public ClassParser(JavaParserTD parent) {
        super(parent);
    }


    public void parse(Token token, ICode iCode) throws Exception {
        ICodeNode rootNode = ICodeFactory.lookupICodeNode(iCode.getRoot(), "object");
        ICodeNode classNameNode = null;
        String className = token.getText().toLowerCase();
        SymTabEntry classNameId = symTabStack.lookup(className);
        if(classNameId == null){
            classNameId = symTabStack.enterLocal(className);
            classNameNode = ICodeFactory.createICodeNode(CLASS_NAME);
            classNameNode.setAttribute(ID, classNameId);
            classNameId.appendLineNumber(token.getLineNumber());
        }

        token = nextToken();

        switch ((JavaTokenType)token.getType()){
            case EXTENDS:
            case IMPLEMENTS:
                ParentClassParser parentClassParser = new ParentClassParser(this);
                parentClassParser.parse(token, classNameNode, iCode);
                break;
            default :
                rootNode.addChild(classNameNode);
        }

    }
}

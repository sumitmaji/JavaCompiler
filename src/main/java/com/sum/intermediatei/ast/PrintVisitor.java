package com.sum.intermediatei.ast;

import com.sum.intermediate.ICodeKey;
import com.sum.intermediate.SymTabEntry;
import com.sum.intermediate.icodeimpl.ICodeNodeImpl;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintVisitor implements NodeVisitor {

    private static final int INDENT_WIDTH = 4;
    private static final int LINE_WIDTH = 80;
    private PrintStream ps; // output print stream
    private int length; // output line length
    private String indent; // indent spaces
    private String indentation; // indentation of a line
    private StringBuilder line; // output line


    public PrintVisitor(PrintStream ps) {
        this.ps = ps;
        this.length = 0;
        this.indentation = "";
        this.line = new StringBuilder();
        // The indent is INDENT_WIDTH spaces.
        this.indent = "";
        for (int i = 0; i < INDENT_WIDTH; ++i) {
            this.indent += " ";
        }
    }

    /**
     * Append text to the output line.
     *
     * @param text the text to append.
     */
    private void append(String text) {
        int textLength = text.length();
        boolean lineBreak = false;
        // Wrap lines that are too long.
        if (length + textLength > LINE_WIDTH) {
            printLine();
            line.append(indentation);
            length = indentation.length();
            lineBreak = true;
        }
        // Append the text.
        if (!(lineBreak && text.equals(" "))) {
            line.append(text);
            length += textLength;
        }
    }

    /**
     * Print an output line.
     */
    private void printLine() {
        if (length > 0) {
            ps.println(line);
            line.setLength(0);
            length = 0;
        }
    }

    /**
     * Print a parse tree node.
     *
     * @param node the parse tree node.
     */
    private void printNodeWithChildBegin(Node node) {
        // Opening tag.
        append(indentation);
        append("<" + node.toString());
        append(">");
        printLine();
    }

    private void printNodeWithChildEnd(Node node) {
        // Opening tag.
        append(indentation);
        append("</" + node.toString() + ">");
        printLine();
    }

    private void printNode(Node node) {
        // Opening tag.
        append(indentation);
        append("<" + node.toString());
        append(" ");
        append("/>");
        printLine();
    }

    private void printTypeSpec(ICodeNodeImpl node) {
    }


    /**
     * Print a parse tree node's attributes.
     *
     * @param node the parse tree node.
     */
    private void printAttributes(ICodeNodeImpl node) {
        String saveIndentation = indentation;
        indentation += indent;
        Set<Map.Entry<ICodeKey, Object>> attributes = node.entrySet();
        Iterator<Map.Entry<ICodeKey, Object>> it = attributes.iterator();
        // Iterate to print each attribute.
        while (it.hasNext()) {
            Map.Entry<ICodeKey, Object> attribute = it.next();
            printAttribute(attribute.getKey().toString(), attribute.getValue());
        }
        indentation = saveIndentation;
    }

    /**
     * Print a node attribute as key="value".
     *
     * @param keyString the key string.
     * @param value     the value.
     */
    private void printAttribute(String keyString, Object value) {
        // If the value is a symbol table entry, use the identifier's name.
        // Else just use the value string.
        boolean isSymTabEntry = value instanceof SymTabEntry;
        String valueString = isSymTabEntry ? ((SymTabEntry) value).getName()
                : value.toString();
        String text = keyString.toLowerCase() + "=\"" + valueString + "\"";
        append(" ");
        append(text);
        // Include an identifier's nesting level.
        if (isSymTabEntry) {
            int level = ((SymTabEntry) value).getSymTab().getNestingLevel();
            printAttribute("LEVEL", level);
        }
    }

    /**
     * Print a parse tree node's child nodes.
     *
     * @param childNodes the array list of child nodes.
     */
    private void printChildNode(Node... childNodes) {
        String saveIndentation = indentation;
        indentation += indent;
        for (Node childNode : childNodes) {
            childNode.visit(this);
        }
        indentation = saveIndentation;
    }
    private void printChildNode(List<Node> childNodes) {
        String saveIndentation = indentation;
        indentation += indent;
        for (Node childNode : childNodes) {
            childNode.visit(this);
        }
        indentation = saveIndentation;
    }

    @Override
    public void visit(AddNode node) {
        printNodeWithChildBegin(node);
        printChildNode(node.left, node.right);
        printNodeWithChildEnd(node);
    }

    @Override
    public void visit(VariableNode node) {
        printNode(node);
    }

    @Override
    public void visit(AndNode andNode) {
        //TODO
    }

    @Override
    public void visit(AssignNode assignNode) {
        printNodeWithChildBegin(assignNode);
        printChildNode(assignNode.variableNode, assignNode.exprNode);
        printNodeWithChildEnd(assignNode);
    }

    @Override
    public void visit(BooleanConstNode booleanConstNode) {

    }

    @Override
    public void visit(CompoundNode compoundNode) {
        printNodeWithChildBegin(compoundNode);
        printChildNode(compoundNode.childNodes);
        printNodeWithChildEnd(compoundNode);
    }

    @Override
    public void visit(EqualToNode equalToNode) {
        printNodeWithChildBegin(equalToNode);
        printChildNode(equalToNode.left, equalToNode.right);
        printNodeWithChildEnd(equalToNode);
    }

    @Override
    public void visit(FieldNode fieldNode) {

    }

    @Override
    public void visit(FloatDivNode floatDivNode) {

    }

    @Override
    public void visit(GreaterThanEqualToNode greaterThanEqualToNode) {

    }

    @Override
    public void visit(GreaterThanNode greaterThanNode) {

    }

    @Override
    public void visit(IntConstNode intConstNode) {
        printNode(intConstNode);
    }

    @Override
    public void visit(IntDivNode intDivNode) {

    }

    @Override
    public void visit(LessThanEqualToNode lessThanEqualToNode) {

    }

    @Override
    public void visit(LessThanNode lessThanNode) {

    }

    @Override
    public void visit(ModNode modNode) {

    }

    @Override
    public void visit(MulNode mulNode) {

    }

    @Override
    public void visit(NegateNode negateNode) {

    }

    @Override
    public void visit(NoOpNode noOpNode) {

    }

    @Override
    public void visit(NotEqualToNode notEqualToNode) {

    }

    @Override
    public void visit(NotNode notNode) {

    }

    @Override
    public void visit(OrNode orNode) {

    }

    @Override
    public void visit(ReadConstNode readConstNode) {

    }

    @Override
    public void visit(StringConstNode stringConstNode) {

    }

    @Override
    public void visit(SubscriptNode subscriptNode) {

    }

    @Override
    public void visit(SubstractNode substractNode) {

    }

    @Override
    public void visit(VariableDeclNode variableDeclNode) {

    }
}

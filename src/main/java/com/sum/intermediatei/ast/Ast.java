package com.sum.intermediatei.ast;

public interface Ast{
    /**
     * Set and return the root node.
     *
     * @param node
     *            the node to set as root.
     * @return the root node.
     */
    public Node setRoot(Node node);

    /**
     * Get the root node.
     *
     * @return the root node.
     */
    public Node getRoot();
}

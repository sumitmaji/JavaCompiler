package com.sum.intermediate.icodeimpl;

import com.sum.intermediate.ICode;
import com.sum.intermediate.ICodeNode;

/**
 * <h1>ICodeImpl</h1>
 * 
 * <p>
 * An implementation of the intermediate code as a parse tree.
 * </p>
 */
public class ICodeImpl implements ICode {
	private ICodeNode root; // root node

	/**
	 * Set and return the root node.
	 * 
	 * @param node
	 *            the node to set as root.
	 * @return the root node.
	 */
	public ICodeNode setRoot(ICodeNode node) {
		root = node;
		return root;
	}

	@Override
	public ICodeNode getRoot() {
		return root;
	}
}
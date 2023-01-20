package com.sum.intermediatei.ast;

import com.sum.intermediate.ICodeNode;
import com.sum.intermediate.ICodeNodeType;
import com.sum.intermediate.icodeimpl.ICodeNodeImpl;

/**
 * <h1>ICodeFactory</h1>
 * 
 * <p>
 * A factory for creating objects that implement the intermediate code.
 * </p>
 */
public class NodeFactory {
	/**
	 * Create and return an intermediate code implementation.
	 * 
	 * @return the intermediate code implementation.
	 */
	public static Ast createAst() {
		return new AstImpl();
	}

	/**
	 * Create and return a node implementation.
	 * 
	 * @param type
	 *            the node type.
	 * @return the node implementation.
	 */
//	public static ICodeNode createICodeNode(ICodeNodeType type) {
//		return new ICodeNodeImpl(type);
//	}

}
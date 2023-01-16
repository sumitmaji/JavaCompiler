package com.sum.intermediate;

import com.sum.intermediate.icodeimpl.ICodeImpl;
import com.sum.intermediate.icodeimpl.ICodeKeyImpl;
import com.sum.intermediate.icodeimpl.ICodeNodeImpl;

import static com.sum.intermediate.icodeimpl.ICodeKeyImpl.ID;

/**
 * <h1>ICodeFactory</h1>
 * 
 * <p>
 * A factory for creating objects that implement the intermediate code.
 * </p>
 */
public class ICodeFactory {
	/**
	 * Create and return an intermediate code implementation.
	 * 
	 * @return the intermediate code implementation.
	 */
	public static ICode createICode() {
		return new ICodeImpl();
	}

	/**
	 * Create and return a node implementation.
	 * 
	 * @param type
	 *            the node type.
	 * @return the node implementation.
	 */
	public static ICodeNode createICodeNode(ICodeNodeType type) {
		return new ICodeNodeImpl(type);
	}

	public static ICodeNode lookupICodeNode(ICodeNode root, String className){
		while(root != null){
			if(root.getAttribute(ID) != null){
				SymTabEntry attribute = (SymTabEntry) root.getAttribute(ID);
				if(attribute.getName().equals(className)){
					return root;
				}else{
					for (ICodeNode child : root.getChildren()) {
						ICodeNode tmpNode = lookupICodeNode(child, className);
						if(tmpNode != null){
							return tmpNode;
						}
					}
					return null;
				}
			}
		}
		return null;
	}
}
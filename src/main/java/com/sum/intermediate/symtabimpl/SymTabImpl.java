package com.sum.intermediate.symtabimpl;

import com.sum.intermediate.SymTab;
import com.sum.intermediate.SymTabEntry;
import com.sum.intermediate.SymTabFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * <h1>SymTabImpl</h1>
 * 
 * <p>
 * An implementation of the symbol table.
 * </p>
 */
public class SymTabImpl extends TreeMap<String, SymTabEntry> implements SymTab {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4893479433108252001L;
	private int nestingLevel;

	public SymTabImpl(int nestingLevel) {
		this.nestingLevel = nestingLevel;
	}

	/**
	 * Create and enter a new entry into the symbol table.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the new entry.
	 */
	public SymTabEntry enter(String name) {
		SymTabEntry entry = SymTabFactory.createSymTabEntry(name, this);
		put(name, entry);
		return entry;
	}

	/**
	 * Look up an existing symbol table entry.
	 * 
	 * @param name
	 *            the name of the entry.
	 * @return the entry, or null if it does not exist.
	 */
	public SymTabEntry lookup(String name) {
		return get(name);
	}

	/**
	 * @return a list of symbol table entries sorted by name.
	 */
	public ArrayList<SymTabEntry> sortedEntries() {
		Collection<SymTabEntry> entries = values();
		Iterator<SymTabEntry> iter = entries.iterator();
		ArrayList<SymTabEntry> list = new ArrayList<SymTabEntry>(size());
		// Iterate over the sorted entries and append them to the list.
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list; // sorted list of entries
	}

	@Override
	public int getNestingLevel() {
		return nestingLevel;
	}
}
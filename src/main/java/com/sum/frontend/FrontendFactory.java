package com.sum.frontend;

import com.sum.frontend.java.JavaParserTD;
import com.sum.frontend.java.JavaScanner;

/**
 * <h1>FrontendFactory</h1>
 * 
 * <p>
 * A factory class that creates parsers for specific source languages.
 * </p>
 */
public class FrontendFactory {
	/**
	 * Create a parser.
	 * 
	 * @param language
	 *            the name of the source language (e.g., "Pascal").
	 * @param type
	 *            the type of parser (e.g., "top-down").
	 * @param source
	 *            the source object.
	 * @return the parser.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public static Parser createParser(String language, String type,
			Source source) throws Exception {
		if (language.equalsIgnoreCase("Java")
				&& type.equalsIgnoreCase("top-down")) {
			Scanner scanner = new JavaScanner(source);
			return new JavaParserTD(scanner);
		} else {
			throw new Exception("Parser factory: Invalid type '" + type + "'");
		}
	}
}
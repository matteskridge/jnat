package org.jnat.swing.editor.color;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public abstract class NTextColorParser {
	public static final String keyword = "#2B30BD";
	public static final String string = "#BD872B";
	public static final String variable = "#70156A";
	public static final String comment = "#999999";

	public abstract String parse(String text);
}

package org.jnat.swing.editor.color;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NTextColorXmlParser extends NTextColorParser {
	public String parse(String text) {
		text = parseTags(text);
		text = parseQuotes(text);
		return text;
	}

	public String parseTags(String text) {
		text = text.replaceAll("&lt;.*?&gt;", "<span style='color:"+keyword+";'>$0</span>");
		return text;
	}

	public String parseQuotes(String text) {
		text = text.replaceAll("&quot;.*?&quot;", "<span style='color:"+string+";'>$0</span>");
		text = text.replaceAll("\".*?\"", "<span style='color:"+string+";'>$0</span>");
		return text;
	}
}

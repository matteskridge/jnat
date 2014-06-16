package org.jnat.swing.editor.color;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NTextColorJavaParser extends NTextColorParser {
	public String parse(String text) {
		text = parseKeywords(text);
		text = parseQuotes(text);
		return text;
	}

	private String parseKeywords(String text) {
		String[] keywords = {"function", "class", "public", "private",
				"protected", "extends", "implements", "import",
				"package", "static", "void", "for", "if", "while",
				"for", "return", "int", "char", "long", "double",
				"float"};

		for (String keyword: keywords) {
			text = text.replaceAll(keyword+" ", "<span style='color:"+this.keyword+"'>$0</span>");
			//text = text.replaceAll("( |\t)"+keyword+"( |\t)", "<span style='color:"+this.keyword+"'>$0</span>");
		}

		return text;
	}

	public String parseQuotes(String text) {
		text = text.replaceAll("&quot;.*?&quot;", "<span style='color:"+string+";'>$0</span>");
		text = text.replaceAll("\".*?\"", "<span style='color:"+string+";'>$0</span>");
		return text;
	}
}

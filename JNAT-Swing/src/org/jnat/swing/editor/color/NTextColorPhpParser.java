package org.jnat.swing.editor.color;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NTextColorPhpParser extends NTextColorParser {
	public String parse(String text) {
		text = parseKeywords(text);
		text = parseQuotes(text);
		text = parseVariables(text);
		return text;
	}

	private String parseKeywords(String text) {
		String[] keywords = {"function", "class", "public", "private",
				"protected", "extends", "implements", "static",
				"for", "if", "while",
				"return", "echo", "eval", "exit", "namespace"};

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

	public String parseVariables(String text) {
		text = text.replaceAll("\\$([a-zA-Z0-9_]+)", "<span style='color:"+variable+";'>$0</span>");
		return text;
	}
}

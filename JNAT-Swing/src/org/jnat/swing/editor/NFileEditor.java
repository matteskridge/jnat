package org.jnat.swing.editor;

import org.jnat.io.NFile;

import java.io.File;
import java.io.FileWriter;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NFileEditor extends NTextEditor {
	private File file;

	public NFileEditor() {

	}

	public NFileEditor(File f) {
		file = f;

		// Read the provided file
		NFile improved = new NFile(file);
		String text = improved.read();

		detectStyling();

		if (text != null) {
			setText(text);
		}
	}

	private void detectStyling() {
		String text = file.getAbsolutePath();
		if (text.endsWith(".xml")) {
			setFormat("xml");
		} else if (text.endsWith(".yml")) {
			setFormat("yml");
		} else if (text.endsWith(".java")) {
			setFormat("java");
		} else if (text.endsWith(".cpp") || text.endsWith(".h")) {
			setFormat("cpp");
		} else if (text.endsWith(".php")) {
			setFormat("php");
		}
	}

	public void save() {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(getText());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFile(File f) {
		file = f;
	}

	public File getFile() {
		return file;
	}
}

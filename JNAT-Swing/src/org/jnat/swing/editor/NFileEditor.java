package org.jnat.swing.editor;

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
	}

	public void save() {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(getText());
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

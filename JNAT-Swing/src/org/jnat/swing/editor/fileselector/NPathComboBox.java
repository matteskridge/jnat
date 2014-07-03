package org.jnat.swing.editor.fileselector;

import org.jnat.swing.general.controls.NComboBox;

import java.io.File;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NPathComboBox extends NComboBox {
	private File file;

	public NPathComboBox(File file) {
		this.file = file;
	}
}

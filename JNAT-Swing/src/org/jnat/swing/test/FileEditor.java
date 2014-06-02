package org.jnat.swing.test;

import org.jnat.swing.NFrame;
import org.jnat.swing.editor.NFileChooser;
import org.jnat.swing.editor.NFileEditor;
import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.NAddTabEvent;
import org.jnat.swing.events.NSelectTabEvent;
import org.jnat.swing.toolbar.NToolbarButton;

import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class FileEditor extends NFrame {

	private NFileEditor selected;

	public FileEditor() {
		super("File Editor");

		// Initialize the toolbar
		addToolbar(new NToolbarButton("new", "New File"));
		addToolbar(new NToolbarButton("open", "Open"));
		addToolbar(new NToolbarButton("save", "Save"));

		// Configure GUI properties
		setTabsEnabled(true);
		setTabsClosable(true);
		setTabsDefaultShown(true);
		setTabsUserAddable(true);

		// Configure the start tab
		add(new NFileChooser(), BorderLayout.CENTER);

		// Finish showing the GUI
		setVisible(true);
	}

	public void eventOccurred(NEvent event) {
		if (event.getAction().equals("new") || (event instanceof NAddTabEvent)) {

			// Add a new tab when the user clicks a button
			addTab("New File", selected = new NFileEditor());

		} else if (event instanceof NSelectTabEvent) {

			// When the user changes tabs, take note of that
			Component selected = ((NSelectTabEvent) event).getComponent();
			if (selected instanceof NFileEditor) {
				NFileEditor editor = (NFileEditor)selected;
				this.selected = editor;
			}

		} else if (event.getAction().equals("save")) {

			// Process a save file request
			if (selected.getFile() == null) {

			} else {

			}

		}
	}

	public static void main(String[] args) {
		new FileEditor();
	}
}

package org.jnat.swing.test;

import org.jnat.swing.NFrame;
import org.jnat.swing.editor.fileselector.NFileChooser;
import org.jnat.swing.editor.NFileEditor;
import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.file.NFileSelectedEvent;
import org.jnat.swing.events.tab.NAddTabEvent;
import org.jnat.swing.events.tab.NSelectTabEvent;
import org.jnat.swing.toolbar.NToolbarButton;

import java.awt.*;
import java.io.File;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class FileEditor extends NFrame {

	private NFileEditor selected;

	public FileEditor() {
		super("JNAT File Editor");

		// Initialize the toolbar
		addToolbar(new NToolbarButton("new", "New File"));
		addToolbar(new NToolbarButton("open", "Open"));
		addToolbar(new NToolbarButton("save", "Save"));

		// Configure tab properties
		setTabsEnabled(true);
		setTabsClosable(true);
		setTabsDefaultShown(true);
		setTabsUserAddable(true);

		// Configure the start tab
		add(new NFileChooser(this), BorderLayout.CENTER);

		// Finish showing the GUI
		setVisible(true);
	}

	public void eventOccurred(NEvent event) {
		if (event.getAction().equals("new") || (event instanceof NAddTabEvent)) {

			// Add a new tab when the user clicks the add button
			addTab("New File", selected = new NFileEditor());

		} else if (event instanceof NSelectTabEvent) {

			// When the user changes tabs, store the current tab
			Component selected = ((NSelectTabEvent) event).getComponent();
			if (selected instanceof NFileEditor) {
				NFileEditor editor = (NFileEditor)selected;
				this.selected = editor;
			}

		} else if (event.getAction().equals("save")) {

			// Process a save file request
			if (selected.getFile() == null) {

			} else {
				selected.save();
			}

		} else if (event instanceof NFileSelectedEvent) {

			// When the user selects a file, add the tab
			File f = ((NFileSelectedEvent) event).getFile();
			addTab(f.getName(), selected = new NFileEditor(f));

		}
	}

	public static void main(String[] args) {
		new FileEditor();
	}
}

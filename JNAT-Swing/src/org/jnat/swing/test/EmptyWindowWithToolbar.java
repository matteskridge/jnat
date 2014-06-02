package org.jnat.swing.test;

import org.jnat.swing.NFrame;
import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.NEventListener;
import org.jnat.swing.events.NSearchEvent;
import org.jnat.swing.menu.NMenu;
import org.jnat.swing.menu.NMenuItem;
import org.jnat.swing.toolbar.NToolbarButton;
import org.jnat.swing.toolbar.NIcon;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class EmptyWindowWithToolbar implements NEventListener {

	private static NFrame frame;

	public static void main(String[] args) {
		// Create the GUI
		frame = new NFrame("Empty Window with Toolbar");

		// Initialize the toolbar
		frame.addToolbar(new NToolbarButton("new", "New File"));
		frame.addToolbar(new NToolbarButton("open", "Open"));
		frame.addToolbar(new NToolbarButton("refresh", new NIcon("Refresh")));
		frame.addToolbar(new NToolbarButton("add", new NIcon("Add")));
		frame.addToolbar(new NToolbarButton("share", "Share", new NIcon("Share")));

		// Set a few GUI properties
		frame.setSearchEnabled(true);

		// Initialize the menu
		NMenu file = new NMenu("App");
		file.add(new NMenuItem("exit", "Exit"));
		frame.addMenu(file);

		// Add a listener to the GUI
		frame.addEventListener(new EmptyWindowWithToolbar());

		// Finished creating the GUI and show it
		frame.setVisible(true);
	}

	public void eventOccurred(NEvent e) {
		if (e.getAction().equals("new")) {

		} else if (e.getAction().equals("open")) {

		} else if (e.getAction().equals("exit")) {
			System.exit(0);
		} else if (e instanceof NSearchEvent) {
			String query = ((NSearchEvent)e).getQuery();
			frame.showDialog(query);
		}
	}
}

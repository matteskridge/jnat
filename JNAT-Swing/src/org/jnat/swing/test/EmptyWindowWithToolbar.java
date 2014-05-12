package org.jnat.swing.test;

import org.jnat.swing.NFrame;
import org.jnat.swing.listeners.NEvent;
import org.jnat.swing.listeners.NEventListener;
import org.jnat.swing.menu.NMenu;
import org.jnat.swing.menu.NMenuItem;
import org.jnat.swing.toolbar.NToolbarButton;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class EmptyWindowWithToolbar implements NEventListener {
	public static void main(String[] args) {
		// Create the GUI
		NFrame frame = new NFrame("Empty Window with Toolbar");

		// Initialize the toolbar
		frame.addToolbar(new NToolbarButton("new", "New File"));
		frame.addToolbar(new NToolbarButton("open", "Open"));

		// Initialize the menu file menu
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

		}
	}
}

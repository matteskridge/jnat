package org.jnat.swing.menu;

import javax.swing.*;

/**
 * @author Matt Eskridge
 * @created 5/11/14
 */
public class NMenuItem extends JMenuItem {
	public NMenuItem(String action, String title) {
		super(title);
		setActionCommand(action);
	}
}

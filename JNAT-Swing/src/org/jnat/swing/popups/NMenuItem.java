package org.jnat.swing.popups;

import org.jnat.swing.toolbar.NIcon;

import javax.swing.*;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NMenuItem extends JMenuItem {
	public NMenuItem(String action, String text) {
		init(action,text, null);
	}

	public NMenuItem(String action, String text, NIcon icon) {
		init(action, text, icon);
	}

	private void init(String action, String text, NIcon icon) {
		setActionCommand(action);
		setText(text);
		if (icon != null) setIcon(icon.getIcon(36));
	}
}

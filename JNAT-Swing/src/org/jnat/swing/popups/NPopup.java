package org.jnat.swing.popups;

import org.jnat.swing.toolbar.NIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NPopup extends JPopupMenu implements ActionListener {
	public void addOption(String action, String text) {
		NMenuItem item = new NMenuItem(action,text);
		item.addActionListener(this);
		add(item);
	}

	public void addOption(String action, String text, NIcon icon) {
		NMenuItem item = new NMenuItem(action,text,icon);
		item.addActionListener(this);
		add(item);
	}

	public void trigger(String action) {

	}

	public void actionPerformed(ActionEvent e) {
		trigger(e.getActionCommand());
	}
}

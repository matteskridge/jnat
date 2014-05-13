package org.jnat.swing.menu;

import org.jnat.swing.NFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matt Eskridge
 * @created 5/11/14
 */
public class NMenuBar extends JMenuBar {
	private NFrame master;

	public NMenuBar(NFrame master) {
		this.master = master;
	}

	public JMenu add(JMenu menu) {
		for (int i = 0; i < menu.getItemCount(); i++) {
			final JMenuItem item = menu.getItem(i);
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					trigger(item.getActionCommand());
				}
			});
		}
		super.add(menu);
		return menu;
	}

	public void trigger(String text) {
		master.trigger(text);
	}
}

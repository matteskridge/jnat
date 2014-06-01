package org.jnat.swing.toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class NToolbarButton extends NToolbarGradientWidget {
	private String id;
	private String name;
	private int t = 3, s = 13, distance = 5;

	public NToolbarButton(String id, String name) {
		init(id, name, null);
	}

	public NToolbarButton(String id, String name, NIcon icon) {
		init(id, name, icon);
	}

	public NToolbarButton(String id, NIcon icon) {
		init(id, null, icon);
	}

	private void init(String id, String name, NIcon icon) {
		// Set a few variables
		this.id = id;
		this.name = name;

		// Set GUI properties
		setBorder(BorderFactory.createEmptyBorder(t,s,t+1,s));
		setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

		// If there is no icon, add a regular label
		if (icon == null) {
			JLabel label = new JLabel(name);
			label.addMouseListener(this);
			add(label);
		} else {
			int b = 1;

			JLabel label;
			if (name == null) {
				label = new JLabel();
			} else {
				b = 0;
				label = new JLabel(name);
			}

			label.addMouseListener(this);
			label.setBorder(BorderFactory.createEmptyBorder(b,b,b,b));
			label.setIcon(icon.getIcon(14));
			add(label);
		}
	}

	public void click() {
		trigger(id);
	}
}

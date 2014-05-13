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

	private void init(String id, String name, Image icon) {
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
		}
	}

	public void click() {
		trigger(id);
	}
}

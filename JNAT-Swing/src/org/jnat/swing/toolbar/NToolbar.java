package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.panels.NPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class NToolbar extends JPanel {

	private ArrayList<NToolbarWidget> items;
	private int b = 5, bottom = 2, left = 3, distance = 5;

	public NToolbar() {
		items = new ArrayList();

		if (JnatUtilities.isMac()) {
			setOpaque(false);
			setBorder(new CompoundBorder(
				BorderFactory.createMatteBorder(0,0,1,0,Color.decode("#888888")),
				BorderFactory.createEmptyBorder(2,b+left-distance,b+bottom,b)
			));
			setLayout(new FlowLayout(FlowLayout.LEFT,distance,0));
		}
	}

	public void addToolbarSeparator() {
	}

	public void addToolbar(NToolbarWidget widget) {
		items.add(widget);
		add(widget);
	}
}

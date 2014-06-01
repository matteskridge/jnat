package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.NFrame;
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
	private NFrame master;
	private NSearchBar search;
	private JPanel center;
	private JPanel right;
	private boolean searchShown = false;
	private int b = 5, bottom = 2, left = 3, distance = 5;

	public NToolbar(NFrame master) {
		// Initialize the variables
		this.items = new ArrayList();
		this.master = master;
		this.search = new NSearchBar();
		this.center = new JPanel();
		this.right = new JPanel();

		// Initialize the toolbar GUI
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#888888")),
				BorderFactory.createEmptyBorder(2, b + left - distance, b + bottom, b)
		));

		// Initialize the center panel
		if (JnatUtilities.isMac()) {
			center.setOpaque(false);
			center.setLayout(new FlowLayout(FlowLayout.LEFT, distance, 0));
		}

		// Initialize the right side
		search.setVisible(false);
		right.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		right.add(search);

		// Add the items to the GUI
		add(center, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
	}

	public void trigger(String event) {
		master.trigger(event);
	}

	public void addToolbarSeparator() {
	}

	public void addToolbar(NToolbarWidget widget) {
		widget.setToolbar(this);
		items.add(widget);
		center.add(widget);
	}

	public void setSearchEnabled(boolean enabled) {
		searchShown = enabled;
		search.setVisible(enabled);
	}
}

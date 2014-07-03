package org.jnat.swing.editor.fileselector;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.panels.NPanel;
import org.jnat.swing.toolbar.NSearchBar;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NFileControlBar extends NPanel {
	private int b = 5;
	private Color border = Color.decode("#898989");
	private Color background = Color.decode("#EDEDED");
	private NPanel left;
	private NPanel right;

	public NFileControlBar() {
		left = new NPanel();
		right = new NPanel();

		if (JnatUtilities.isMac()) {
			setLayout(new BorderLayout());
			setBackground(background);
			setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0,0,1,0,border),BorderFactory.createEmptyBorder(b,b,b,b)));

			right.add(new NSearchBar(this));

			add(left, BorderLayout.WEST);
			add(right, BorderLayout.EAST);
		} else {

		}
	}
}

package org.jnat.swing.editor.fileselector;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.general.controls.NButton;
import org.jnat.swing.panels.NPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NFileFinishBar extends NPanel {
	private int b = 5;
	private Color mac_border = Color.decode("#898989");
	private Color mac_background = Color.decode("#EDEDED");
	private NPanel right;

	public NFileFinishBar() {
		setLayout(new BorderLayout());

		if (JnatUtilities.isMac()) {
			setBackground(mac_background);
			setBorder(new CompoundBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, mac_border),BorderFactory.createEmptyBorder(b,b,b,b)));
		} else {

		}

		right = new NPanel();
		right.add(new NButton("Cancel"));
		right.add(new NButton("Open"));
		add(right, BorderLayout.EAST);
	}
}

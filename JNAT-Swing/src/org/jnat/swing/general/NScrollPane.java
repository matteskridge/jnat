package org.jnat.swing.general;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NScrollPane extends JScrollPane {
	public NScrollPane() {
		init();
	}

	public NScrollPane(Component center) {
		super(center);
		init();
	}

	private void init() {
		setOpaque(false);
		getViewport().setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		setViewportBorder(BorderFactory.createEmptyBorder());
		getVerticalScrollBar().setUnitIncrement(16);
	}
}

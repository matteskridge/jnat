package org.jnat.swing.general;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.panels.NPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NSourcePanel extends NPanel {
	public NSourcePanel() {
		if (JnatUtilities.isMac()) {
			setBackground(Color.decode("#e1e5eb"));
			setBorder(BorderFactory.createMatteBorder(0,0,0,2,Color.decode("#e3e7ec")));
		}
	}
}

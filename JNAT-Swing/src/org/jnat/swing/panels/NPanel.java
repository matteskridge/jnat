package org.jnat.swing.panels;

import org.jnat.swing.JnatUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by matt on 5/10/14.
 */
public class NPanel extends JPanel {
	public NPanel() {
		if (JnatUtilities.isMac()) {
			setBackground(Color.decode("#EDEDED"));
		}
	}
}

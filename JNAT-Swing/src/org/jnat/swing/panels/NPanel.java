package org.jnat.swing.panels;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.toolbar.NTabArea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by matt on 5/10/14.
 */
public class NPanel extends JPanel {
	private ArrayList<NameListener> nameListeners = new ArrayList();

	public NPanel() {
		if (JnatUtilities.isMac()) {
			setBackground(Color.decode("#EDEDED"));
		}
	}

	public void setName(String name) {
		super.setName(name);
		for (NameListener listen: nameListeners) {
			listen.nameChanged(this, name);
		}
	}

	public void addNameListener(NameListener listen) {
		nameListeners.add(listen);
	}

	public interface NameListener {
		public void nameChanged(NPanel panel, String name);
	}
}

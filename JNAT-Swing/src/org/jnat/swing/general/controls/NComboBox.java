package org.jnat.swing.general.controls;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NComboBox extends JComboBox {
	private DefaultComboBoxModel model;

	public NComboBox() {
		model = new DefaultComboBoxModel();
		setModel(model);
	}

	public void addItem(Object value) {
		model.addElement(value);
	}

	public void addItem(Object key, Object value) {
		model.addElement(value);
	}
}

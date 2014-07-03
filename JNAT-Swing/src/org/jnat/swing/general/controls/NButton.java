package org.jnat.swing.general.controls;

import org.jnat.swing.events.NEventListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 6/28/14
 */
public class NButton extends JButton implements ActionListener {
	private ArrayList<NEventListener> listeners;

	public NButton(String text) {
		super(text);
		listeners = new ArrayList();
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

	}
}

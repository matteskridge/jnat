package org.jnat.swing.editor;

import org.jnat.swing.general.NScrollPane;
import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.NEventListener;
import org.jnat.swing.panels.NPanel;
import org.jnat.swing.popups.NTextPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTextEditor extends NPanel implements MouseListener, NEventListener {
	private JEditorPane pane;
	private int b = 5;

	public NTextEditor() {
		setName("Text Editor");
		setLayout(new BorderLayout());

		pane = new JEditorPane();
		pane.setBorder(BorderFactory.createEmptyBorder(b,b,b,b));
		pane.setText("");
		pane.addMouseListener(this);

		add(new NScrollPane(pane), BorderLayout.CENTER);
	}

	public String getText() {
		return pane.getText();
	}

	public void eventOccurred(NEvent e) {
		if (e.getAction().equals("cut")) {
			pane.cut();
		} else if (e.getAction().equals("copy")) {
			pane.copy();
		} else if (e.getAction().equals("paste")) {
			pane.paste();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
			NTextPopup popup = new NTextPopup(this, pane.getSelectedText() != null);
			popup.show(this,e.getX(),e.getY());
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}

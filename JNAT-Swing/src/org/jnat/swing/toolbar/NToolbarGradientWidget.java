package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Matt Eskridge
 * @created 5/11/14
 */
public abstract class NToolbarGradientWidget extends NToolbarWidget implements MouseListener {
	private int round = 5;
	private boolean active = false;

	public NToolbarGradientWidget() {
		addMouseListener(this);
	}

	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;


		if (JnatUtilities.isMac()) {
			Color begin, end;

			if (active) {
				begin = Color.decode("#A1A1A1");
				end = Color.decode("#D4D4D4");
			} else {
				begin = Color.decode("#F6F6F6");
				end = Color.decode("#BDBDBD");
			}

			g.setColor(Color.decode("#DDDDDD"));
			g.fillRoundRect(0, 0, getWidth(), getHeight(), round, round);

			g.setPaint(new GradientPaint(0,0,begin,0,getHeight(),end));
			g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,round,round);

			g.setColor(Color.decode("#888888"));
			g.drawRoundRect(0,0,getWidth()-2,getHeight()-2,round,round);
		} else {

		}

		//super.paintComponent(g1);
	}

	public void mouseClicked(MouseEvent mouseEvent) {
		click();
	}

	public void mousePressed(MouseEvent mouseEvent) {
		active = true;
		repaint();
	}

	public void mouseReleased(MouseEvent mouseEvent) {
		active = false;
		repaint();
	}

	public void mouseEntered(MouseEvent mouseEvent) {

	}

	public void mouseExited(MouseEvent mouseEvent) {

	}

	public abstract void click();
}

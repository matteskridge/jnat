package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.panels.NPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTabArea extends JPanel implements MouseListener, MouseMotionListener, NPanel.NameListener {
	private NToolbar toolbar;
	private String selected;
	private String hover;
	private boolean closable;
	private boolean showDefault;
	private boolean userAddable;
	private ArrayList<NTab> tabs;
	private HashMap<Rectangle, String> clickboxes;
	private HashMap<Rectangle, String> closeboxes;
	private Rectangle addRect;
	private Rectangle homeRect;

	public NTabArea(NToolbar toolbar) {
		// Store some of the passed variables
		this.toolbar = toolbar;

		// Initialize some variables
		tabs = new ArrayList();
		hover = "";

		closable = false;
		showDefault = false;
		userAddable = false;

		// Initialize the GUI
		setOpaque(false);
		setPreferredSize(new Dimension(1, getOsHeight()));
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private int getOsHeight() {
		if (JnatUtilities.isMac()) {
			return 25;
		} else {
			return 20;
		}
	}

	public void tabSelected(String id) {
		selected = id;
		repaint();
	}

	public void tabAdded(String id, String name, Component comp, boolean select) {
		if (comp instanceof NPanel) {
			((NPanel)comp).addNameListener(this);
		}

		tabs.add(new NTab(id, name, comp));
		if (select) selected = id;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		clickboxes = new HashMap();
		closeboxes = new HashMap();

		if (JnatUtilities.isMac()) {
			paintMac((Graphics2D)g);
		}
	}

	public void paintMac(Graphics2D g) {
		int extrawidth = getExtraWidth();
		int size = (getWidth()-extrawidth)/tabs.size(), x = 0, y = 0;

		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		g.setRenderingHints(qualityHints);

		for (NTab tab: tabs) {
			boolean selected = (this.selected.equals(tab.id));

			// Highlight the selected tab
			if (selected) {
				g.setColor(Color.decode("#777777"));
				g.drawLine(0, y, x, y);
				g.drawLine(x+size,y,getWidth(),y);
			} else {
				g.setColor(new Color(0.1f,0.1f,0.1f,0.1f));
				g.fillRect(x, y, size, getHeight());
			}

			// Remember this area for user clicks
			clickboxes.put(new Rectangle(x,y,size,getHeight()), tab.id);

			// Draw the text
			g.setColor(Color.decode("#222222"));
			g.setFont(g.getFont().deriveFont(10f));
			g.drawString(tab.name, x+(size/2)-(g.getFontMetrics().stringWidth(tab.name)/2), y+17);

			if (closable && hover.equals(tab.id)) {
				int m = 5, r = 5, s = getHeight()-(m*2);

				g.setColor(Color.decode("#888888"));
				g.fillRoundRect(x+m-1, y+m-1, s+1, s+1, r, r);

				g.setColor(Color.decode("#999999"));
				g.fillRoundRect(x + m, y + m, s, s, r, r);

				g.setColor(Color.decode("#EEEEEE"));
				g.drawString("x", x+m+(s/2)-(g.getFontMetrics().stringWidth("x")/2),16);

				closeboxes.put(new Rectangle(x + m, y + m, s, s), tab.id);
			}

			// Draw the separator line
			g.setColor(Color.decode("#777777"));
			g.drawLine(x+size,y,x+size,getHeight());

			x += size;
		}

		size = getOsButtonWidth();

		if (userAddable) {
			g.setColor(Color.decode("#444444"));
			g.drawString("+", x+(size/2)-(g.getFontMetrics().stringWidth("+")/2), y+16);

			g.setColor(new Color(0.1f,0.1f,0.1f,0.1f));
			g.fillRect(x, y, size, getHeight());

			g.setColor(Color.decode("#777777"));
			g.drawLine(x + size, y, x + size, getHeight());

			addRect = new Rectangle(x, y, size, getHeight());
			x += getOsButtonWidth();
		}

		if (showDefault) {
			int w = getOsButtonWidth(), s = 10;

			g.setColor(Color.decode("#777777"));
			g.drawImage((new NIcon("Home")).getImage(s, JnatUtilities.getOperatingSystemColor()), x+(w/2)-(s/2), y+(getHeight()/2)-(s/2), s, s, null);

			if (!selected.equals("")) {
				g.setColor(new Color(0.1f,0.1f,0.1f,0.1f));
				g.fillRect(x, y, size, getHeight());
			} else {
				g.setColor(Color.decode("#777777"));
				g.drawLine(0, y, x, y);
			}

			g.setColor(Color.decode("#777777"));
			g.drawLine(x + size, y, x + size, getHeight());

			homeRect = new Rectangle(x, y, size, getHeight());
			x += getOsButtonWidth();
		}
	}

	private int getOsButtonWidth() {
		return 30;
	}

	private int getExtraWidth() {
		int width = 0;

		if (showDefault)
			width += getOsButtonWidth();
		if (userAddable)
			width += getOsButtonWidth();

		return width;
	}

	public int numberOfTabs() {
		return tabs.size();
	}

	public void mouseClicked(MouseEvent e) {
		for (Rectangle rect: closeboxes.keySet()) {
			if (rect.contains(e.getX(), e.getY())) {
				toolbar.requestClose(closeboxes.get(rect));
				return;
			}
		}

		for (Rectangle rect: clickboxes.keySet()) {
			if (rect.contains(e.getX(), e.getY())) {
				NTab tab = getTab(clickboxes.get(rect));
				toolbar.requestSelectTab(tab.id, tab.comp);
				return;
			}
		}

		if (addRect.contains(e.getX(), e.getY())) {
			toolbar.requestAddTab();
			return;
		}

		if (homeRect.contains(e.getX(), e.getY())) {
			toolbar.requestHomeTab();
			return;
		}
	}

	public NTab getTab(String id) {
		for (NTab tab: tabs) {
			if (tab.id.equals(id)) return tab;
		}
		return null;
	}

	public void mousePressed(MouseEvent mouseEvent) {

	}

	public void mouseReleased(MouseEvent mouseEvent) {

	}

	public void mouseEntered(MouseEvent mouseEvent) {

	}

	public void mouseExited(MouseEvent mouseEvent) {
		hover = "";
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		String oldhover = hover;
		for (Rectangle rect: clickboxes.keySet()) {
			if (rect.contains(e.getX(), e.getY())) {
				hover = clickboxes.get(rect);
				if (!hover.equals(oldhover)) {
					repaint();
				}
			}
		}
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void setTabsClosable(boolean closable) {
		this.closable = closable;
		repaint();
	}

	public void setTabsDefaultShown(boolean shown) {
		showDefault = shown;
		repaint();
	}

	public void setTabsUserAddable(boolean userAddable) {
		this.userAddable = userAddable;
	}

	public void tabRemoved(String id) {
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).id.equals(id)) {
				tabs.remove(i);
				repaint();
				return;
			}
		}
	}

	public String getClosestTab(String id) {
		if (!id.equals(selected)) {
			return selected;
		}

		NTab last = null;
		boolean returnNext = false;

		for (NTab tab: tabs) {
			if (returnNext) {
				return tab.id;
			}

			if (tab.id.equals(id)) {
				if (last != null) {
					return last.id;
				} else {
					returnNext = true;
				}
			}

			last = tab;
		}

		return "";
	}

	public void nameChanged(NPanel panel, String name) {
		for (NTab tab: tabs) {
			if (panel == tab.comp) {
				tab.name = name;
				repaint();
				return;
			}
		}
	}

	class NTab {
		private String id;
		private String name;
		private Component comp;

		public NTab(String id, String name, Component comp) {
			this.id = id;
			this.name = name;
			this.comp = comp;
		}
	}
}

package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.NFrame;
import org.jnat.swing.events.NEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class NToolbar extends JPanel {

	private ArrayList<NToolbarWidget> items;
	private NFrame master;
	private NSearchBar search;
	private NTabArea tabs;
	private JPanel center;
	private JPanel right;
	private boolean searchShown = false;
	private int b = 5, bottom = 2, left = 3, distance = 5;

	public NToolbar(NFrame master) {
		// Initialize the variables
		this.items = new ArrayList();
		this.master = master;
		this.search = new NSearchBar(this);
		this.center = new JPanel();
		this.right = new JPanel();
		this.tabs = new NTabArea(this);

		// Initialize the toolbar GUI
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#888888")));

		// Initialize the center panel
		if (JnatUtilities.isMac()) {
			center.setOpaque(false);
			center.setLayout(new FlowLayout(FlowLayout.LEFT, distance, 0));
			center.setBorder(BorderFactory.createEmptyBorder(2, b + left - distance, b + bottom, b));
		}

		// Initialize the bottom
		tabs.setVisible(false);

		// Initialize the right side
		search.setVisible(false);
		right.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		right.add(search);

		// Add the items to the GUI
		add(center, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
		add(tabs, BorderLayout.SOUTH);
	}

	public void trigger(String event) {
		master.trigger(event);
	}

	public void addToolbarSeparator() {
	}

	public void addToolbar(NToolbarWidget widget) {
		widget.setToolbar(this);
		items.add(widget);
		center.add(widget);
	}

	public void setSearchEnabled(boolean enabled) {
		searchShown = enabled;
		search.setVisible(enabled);
	}

	public void trigger(NEvent event) {
		master.trigger(event);
	}

	public void setTabsEnabled(boolean enabled) {
		//tabs.setVisible(enabled);
	}

	public void setTabsClosable(boolean closable) {
		tabs.setTabsClosable(closable);
	}

	public void setTabsDefaultShown(boolean shown) {
		tabs.setTabsDefaultShown(shown);
	}

	public void setTabsUserAddable(boolean userAddable) {
		tabs.setTabsUserAddable(userAddable);
	}

	public void tabAdded(String id, String name, Component comp, boolean select) {
		tabs.tabAdded(id, name, comp, select);
		tabs.setVisible(tabs.numberOfTabs() > 0);
	}

	public void tabSelected(String id) {
		tabs.tabSelected(id);
	}

	public void tabRemoved(String id) {
		tabs.tabRemoved(id);
		tabs.setVisible(tabs.numberOfTabs() > 0);
	}

	public void requestAddTab() {
		master.requestAddTab();
	}

	public void requestHomeTab() {
		master.requestHomeTab();
	}

	public void requestSelectTab(String id, Component comp) {
		master.requestSelectTab(id, comp);
	}

	public void requestClose(String id) {
		master.requestCloseTab(id);
	}

	public String getClosestTab(String id) {
		return tabs.getClosestTab(id);
	}
}

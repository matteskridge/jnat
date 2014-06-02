package org.jnat.swing;

import org.jnat.swing.events.*;
import org.jnat.swing.menu.NMenu;
import org.jnat.swing.menu.NMenuBar;
import org.jnat.swing.panels.NPanel;
import org.jnat.swing.panels.NWindowCenterPanel;
import org.jnat.swing.toolbar.NToolbar;
import org.jnat.swing.toolbar.NToolbarWidget;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Matt Eskridge on 5/10/14.
 *
 * @author Matt Eskridge
 */
public class NFrame extends JFrame {
	private JPanel contentPane;
	private JPanel card;
	private CardLayout cl;

	private NToolbar toolbar;
	private NWindowCenterPanel center;
	private NMenuBar menu;

	private ArrayList<NEventListener> listeners = new ArrayList();

	private boolean toolbarShown = false;
	private boolean menuBarShown = false;

	public NFrame() {
		init();
	}

	public NFrame(String text) {
		init();
		setTitle(text);
	}

	/**
	 * Set up the frame to use the various API convenientses which
	 * JNAT has to offer.
	 */
	private void init() {
		// Begin static initialization of JNAT
		JnatUtilities.initialize();

		// Initialize properties related to the OS
		if (JnatUtilities.isMac()) initMac();

		// Initialize variables
		contentPane = new JPanel();
		toolbar = new NToolbar(this);
		menu = new NMenuBar(this);
		card = new JPanel();
		center = new NWindowCenterPanel();

		// Initialize the content pane
		contentPane.setLayout(new BorderLayout());

		// Set GUI properties
		setTitle("JNAT - Java Native Application Toolkit");
		setSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);

		// Initialize the card panel
		card.setLayout(cl = new CardLayout());
		card.add(center, "");
		cl.show(card, "");

		// Add components to the content pane
		contentPane.add(card, BorderLayout.CENTER);
	}

	/**
	 * This function is called on a mac platform only to set up the
	 * NFrame for mac use.
	 */
	private void initMac() {
		// Show the apple fullscreen button.
		try {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", getTitle());

			Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
			Class params[] = new Class[]{Window.class, Boolean.TYPE};
			Method method = util.getMethod("setWindowCanFullScreen", params);
			method.invoke(util, this, true);
		} catch (Exception e) {

		}
	}

	/**
	 * Overrides the JFrame add option to add any components to the
	 * center panel, instead of the content pane.
	 *
	 * @param c The component to add.
	 */
	public Component add(Component c) {
		if (contentPane == null) return super.add(c);
		center.add(c);
		return c;
	}

	/**
	 * Overrides the alternative JFrame add method, which includes an
	 * object as an additional argument.
	 *
	 * @param c The component to add
	 * @param o Argument to the layout manager
	 */
	public void add(Component c, Object o) {
		if (contentPane == null) {
			super.add(c,o);
			return;
		}

		center.add(c, o);
	}

	/**
	 * Returns the pane which components should be added to. Using this
	 * method is not recommended, but is there mostly for code which
	 * invokes this method explicitly.
	 */
	public Container getContentPane() {
		return center;
	}

	/**
	 * Add a widget to the toolbar
	 */
	public void addToolbar(NToolbarWidget widget) {
		showToolbar();
		toolbar.addToolbar(widget);
	}

	/**
	 * Add a toolbar separator
	 */
	public void addToolbarSeparator() {
		showToolbar();
		toolbar.addToolbarSeparator();
	}

	/**
	 * This method is used to set certain OS properties which only apply
	 * when the toolbar is visible.
	 */
	private void showToolbar() {
		if (toolbarShown) return;

		// Add the toolbar
		contentPane.add(toolbar, BorderLayout.NORTH);

		// Perform OS specific setup
		if (JnatUtilities.isMac()) {
			getRootPane().putClientProperty("apple.awt.brushMetalLook", true);
		}

		toolbarShown = true;
	}

	private void showMenuBar() {
		if (menuBarShown) return;

		setJMenuBar(menu);

		menuBarShown = true;
	}

	public void addMenu(NMenu file) {
		showMenuBar();
		menu.add(file);
	}

	public void trigger(String text) {
		trigger(new NEvent(text));
	}

	public void trigger(NEvent event) {
		eventOccurred(event);
		for (NEventListener listen: listeners) {
			listen.eventOccurred(event);
		}
	}

	public void eventOccurred(NEvent event) {

	}

	public void addEventListener(NEventListener listener) {
		listeners.add(listener);
	}

	public void setSearchEnabled(boolean search) {
		toolbar.setSearchEnabled(search);
	}

	public void setTabsEnabled(boolean enabled) {
		toolbar.setTabsEnabled(enabled);
		showToolbar();
	}

	public void setTabsClosable(boolean closable) {
		toolbar.setTabsClosable(closable);
	}

	public void setTabsDefaultShown(boolean shown) {
		toolbar.setTabsDefaultShown(shown);
	}

	public void setTabsUserAddable(boolean userAddable) {
		toolbar.setTabsUserAddable(userAddable);
	}

	public void showDialog(String message) {
		showDialog("Dialog", message);
	}

	public void showDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public Component addTab() {
		return addTab("");
	}

	public Component addTab(String name) {
		return addTab(name, new NPanel());
	}

	public Component addTab(Component comp) {
		return addTab("", comp);
	}

	public Component addTab(String name, Component comp) {
		return addTab(name, comp, false);
	}

	public Component addTab(String name, Component comp, boolean background) {
		String id = ""+System.currentTimeMillis();
		card.add(comp, id);

		if (!background) {
			cl.show(card, id);
		}

		toolbar.tabAdded(id, name, comp, !background);
		return comp;
	}

	public void selectTab(String id) {
		cl.show(card, id);
		toolbar.tabSelected(id);
	}

	public void removeTab(String id) {
		String closest = toolbar.getClosestTab(id);
		toolbar.tabRemoved(id);
		selectTab(closest);
	}

	public void requestHomeTab() {
		selectTab("");
	}

	public void requestAddTab() {
		trigger(new NAddTabEvent());
	}

	public void requestCloseTab(String id) {
		removeTab(id);
	}

	public void requestSelectTab(String id, Component comp) {
		selectTab(id);
		trigger(new NSelectTabEvent(id, comp));
	}
}

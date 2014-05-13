package org.jnat.swing;

import org.jnat.swing.listeners.NEvent;
import org.jnat.swing.listeners.NEventListener;
import org.jnat.swing.menu.NMenu;
import org.jnat.swing.menu.NMenuBar;
import org.jnat.swing.panels.NBorderPanel;
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
		center = new NWindowCenterPanel();

		// Initialize the content pane
		contentPane.setLayout(new BorderLayout());

		// Set GUI properties
		setTitle("JNAT - Java Native Application Toolkit");
		setSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);

		// Add components to the content pane
		contentPane.add(center, BorderLayout.CENTER);
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
		NEvent event = new NEvent();
		event.setAction(text);

		for (NEventListener listen: listeners) {
			listen.eventOccurred(event);
		}
	}

	public void addEventListener(NEventListener listener) {
		listeners.add(listener);
	}
}

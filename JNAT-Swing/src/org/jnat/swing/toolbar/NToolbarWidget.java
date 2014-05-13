package org.jnat.swing.toolbar;

import org.jnat.swing.panels.NPanel;

import javax.swing.*;

/**
 * Created by matt on 5/10/14.
 */
public class NToolbarWidget extends JPanel {
	protected NToolbar toolbar;

	public NToolbarWidget() {

	}

	public void setToolbar(NToolbar toolbar) {
		this.toolbar = toolbar;
	}

	public void trigger(String event) {
		toolbar.trigger(event);
	}
}

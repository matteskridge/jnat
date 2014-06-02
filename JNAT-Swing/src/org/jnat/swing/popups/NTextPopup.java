package org.jnat.swing.popups;

import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.NEventListener;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTextPopup extends NPopup {
	private NEventListener listener;

	public NTextPopup(NEventListener listener, boolean selected) {
		this.listener = listener;

		if (selected) addOption("cut", "Cut");
		if (selected) addOption("copy", "Copy");
		addOption("paste", "Paste");
	}

	public void trigger(String event) {
		listener.eventOccurred(new NEvent(event));
	}
}

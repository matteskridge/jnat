package org.jnat.swing.events.action;

import org.jnat.swing.events.NEvent;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NActionEvent extends NEvent {
	public NActionEvent(String action) {
		super(action);
	}
}

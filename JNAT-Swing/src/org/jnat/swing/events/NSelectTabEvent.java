package org.jnat.swing.events;

import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 6/2/14
 */
public class NSelectTabEvent extends NTabEvent {
	private String id;
	private Component comp;

	public NSelectTabEvent(String id, Component comp) {
		super(NTabEvent.TYPE_SELECT);
		this.id = id;
		this.comp = comp;
	}

	public String getId() {
		return id;
	}

	public Component getComponent() {
		return comp;
	}
}

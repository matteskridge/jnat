package org.jnat.swing.events;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTabEvent extends NEvent {
	private int type;

	public static final int TYPE_ADD = 0;
	public static final int TYPE_SELECT = 1;

	public NTabEvent(int id) {
		super("jnat.tab");
		this.type = id;
	}

	public int getType() {
		return type;
	}

	public boolean isAddTab() {
		return type == TYPE_ADD;
	}
}

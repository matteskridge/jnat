package org.jnat.swing.events;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NSearchEvent extends NEvent {
	private String query;

	public NSearchEvent(String query) {
		super("jnat.search");
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
}

package org.jnat.swing.events.search;

import org.jnat.swing.events.NEvent;

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

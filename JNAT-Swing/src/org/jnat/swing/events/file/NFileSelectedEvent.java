package org.jnat.swing.events.file;

import java.io.File;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NFileSelectedEvent extends NFileEvent {

	public NFileSelectedEvent(File file) {
		super(file);
	}
}

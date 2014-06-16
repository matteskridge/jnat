package org.jnat.swing.events.file;

import org.jnat.io.NFile;
import org.jnat.swing.events.NEvent;

import java.io.File;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NFileEvent extends NEvent {
	protected NFile file;

	public NFileEvent(File file) {
		super("jnat.file");
		this.file = new NFile(file);
	}

	public File getFile() {
		return file;
	}
}

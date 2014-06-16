package org.jnat.io;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Matt Eskridge
 * @created 6/15/14
 */
public class NFile extends File {
	public NFile(File source) {
		super(source.getAbsolutePath());
	}

	public NFile(String path) {
		super(path);
	}

	public String read() {
		try {
			return new Scanner(this).useDelimiter("\\A").next();
		} catch (Exception e) {
			return null;
		}
	}

	public void write(String text) {
		try {
			FileWriter writer = new FileWriter(text);
			writer.write(text);
			writer.close();
		} catch (Exception e) {

		}
	}
}

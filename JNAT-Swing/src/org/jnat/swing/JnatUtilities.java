package org.jnat.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 5/10/14
 */
public class JnatUtilities {
	private static int JNAT_WINDOWS = 0;
	private static int JNAT_MAC = 1;
	private static int JNAT_LINUX = 2;
	private static int JNAT_UNKNOWN = 99;

	private static int JNAT_WINDOWS_XP = 10;
	private static int JNAT_WINDOWS_VISTA = 11;
	private static int JNAT_WINDOWS_7 = 12;
	private static int JNAT_WINDOWS_8 = 13;

	/**
	 * Set up JNAT. This is called automatically by NFrame.
	 */
	public static void initialize() {
		try {
			initLook();
		} catch (Exception e) {

		}
	}

	/**
	 * Initialize the look and feel
	 */
	private static void initLook() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	/**
	 * This function will retrieve the operating system ID.
	 *
	 * @return The operating system ID.
	 */
	public static int getOperatingSystem() {
		String os = System.getProperty("os.name");

		if (os.contains("Windows")) {
			return JNAT_WINDOWS;
		} else if (os.contains("OS X") || os.contains("Mac") || os.contains("OSX")) {
			return JNAT_MAC;
		} else {
			return JNAT_UNKNOWN;
		}
	}

	public static boolean isMac() {
		return getOperatingSystem() == JNAT_MAC;
	}

	public static String getOperatingSystemId() {
		if (isMac()) {
			return "mac";
		} else {
			return "generic";
		}
	}

	public static Color getOperatingSystemColor() {
		return getOperatingSystemColor(getOperatingSystem());
	}

	public static Color getOperatingSystemColor(int os) {
		if (os == JNAT_MAC) {
			return Color.decode("#666666");
		} else {
			return null;
		}
	}
}

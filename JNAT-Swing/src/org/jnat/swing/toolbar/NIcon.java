package org.jnat.swing.toolbar;

import org.filthyrichclients.images.ColorTintFilter;
import org.jnat.swing.JnatUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Matt Eskridge
 * @created 5/17/14
 */
public class NIcon {
	private BufferedImage img;

	public NIcon(String name) {
		String os = JnatUtilities.getOperatingSystemId();
		init(name, "org.jnat.swing.resources.icons."+os);
	}

	public NIcon(String name, String domain) {
		init(name, domain);
	}

	private void init(String name, String domain) {
		String path = domain.replace(".", "/")+"/"+name+".png";

		try {
			img = ImageIO.read(getStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public InputStream getStream(String path) {
		if (NIcon.class.getResource("NIcon.class").getProtocol().equals("file")) {
			try {
				File file = new File("JNAT-Swing/src/"+path);
				return new FileInputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return NIcon.this.getClass().getResourceAsStream(path);
		}
	}

	public BufferedImage getImage() {
		return img;
	}

	public BufferedImage getImage(int size) {
		BufferedImage image = new BufferedImage(size, size, img.getType());
		Graphics2D g = image.createGraphics();
		g.drawImage(img, 0, 0, size, size, null);
		return image;
	}

	public BufferedImage getImage(int size, Color tint) {
		ColorTintFilter filter = new ColorTintFilter(tint, 1f);
		return filter.filter(getImage(size), null);
	}

	public BufferedImage getImage(int size, int os) {
		return getImage(size, JnatUtilities.getOperatingSystemColor(os));
	}

	public ImageIcon getIcon(int size) {
		return new ImageIcon(getImage(size));
	}

	public ImageIcon getIcon(int size, Color tint) {
		return new ImageIcon(getImage(size, tint));
	}

	public ImageIcon getIcon(int size, int os) {
		return new ImageIcon(getImage(size, os));
	}
}

package org.filthyrichclients.images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * @author Matt Eskridge
 * @created 5/31/14
 */
public class ColorTintFilter extends AbstractFilter {
	private final Color mixColor;
	private final float mixValue;

	public ColorTintFilter(Color mixColor, float mixValue) {
		if (mixColor == null) {
			throw new IllegalArgumentException( "mixColor cannot be null");
		}

		this.mixColor = mixColor;
		if (mixValue < 0.0f) {
			mixValue = 0.0f;
		} else if (mixValue > 1.0f) {
			mixValue = 1.0f;
		}
		this.mixValue = mixValue;
	}

	public float getMixValue() {
		return mixValue;
	}

	public Color getMixColor() {
		return mixColor;
	}

	public BufferedImage filter(BufferedImage source) {
		return filter(source, null);
	}

	public BufferedImage filter(BufferedImage src, BufferedImage dst) {

		if (dst == null) {
			dst = createCompatibleDestImage(src, null);
		}

		int width = src.getWidth();
		int height = src.getHeight();

		int[] pixels = new int[width * height];
		getPixels(src, 0, 0, width, height, pixels);
		mixColor(pixels);
		setPixels(dst, 0, 0, width, height, pixels);

		return dst;
	}

	public static int[] getPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
		if (w == 0 || h == 0) {
			return new int[0];
		}
		if (pixels == null) {
			pixels = new int[w * h];
		} else if (pixels.length < w * h) {
			throw new IllegalArgumentException(
					"pixels array must have a length >= w*h");
		}

		int imageType = img.getType();
		if (imageType == BufferedImage.TYPE_INT_ARGB ||
				imageType == BufferedImage.TYPE_INT_RGB) {
			Raster raster = img.getRaster();
			return (int[]) raster.getDataElements(x, y, w, h, pixels);
		}

		return img.getRGB(x, y, w, h, pixels, 0, w);
	}

	public static void setPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
		if (pixels == null || w == 0 || h == 0) {
			return;
		} else if (pixels.length < w * h) {
			throw new IllegalArgumentException(
					"pixels array must have a length >= w*h");
		}

		int imageType = img.getType();
		if (imageType == BufferedImage.TYPE_INT_ARGB ||
				imageType == BufferedImage.TYPE_INT_RGB) {
			WritableRaster raster = img.getRaster();
			raster.setDataElements(x, y, w, h, pixels);
		} else {
			img.setRGB(x, y, w, h, pixels, 0, w);
		}
	}

	private void mixColor(int[] inPixels) {
		int mix_a = mixColor.getAlpha();
		int mix_r = mixColor.getRed();
		int mix_b = mixColor.getBlue();
		int mix_g = mixColor.getGreen();

		for (int i = 0; i < inPixels.length; i++) {
			int argb = inPixels[i];

			int a = argb & 0xFF000000;
			int r = (argb >> 16) & 0xFF;
			int g = (argb >>  8) & 0xFF;
			int b = (argb      ) & 0xFF;
			r = (int) (r * (1.0f - mixValue) + mix_r * mixValue);
			g = (int) (g * (1.0f - mixValue) + mix_g * mixValue);
			b = (int) (b * (1.0f - mixValue) + mix_b * mixValue);

			inPixels[i] = a << 24 | r << 16 | g << 8 | b;
		}
	}
}

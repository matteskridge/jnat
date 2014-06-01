package org.jnat.swing.toolbar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Matt Eskridge
 * @created 5/31/14
 */
public class NSearchBar extends JPanel {

	private JLabel icon;
	private JTextField field;
	private int b = 4;

	public NSearchBar() {
		// Initialize a few variables
		icon = new JLabel((new NIcon("Search")).getIcon(16, Color.decode("#767676")));
		field = new JTextField(15);

		// Initialize the GUI
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(b,b,b,b));

		// Set up the text field
		field.setOpaque(false);
		field.setBorder(BorderFactory.createEmptyBorder());

		// Add icons to the area
		add(icon, BorderLayout.WEST);
		add(field, BorderLayout.CENTER);
	}

	public void paintComponent(Graphics g1) {
		int m = 2, r = 15, rd = 0, b = 1;

		Graphics2D g = (Graphics2D)g1;

		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		g.setRenderingHints(qualityHints);

		g.setColor(Color.decode("#9F9F9F"));
		g.fillRoundRect(m, m, getWidth() - (m * 2), getHeight() - (m * 2), r, r);
		g.setPaint(new GradientPaint(0,0,Color.decode("#DEDEDE"),0,getHeight(),Color.decode("#FFFFFF")));
		g.fillRoundRect(m+b,m+b,getWidth()-(m*2)-(b*2), getHeight()-(m*2)-(b*2), r+rd, r+rd);
	}
}

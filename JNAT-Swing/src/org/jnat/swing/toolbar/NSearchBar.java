package org.jnat.swing.toolbar;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.events.NSearchEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Matt Eskridge
 * @created 5/31/14
 */
public class NSearchBar extends JPanel implements ActionListener {

	private NToolbar master;
	private JLabel icon;
	private JTextField field;
	private int b = 3, side = 5;

	public NSearchBar(NToolbar bar) {
		// Initialize a few variables
		master = bar;
		icon = new JLabel((new NIcon("Search")).getIcon(16, JnatUtilities.getOperatingSystem()));
		field = new JTextField(15);

		// Initialize the GUI
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(b,b,b,b));

		// Set up the text field
		field.setOpaque(false);
		field.setBorder(BorderFactory.createEmptyBorder(0,side,0,0));
		field.addActionListener(this);

		// Add icons to the area
		add(icon, BorderLayout.WEST);
		add(field, BorderLayout.CENTER);
	}

	public void paintComponent(Graphics g1) {
		int m = 0, r = 15, rd = 0, b = 1;

		Graphics2D g = (Graphics2D)g1;

		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		g.setRenderingHints(qualityHints);

		g.setColor(Color.decode("#9F9F9F"));
		g.fillRoundRect(m, m, getWidth() - (m * 2), getHeight() - (m * 2), r, r);
		g.setPaint(new GradientPaint(0,0,Color.decode("#F2F2F2"),0,getHeight(),Color.decode("#FFFFFF")));
		g.fillRoundRect(m+b,m+b,getWidth()-(m*2)-(b*2), getHeight()-(m*2)-(b*2), r+rd, r+rd);
	}

	public void actionPerformed(ActionEvent e) {
		master.trigger(new NSearchEvent(field.getText()));
	}
}

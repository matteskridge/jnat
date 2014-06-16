package org.jnat.swing.editor;

import org.jnat.swing.JnatUtilities;
import org.jnat.swing.editor.color.NTextColorJavaParser;
import org.jnat.swing.editor.color.NTextColorParser;
import org.jnat.swing.editor.color.NTextColorXmlParser;
import org.jnat.swing.editor.color.NTextColorYmlParser;
import org.jnat.swing.general.NScrollPane;
import org.jnat.swing.events.NEvent;
import org.jnat.swing.events.NEventListener;
import org.jnat.swing.panels.NPanel;
import org.jnat.swing.popups.NTextPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.text.*;
import javax.swing.text.Element;
import javax.swing.text.html.*;
import javax.swing.text.html.ParagraphView;
import javax.swing.text.html.parser.*;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTextEditor extends NPanel implements MouseListener, KeyListener, NEventListener {
	private JTextPane pane;
	private String format;
	private NTextEditorUpdater updater;
	private int b = 5;

	public NTextEditor() {
		setName("Text Editor");
		setLayout(new BorderLayout());

		pane = new JTextPane();
		pane.setBorder(BorderFactory.createEmptyBorder(b,b,b,b));
		pane.setText("");
		pane.addMouseListener(this);
		pane.setContentType("text/html");
		pane.addKeyListener(this);

		add(new NScrollPane(pane), BorderLayout.CENTER);
	}

	public String getText() {

		/*MyHtml2Text parser = new MyHtml2Text();
		try {
			parser.parse(new StringReader(pane.getText()));
		} catch (IOException e) {

		}*/

		if (!pane.getText().contains("<pre>"))
			return "";

		String text = pane.getText();
		text = text.substring(text.indexOf("<pre>"));
		text = text.substring(0, text.lastIndexOf("</pre>"));
		text = text.replace("<br />", "\n");//JnatUtilities.getSystemLineSeparator());
		text = text.replace("<br>", "\n");//JnatUtilities.getSystemLineSeparator());
		text = text.replaceAll("\\<.*?\\>", "");
		text = text.replace("&lt;", "<");
		text = text.replace("&gt;", ">");
		text = text.replace("&quot;", "\"");
		return text;
	}

	public void setText(String text) {
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		text = text.replace("\n", "<br />");

		String css = "* { tab-size:4; }";
		String begin = "<html><head><style type='text/css'>"+css+"</style></head><body><pre>";
		String end = "</pre></body></html>";

		if (format != null) {
			text = formatText(text, format);
		}

		pane.setText(begin + text + end);

		System.out.println(pane.getText());
	}

	public static String formatText(String text, String format) {
		NTextColorParser parser = null;

		if (format.equals("xml")) {
			parser = new NTextColorXmlParser();
		} else if (format.equals("yml")) {
			parser = new NTextColorYmlParser();
		} else if (format.equals("java")) {
			parser = new NTextColorJavaParser();
		} else if (format.equals("cpp")) {

		}

		if (parser != null) {
			return parser.parse(text);
		} else {
			return text;
		}
	}

	private void reformat() {
		//pane.setText(pane.getText());
		int selected = pane.getSelectionStart();
		setText(getText());
		pane.setSelectionStart(selected);
		pane.setSelectionEnd(selected);
	}

	public void setFormat(String format) {
		this.format = format;
		reformat();
	}

	public void eventOccurred(NEvent e) {
		if (e.getAction().equals("cut")) {
			pane.cut();
		} else if (e.getAction().equals("copy")) {
			pane.copy();
		} else if (e.getAction().equals("paste")) {
			pane.paste();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.isPopupTrigger() || SwingUtilities.isRightMouseButton(e)) {
			NTextPopup popup = new NTextPopup(this, pane.getSelectedText() != null);
			popup.show(this,e.getX(),e.getY());
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void keyTyped(KeyEvent keyEvent) {

	}

	public void keyPressed(KeyEvent keyEvent) {

	}

	public void keyReleased(KeyEvent keyEvent) {
		if (updater == null) {
			updater = new NTextEditorUpdater();
			updater.start();
		}

		updater.restart();
	}

	class NTextEditorUpdater implements ActionListener {
		private Timer timer;
		private int delay = 1000;

		public NTextEditorUpdater() {
			timer = new Timer(delay, this);
		}

		public void start() {
			timer.start();
		}

		public void restart() {
			timer.restart();
		}

		public void actionPerformed(ActionEvent e) {
			reformat();
			timer.stop();
		}
	}

	class MyHtml2Text extends HTMLEditorKit.ParserCallback {
		StringBuffer s;
		
		public MyHtml2Text() {

		}

		public void parse(Reader in) throws IOException {
			s = new StringBuffer();
			ParserDelegator delegator = new ParserDelegator();
			delegator.parse(in, this, Boolean.TRUE);
		}

		public void handleText(char[] text, int pos) {
			s.append(text);
			//s.append("\n");
		}

		public String getText() {
			return s.toString();
		}
	}
}
package org.jnat.swing.editor.fileselector;

import org.jnat.swing.events.NEventListener;
import org.jnat.swing.events.file.NFileSelectedEvent;
import org.jnat.swing.general.NScrollPane;
import org.jnat.swing.general.NSourcePanel;
import org.jnat.swing.general.table.NTable;
import org.jnat.swing.general.table.NTableModel;
import org.jnat.swing.panels.NPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NFileChooser extends NPanel implements ListSelectionListener {

	private ArrayList<NEventListener> listeners;
	private NTable table;
	private NScrollPane scroll;
	private NFileControlBar top;
	private NFileFinishBar finish;
	private NSourcePanel side;
	private File file;

	public NFileChooser() {
		init();
	}

	public NFileChooser(File source) {
		this.file = source;
		init();
	}

	public NFileChooser(NEventListener listener) {
		init();
		listeners.add(listener);
	}

	public NFileChooser(File source, NEventListener listener) {
		this.file = file;
		init();
		listeners.add(listener);
	}

	private void init() {
		listeners = new ArrayList();

		setLayout(new BorderLayout());

		if (file == null) {
			file = new File(System.getProperty("user.home"));
		}

		selectDirectory(file);
	}

	public void selectDirectory(File folder) {
		removeAll();

		table = new NFileListTable();
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellSelectionModel.addListSelectionListener(this);

		add(top = new NFileControlBar(), BorderLayout.NORTH);
		add(side = new NFavoritesSourcePanel(), BorderLayout.WEST);
		add(scroll = new NScrollPane(table), BorderLayout.CENTER);
		add(finish = new NFileFinishBar(), BorderLayout.SOUTH);

		ArrayList<String> headers = new ArrayList();
		headers.add("Name");
		headers.add("Last Modified");

		ArrayList<ArrayList<String>> data = new ArrayList();
		for (File f: folder.listFiles()) {
			if (f.getName().startsWith(".")) continue;

			ArrayList<String> row = new ArrayList();
			row.add(f.getName());
			row.add(""+f.lastModified());
			data.add(row);
		}

		NTableModel model = new NTableModel(headers, data);
		model.setEditable(false);

		table.setModel(model);
		revalidate();
	}

	public void valueChanged(ListSelectionEvent e) {
		int index = e.getFirstIndex();
		File f = new File(file.getAbsolutePath()+File.separator+table.getModel().getValueAt(index,0));

		if (!e.getValueIsAdjusting() && f.exists() && f.isDirectory()) {
			selectDirectory(f);
		} else if (!e.getValueIsAdjusting() && f.exists()) {
			NFileSelectedEvent event = new NFileSelectedEvent(f);
			for (NEventListener listener: listeners) {
				listener.eventOccurred(event);
			}
		}

		table.clearSelection();
	}
}

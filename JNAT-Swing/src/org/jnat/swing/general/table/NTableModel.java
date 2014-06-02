package org.jnat.swing.general.table;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * @author Matt Eskridge
 * @created 6/1/14
 */
public class NTableModel implements TableModel {
	private ArrayList<String> headers;
	private ArrayList<ArrayList<String>> data;
	private boolean editable;

	public NTableModel(ArrayList<String> headers, ArrayList<ArrayList<String>> data) {
		this.headers = headers;
		this.data = data;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getRowCount() {
		return data.size();
	}

	public int getColumnCount() {
		return headers.size();
	}

	public String getColumnName(int i) {
		return headers.get(i);
	}

	public Class<?> getColumnClass(int i) {
		return String.class;
	}

	public boolean isCellEditable(int i, int i2) {
		return editable;
	}

	public Object getValueAt(int i, int i2) {
		return data.get(i).get(i2);
	}

	public void setValueAt(Object o, int i, int i2) {
		data.get(i).set(i2, ""+o);
	}

	public void addTableModelListener(TableModelListener tableModelListener) {

	}

	public void removeTableModelListener(TableModelListener tableModelListener) {

	}
}

package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Costumer;

public class TableCostumer extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Costumer> ls;
	private String[] columnNames = {"id", "Nama", "Alamat", "Nomorhp"};
	public TableCostumer (List<Costumer>ls) {
		this.ls = ls;
	}
	
	public int getRowCount() {
	    return ls.size();
	}

	public int getColumnCount() {
	    return 4;
	}

	public String getColumnName(int column) {
	    return columnNames[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
	    switch (columnIndex) {
	        case 0:
	            return ls.get(rowIndex).getId();
	        case 1:
	            return ls.get(rowIndex).getNama();
	        case 2:
	            return ls.get(rowIndex).getAlamat();
	        case 3:
	            return ls.get(rowIndex).getNomorhp();
	        default:
	            return null;
	    }
	}

}

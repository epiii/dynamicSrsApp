package id.ac.its.depandi.dynamic_srs.table_model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import id.ac.its.depandi.dynamic_srs.core.SRS;

public class SrsTableModel extends AbstractTableModel{

	public static final int OBJECT_COL = -1;
	private static final int SRS_ID_COL = 0;
	private static final int SRS_NAME_COL = 1;
	private static final int USER_NAME_COL = 2;

	private String[] columnNames = { "Srs_ID", "Srs_Name","User_Name" };
	private List<SRS> srss;

	public SrsTableModel(List<SRS> theUsers) {
		srss = theUsers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return srss.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		SRS tempSrs = srss.get(row);

		switch (col) {
			case SRS_ID_COL:
				return tempSrs.getSrs_id();
			case SRS_NAME_COL:
				return tempSrs.getSrs_name();
			case USER_NAME_COL:
				return tempSrs.getUser_name();
			default:
				return tempSrs.getSrs_name();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}	
}

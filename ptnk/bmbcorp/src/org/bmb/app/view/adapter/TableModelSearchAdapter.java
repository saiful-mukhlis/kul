package org.bmb.app.view.adapter;


import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public interface TableModelSearchAdapter extends TableModel {
	public boolean isInstace(Object model);
	public void addObjModel(Object model);
	public void editObjModel(Object model);
	public void load();
	public void reload();
	
	public void setPaging(PagingAdapter paging);
	public void setDefaultLebar(JTable table);
	public List getModel();
	public void fireTableDataChanged();
	public void setSearching(boolean isSearch);
	public boolean isSearching();
	public void setTextSearch(String teksSearch);
}

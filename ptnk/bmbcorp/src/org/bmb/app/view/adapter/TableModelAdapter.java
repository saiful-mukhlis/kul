package org.bmb.app.view.adapter;


import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.bmb.app.dao.adapter.DaoAdapter;
import com.orientechnologies.orient.core.db.ODatabaseWrapperAbstract;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.ODatabaseRecordTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface TableModelAdapter extends TableModel {
	public void addObjModel(ODocument model);
	public void editObjModel(ODocument model);
	public void load(ODatabaseDocumentTx db);
	public void reload(ODatabaseDocumentTx db);
	
	public void setPaging(PagingAdapter paging);
	public void setDefaultLebar(JTable table);
	public List getModel();
	public void fireTableDataChanged();
	
	public void loadDataModel(ODatabaseDocumentTx db);
	public void loadJumlahData(ODatabaseDocumentTx db);
	
	public void initDao();
	public void setTable(TableAdapter table);
	public void initNamaKolom(ODatabaseDocumentTx db);
	public List getModel2();
	public DaoAdapter getDao();
	
	public String[] getNamaKolom();
}

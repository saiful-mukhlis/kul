package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatTableModel  extends TableModelAbstract{

	public FormatTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	protected final int NO = 0;
	protected final int NAME = 1;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(NAME).setPreferredWidth(100);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[2];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[NAME]=App.getT(db, "Nama Toko");
	}
	public void load(ODatabaseDocumentTx db) {
		loadJumlahData(db);
		loadDataModel(db);
		super.load(db);
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == NO) {
			int no = rowIndex + 1;
			if (paging != null) {
					no += ((paging.getCurentHalaman()-1) * paging.getJumlahPerHalaman());
			}
			return no;
		} else if (columnIndex == NAME) {
			return m.field("name");
		} 
		else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
		// TODO Auto-generated method stub
		
	}








	@Override
	public void initDao() {
		dao=App.getFormatDao();
	}
	@Override
	public List getModel2() {
		return null;
	}

}

package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTableModel  extends TableModelAbstract{

	public PelangganTableModel(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAME = 2;
	protected final int NAME_PEMILIK = 3;
//	protected final int TELP = 4;
//	protected final int ADDRESS = 5;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAME).setPreferredWidth(100);
			t.getColumn(NAME_PEMILIK).setPreferredWidth(100);
//			t.getColumn(TELP).setPreferredWidth(27);
//			t.getColumn(ADDRESS).setPreferredWidth(27);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[4];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[CODE]=App.getT(db, "Kode");
		namaKolom[NAME]=App.getT(db, "Nama Toko");
		namaKolom[NAME_PEMILIK]=App.getT(db, "Nama Pemilik");
//		namaKolom[TELP]=App.getT(db, "No Telp");
//		namaKolom[ADDRESS]=App.getT(db, "Alamat");
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
		} else if (columnIndex == CODE) {
			return m.field("code");
		} else if (columnIndex == NAME) {
			return m.field("name");
		} else if (columnIndex == NAME_PEMILIK) {
			return m.field("pemilik");
		}
//		else if (columnIndex == TELP) {
//			return m.field("notelp");
//		} else if (columnIndex == ADDRESS) {
//			return m.field("alamat");
//		} 
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
		dao=App.getPelangganDao();
	}
	@Override
	public List getModel2() {
		return null;
	}

}

package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangTableModel  extends TableModelAbstract{

	public KandangTableModel(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
//	protected final int CODE = 1;
	protected final int NAMA = 1;
//	protected final int NOTE = 3;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[2];
		namaKolom[NO]=App.getT(db, "No");
//		namaKolom[CODE]=App.getT(db, "Kode");
		namaKolom[NAMA]=App.getT(db, KandangDao.fname);
//		namaKolom[NOTE]=App.getT(db, "Keterangan");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
//			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAMA).setPreferredWidth(100);
//			t.getColumn(NOTE).setPreferredWidth(27);
			}
		
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
		} 
//		else if (columnIndex == CODE) {
//			return m.field(KandangDao.code);
//		} 
		else if (columnIndex == NAMA) {
			return m.field(KandangDao.name);
		}
//		else if (columnIndex == NOTE) {
//			return m.field(KandangDao.note);
//		} 
		else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
	}





	@Override
	public void initDao() {
		dao=App.getKandangDao();
	}
	@Override
	public List getModel2() {
		return null;
	}

}

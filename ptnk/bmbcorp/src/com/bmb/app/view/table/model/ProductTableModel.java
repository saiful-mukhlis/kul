package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductTableModel  extends TableModelAbstract{

	public ProductTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAME = 2;
	protected final int HARGA = 3;
	protected final int SALDO = 4;
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[5];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[CODE]=App.getT(db, "Kode");
		namaKolom[NAME]=App.getT(db, "Nama");
		namaKolom[SALDO]=App.getT(db, "Saldo");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAME).setPreferredWidth(27);
			t.getColumn(HARGA).setPreferredWidth(27);
			t.getColumn(SALDO).setPreferredWidth(27);
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
				if (paging.getCurentHalaman()==-1) {
					no=1;
					no += (0 * paging.getJumlahPerHalaman());
				}else{
					no += (paging.getCurentHalaman() * paging.getJumlahPerHalaman());
				}
			}
			return no;
		} else if (columnIndex == CODE) {
			return m.field("code");
		} else if (columnIndex == NAME) {
			return m.field("name");
		} else if (columnIndex == HARGA) {
			return m.field("harga");
		} else if (columnIndex == SALDO) {
			return m.field("saldo");
		}   else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		// TODO Auto-generated method stub
		
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

package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanbTableModel  extends TableModelAbstract{

	public PenjualanbTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	protected final int NO = 0;
	protected final int TAHUN = 1;
	protected final int BULAN = 2;
	protected final int TOTAL = 3;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(BULAN).setPreferredWidth(10);
			t.getColumn(TAHUN).setPreferredWidth(10);
			t.getColumn(TOTAL).setPreferredWidth(20);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[4];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[BULAN]=App.getT(db, "Bulan");
		namaKolom[TAHUN]=App.getT(db, "Tahun");
		namaKolom[TOTAL]=App.getT(db, "Total");
	}
//	public void load(ODatabaseDocumentTx db) {
//		loadJumlahData(db);
//		loadDataModel(db);
//		super.load(db);
//	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ODocument m=model.get(rowIndex);
		if (columnIndex == NO) {
			int no = rowIndex + 1;
			if (paging != null) {
					no += ((paging.getCurentHalaman()-1) * paging.getJumlahPerHalaman());
			}
			return no;
		}  else if (columnIndex == TAHUN) {
			return m.field("t");
		}else if (columnIndex == BULAN) {
			int tmp=m.field("b");
			return App.bln[tmp];
		} else if (columnIndex == TOTAL) {
			return App.paymentFormat2.format(m.field("total"));
		}   else {
			return null;
		}
	}
	









	@Override
	public void initDao() {
		dao=App.getPenjualanbDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
	@Override
	public void editObjModel(ODocument model) {
		// TODO Auto-generated method stub
		
	}

}

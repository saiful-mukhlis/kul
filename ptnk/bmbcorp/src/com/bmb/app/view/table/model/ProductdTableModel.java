package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.db.Productd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductdTableModel  extends TableModelAbstract{

	public ProductdTableModel(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int URAIAN = 2;
	protected final int REF = 3;
	protected final int D = 4;
	protected final int K = 5;
	protected final int SISA = 6;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[7];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[TGL]=App.getT(db, "Tanggal");
		namaKolom[URAIAN]=App.getT(db, "Uraian");
		namaKolom[REF]=App.getT(db, "Ref");
		namaKolom[D]=App.getT(db, "Debit");
		namaKolom[K]=App.getT(db, "Kredit");
		namaKolom[SISA]=App.getT(db, "Saldo");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(TGL).setPreferredWidth(30);
			t.getColumn(URAIAN).setPreferredWidth(35);
			t.getColumn(REF).setPreferredWidth(35);
			t.getColumn(D).setPreferredWidth(30);
			t.getColumn(K).setPreferredWidth(30);
			t.getColumn(SISA).setPreferredWidth(30);
			
			
			t.getColumn(D).setCellRenderer(App.tablePayment.getNumberRenderer());
			t.getColumn(K).setCellRenderer(App.tablePayment.getNumberRenderer());
			t.getColumn(SISA).setCellRenderer(App.tablePayment.getNumberRenderer());
			
			}
		
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
		} else if (columnIndex == TGL) {
			return App.dateFormat.format(m.field("tgl"));
		} else if (columnIndex == URAIAN) {
			int t=m.field("type");
			if (t==Productd.TYPE_PENJUALAN) {
				return "Penjualan";
			}else if (t==Productd.TYPE_PRODUKSI_PLUS) {
				return "Penambahan Produksi";
			}else if (t==Productd.TYPE_PRODUKSI_EDIT) {
				return "Edit Produksi";
			}else if (t==Productd.TYPE_PRODUKSI_HAPUS) {
				return "Hapus Produksi";
			}else if (t==Productd.TYPE_EDIT) {
				return "Edit Stok";
			}
			return null;
		} else if (columnIndex == REF) {
			return m.field("ref");
		} else if (columnIndex == D) {
			return m.field("d");
		} else if (columnIndex == K) {
			return m.field("k");
		} else if (columnIndex == SISA) {
			return m.field("sisa");
		}  else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getProductdDao().getClassName())) {
			this.model.add(model);
			fireTableDataChanged();
		}
		
	}

	@Override
	public void editObjModel(ODocument model) {
		// TODO Auto-generated method stub
		
	}








	@Override
	public void initDao() {
		dao=App.getProductdDao();
	}
	@Override
	public List getModel2() {
		// TODO Auto-generated method stub
		return null;
	}

}

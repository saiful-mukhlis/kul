package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PenjualanhDao;
import com.bmb.app.db.Pelanggan;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanFTableModel  extends TableModelAbstract{

	protected List<ODocument> pelanggans=new ArrayList<ODocument>();
	
//	protected ODocument penjualanh;
	
	
	public PenjualanFTableModel(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}

	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int CODE = 2;
	protected final int NAME_TOKO = 3;
	protected final int JML = 4;
	protected final int HARGA = 5;
	protected final int TOTAL = 6;
	protected final int DIBAYAR = 7;
	protected final int HUTANG = 8;
	protected final int NOTE = 9;
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[10];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[TGL]=App.getT(db, "Tanggal");
		namaKolom[CODE]=App.getT(db, "Kode");
		namaKolom[NAME_TOKO]=App.getT(db, "Nama Toko");
		namaKolom[JML]=App.getT(db, "Jumlah[Kg]");
		namaKolom[HARGA]=App.getT(db, "Harga");
		namaKolom[TOTAL]=App.getT(db, "Total");
		namaKolom[DIBAYAR]=App.getT(db, "Dibayar");
		namaKolom[HUTANG]=App.getT(db, "Hutang");
		namaKolom[NOTE]=App.getT(db, "Keterangan");
	}
	@Override
	public void setDefaultLebar(JTable table) {
//		if (table!=null) {
//			TableColumnModel t=table.getColumnModel();
//			t.getColumn(NO).setPreferredWidth(27);
//			t.getColumn(TGL).setPreferredWidth(27);
//			t.getColumn(CODE).setPreferredWidth(27);
//			t.getColumn(NAME_TOKO).setPreferredWidth(27);
//			t.getColumn(JML).setPreferredWidth(27);
//			t.getColumn(HARGA).setPreferredWidth(27);
//			t.getColumn(TOTAL).setPreferredWidth(27);
//			t.getColumn(DIBAYAR).setPreferredWidth(27);
//			t.getColumn(HUTANG).setPreferredWidth(27);
//			t.getColumn(NOTE).setPreferredWidth(27);
//			}
		
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
		} else if (columnIndex == CODE) {
			return m.field("code");
		} else if (columnIndex == TGL) {
			return App.dateFormat.format(m.field(PenjualanDao.tgl));
		}  else if (columnIndex ==NAME_TOKO ) {
			ODocument tmp=pelanggans.get(rowIndex);
			return tmp.field(PelangganDao.name);
		} else if (columnIndex == JML) {
			return m.field("jml");
		} else if (columnIndex == HARGA) {
			return m.field("harga");
		} else if (columnIndex == TOTAL) {
			return m.field("total1");
		} else if (columnIndex == DIBAYAR) {
			return m.field("d");
		} else if (columnIndex == HUTANG) {
			return m.field("k");
		} else if (columnIndex == NOTE) {
			return m.field("note");
		}  else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		if (model!=null) {
			if (model.field("@class").equals(App.getPenjualanDao().getClassName())) {
				this.model.add(model);
			}else if(model.field("@class").equals(App.getPelangganDao().getClassName())) {
				this.pelanggans.add(model);
				fireTableDataChanged();
			}
			
		}
	}

	@Override
	public void editObjModel(ODocument model) {
	}








	@Override
	public void initDao() {
		dao=App.getPenjualanDao();
	}
	@Override
	public List getModel2() {
		return pelanggans;
	}
//	public ODocument getPenjualanh() {
//		return penjualanh;
//	}
//	public void setPenjualanh(ODocument penjualanh) {
//		this.penjualanh = penjualanh;
//		if (penjualanh!=null && penjualanh.field("@class").equals(App.getPenjualanhDao().getClassName())) {
//			this.penjualanh = penjualanh;
//			ODatabaseDocumentTx db=App.getDbd();
//			ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			reload(db);
//			db.close();
//		}
//	}

	
	
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {}
	
	

}

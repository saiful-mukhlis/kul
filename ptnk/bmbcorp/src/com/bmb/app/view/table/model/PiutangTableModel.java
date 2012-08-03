package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangTableModel  extends TableModelAbstract{

	protected List<ODocument> pelanggans;
	
	public PiutangTableModel(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}


	protected final int NO = 0;
	protected final int NAMA_TOKO = 1;
	protected final int NAMA_PEMILIK = 2;
	protected final int TOTAL = 3;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[4];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[NAMA_TOKO]=App.getT(db, "Nama Toko");
		namaKolom[NAMA_PEMILIK]=App.getT(db, "Nama Pemilik");
		namaKolom[TOTAL]=App.getT(db, "Total");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(NAMA_TOKO).setPreferredWidth(27);
			t.getColumn(NAMA_PEMILIK).setPreferredWidth(27);
			t.getColumn(TOTAL).setPreferredWidth(27);
			
			t.getColumn(TOTAL).setCellRenderer(App.tablePayment.getCurrencyRenderer());
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
		} else if (columnIndex == NAMA_TOKO) {
			ODocument tmp=pelanggans.get(rowIndex);
			return tmp.field(PelangganDao.name);
		} else if (columnIndex == NAMA_PEMILIK) {
			ODocument tmp=pelanggans.get(rowIndex);
			return tmp.field(PelangganDao.pemilik);
		} else if (columnIndex == TOTAL) {
			return m.field("total");
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
	public void loadDataModel(ODatabaseDocumentTx db) {
		super.loadDataModel(db);
		pelanggans=new ArrayList<ODocument>();
		for (ODocument oDocument : model) {
			ODocument tmp2=oDocument.field(PiutangDao.pelanggan);
			tmp2.field(PelangganDao.name);
			pelanggans.add(tmp2);
		}
	}
	
	
	@Override
	public void initDao() {
		dao=App.getPiutangDao();
	}
	@Override
	public List getModel2() {
		return pelanggans;
	}

}

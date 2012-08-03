package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.db.Grp;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurTableModel  extends TableModelAbstract{
	private ODocument kandang;
	public LajurTableModel(ODatabaseDocumentTx db) {
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
		namaKolom[NAMA]=App.getT(db, LajurDao.fname);
//		namaKolom[NOTE]=App.getT(db, "Keterangan");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
//			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAMA).setPreferredWidth(200);
//			t.getColumn(NOTE).setPreferredWidth(27);
			}
		
	}
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (kandang!=null) {
			if (paging != null) {
				int tmp=(paging.getCurentHalaman()-1)
						* paging.getJumlahPerHalaman();
				if (tmp<0) {
					tmp=0;
				}
				model = getDao().getAllByColumn(db, LajurDao.kandang, kandang.getIdentity(), tmp
						,
						paging.getJumlahPerHalaman());
			} else {
				model = (List<ODocument>) getDao().getAllByColumn(db,  LajurDao.kandang, kandang.getIdentity());
			}
		}else{
			model=new ArrayList<ODocument>();
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			if (kandang!=null) {
				paging.setJumlahData(dao.getCountByColumn(db,  LajurDao.kandang, kandang.getIdentity()));
			}else{
				paging.setJumlahData((long) 0);
			}
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
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
		} 
//		else if (columnIndex == CODE) {
//			return m.field(LajurDao.code);
//		} 
		else if (columnIndex == NAMA) {
			return m.field(LajurDao.name);
		} 
//		else if (columnIndex == NOTE) {
//			return m.field(LajurDao.note);
//		} 
		else {
			return null;
		}
	}
	

//	@Override
//	public void addObjModel(ODocument model) {
//		if (model.field("@class").equals(dao.getClassName())) {
//			this.model.add(model);
//		fireTableDataChanged();
//		}
//	}

	@Override
	public void editObjModel(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getKandangDao().getClassName())) {
			setKandang(model);
		}else if(model==null){
			setKandang(null);
		}
	}





	@Override
	public void initDao() {
		dao=App.getLajurDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
	public ODocument getKandang() {
		return kandang;
	}
	public void setKandang(ODocument kandang) {
		this.kandang = kandang;
	}

}

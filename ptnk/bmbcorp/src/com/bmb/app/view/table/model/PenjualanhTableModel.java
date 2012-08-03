package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PenjualanhDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanhTableModel  extends TableModelAbstract{

	protected ODocument penjualanb;
	
	public PenjualanhTableModel(ODatabaseDocumentTx db) {
		super(db);
		// TODO Auto-generated constructor stub
	}
	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int TOTAL = 2;
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(TGL).setPreferredWidth(20);
			t.getColumn(TOTAL).setPreferredWidth(20);
			}
		
	}
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[3];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[TGL]=App.getT(db, "Tanggal");
		namaKolom[TOTAL]=App.getT(db, "Total");
	}
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (penjualanb!=null) {
			if (paging != null) {
				int tmp=(paging.getCurentHalaman()-1)
						* paging.getJumlahPerHalaman();
				if (tmp<0) {
					tmp=0;
				}
				model = ((PenjualanhDao)getDao()).getAllByColumnTgl(db,  penjualanb, tmp
						,
						paging.getJumlahPerHalaman());
			} else {
				model = (List<ODocument>) ((PenjualanhDao)getDao()).getAllByColumnTgl(db,  penjualanb);
			}
		}else{
			model=new ArrayList<ODocument>();
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			if (penjualanb!=null) {
				paging.setJumlahData(((PenjualanhDao)getDao()).getCountByColumnTgl(db, penjualanb));
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
			return App.dateFormat.format(m.field(PenjualanhDao.tgl));
		} else if (columnIndex == TOTAL) {
			return App.paymentFormat2.format(m.field(PenjualanhDao.total));
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
		setPenjualanb(model);
	}








	@Override
	public void initDao() {
		dao=App.getPenjualanhDao();
	}
	@Override
	public List getModel2() {
		// TODO Auto-generated method stub
		return null;
	}
	public ODocument getPenjualanb() {
		return penjualanb;
	}
	public void setPenjualanb(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getPenjualanbDao().getClassName())) {
			this.penjualanb = model;
		}
	}

	
}

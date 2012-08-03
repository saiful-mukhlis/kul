package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PenjualanhDao;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.dao.PiutangdDao;
import com.bmb.app.db.Pelanggan;
import com.bmb.app.db.Piutang;
import com.bmb.app.db.Piutangd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangdTableModel  extends TableModelAbstract{
	protected ODocument piutang;
	
	public PiutangdTableModel(ODatabaseDocumentTx db) {
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
		namaKolom[SISA]=App.getT(db, "Sisa");
		
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(TGL).setPreferredWidth(30);
			t.getColumn(URAIAN).setPreferredWidth(30);
			t.getColumn(REF).setPreferredWidth(40);
			t.getColumn(D).setPreferredWidth(30);
			t.getColumn(K).setPreferredWidth(30);
			t.getColumn(SISA).setPreferredWidth(30);
			
			t.getColumn(D).setCellRenderer(App.tablePayment.getCurrencyRenderer());
			t.getColumn(K).setCellRenderer(App.tablePayment.getCurrencyRenderer());
			t.getColumn(SISA).setCellRenderer(App.tablePayment.getCurrencyRenderer());
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
		}  else if (columnIndex == URAIAN) {
			int t=m.field("type");
			if (t==Piutangd.TYPE_PENJUALAN) {
				return "Penjualan";
			}else if (t==Piutangd.TYPE_PEMBAYARAN) {
				return "Pembayaran";
			}
			return null;
		} else if (columnIndex == REF) {
			return m.field("ref");
		} else if (columnIndex == D) {
			return App.paymentFormat2.format(m.field("d"));
		} else if (columnIndex == K) {
			return App.paymentFormat2.format(m.field("k"));
		} else if (columnIndex == SISA) {
			return App.paymentFormat2.format(m.field("sisa"));
		}  else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getPiutangdDao().getClassName())) {
			this.model.add(model);
		}
		
	}

	@Override
	public void editObjModel(ODocument model) {
		setPiutang(model);
		
	}








	@Override
	public void initDao() {
		dao=App.getPiutangdDao();
	}
	@Override
	public List getModel2() {
		// TODO Auto-generated method stub
		return null;
	}
	public ODocument getPiutang() {
		return piutang;
	}
	public void setPiutang(ODocument piutang) {
		if (piutang!=null && piutang.field("@class").equals(App.getPiutangDao().getClassName())) {
			this.piutang = piutang;
			ODatabaseDocumentTx db=App.getDbd();
			ODatabaseRecordThreadLocal. INSTANCE.set(db);
			reload(db);
			db.close();
			
		}
	}
	
	
	
	
	
	
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (piutang!=null) {
			if (paging != null) {
				int tmp=paging.getCurentHalaman()-1
						* paging.getJumlahPerHalaman();
				if (tmp<0) {
					tmp=0;
				}
				
				model = dao.getAllByColumn(db, PiutangdDao.piutang , piutang.getIdentity() , tmp
						,
						paging.getJumlahPerHalaman());
				
//				pelanggans=new ArrayList<ODocument>();
//				for (ODocument oDocument : model) {
//					ODocument tmp2=oDocument.field(PenjualanDao.pelanggan);
//					tmp2.field(PelangganDao.name);
//					pelanggans.add(tmp2);
//				}
				
			} else {
				model = (List<ODocument>) getDao().getAllByColumn(db,  PiutangdDao.piutang , piutang.getIdentity());
			}
		}else{
			model=new ArrayList<ODocument>();
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			if (piutang!=null) {
				paging.setJumlahData(dao.getCountByColumn(db, PiutangdDao.piutang, piutang.getIdentity()));
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

}

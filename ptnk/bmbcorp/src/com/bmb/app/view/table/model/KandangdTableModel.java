package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdTableModel  extends TableModelAbstract{

	public KandangdTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
	private ODocument kandang;
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (kandang!=null) {
			if (paging != null) {
				int tmp=paging.getCurentHalaman()-1
						* paging.getJumlahPerHalaman();
				if (tmp<0) {
					tmp=0;
				}
				model = getDao().getAllByColumn(db, KandangdDao.kandang, kandang.getIdentity(), tmp
						,
						paging.getJumlahPerHalaman());
			} else {
				model = (List<ODocument>) getDao().getAllByColumn(db, KandangdDao.kandang, kandang.getIdentity());
			}
		}else{
			model=new ArrayList<ODocument>();
		}
		
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			if (kandang!=null) {
				paging.setJumlahData(dao.getCountByColumn(db, KandangdDao.kandang, kandang.getIdentity()));
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

	protected final int NO = 0;
	protected final int TGL = 1;
	protected final int UMUR = 2;
	protected final int POPULASI = 3;
	protected final int MATI = 4;
	protected final int PAKAN = 5;
	protected final int BB = 6;
	protected final int BK = 7;
	protected final int RB = 8;
	protected final int RK = 9;
	protected final int HD = 10;
	protected final int P = 11;
	
	protected final int FCR = 12;
	protected final int K1 = 13;
	protected final int A = 14;
	protected final int K2 = 15;
	
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[16];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[TGL]=App.getT(db, KandangdDao.ftgl);
		namaKolom[UMUR]=App.getT(db, KandangdDao.fumur);
		namaKolom[POPULASI]=App.getT(db, KandangdDao.fpupulasi);
		namaKolom[MATI]=App.getT(db, KandangdDao.fmati);
		namaKolom[PAKAN]=App.getT(db, KandangdDao.fpakan);
		namaKolom[BB]=App.getT(db, KandangdDao.fbutirb);
		namaKolom[BK]=App.getT(db, KandangdDao.fkgb);
		namaKolom[RB]=App.getT(db, KandangdDao.fbutir);
		namaKolom[RK]=App.getT(db, KandangdDao.fkg);
		namaKolom[HD]=App.getT(db, KandangdDao.fhd);
		namaKolom[P]=App.getT(db, KandangdDao.fp);
		namaKolom[FCR]=App.getT(db, KandangdDao.ffcr);
		namaKolom[K1]=App.getT(db, KandangdDao.fket);
		namaKolom[A]=App.getT(db, KandangdDao.fa);
		namaKolom[K2]=App.getT(db, KandangdDao.fket2);
	}
	@Override
	public void setDefaultLebar(JTable table) {
//		if (table!=null) {
//			TableColumnModel t=table.getColumnModel();
//			t.getColumn(NO).setPreferredWidth(27);
//			}
		
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
		} else if (columnIndex == 	TGL) {
			Date dtmp=m.field(KandangdDao.tgl);
			if (dtmp==null) {
				return null;
			}else{
				return App.dateFormat.format(dtmp);
			}
		} else if (columnIndex == UMUR) {
			return m.field(KandangdDao.umur);
		}  else if (columnIndex == POPULASI) {
			return m.field(KandangdDao.pupulasi);
		}  else if (columnIndex == MATI) {
			return m.field(KandangdDao.mati);
		}  else if (columnIndex == PAKAN) {
			double dtmp=m.field(KandangdDao.pakan);
			return App.paymentFormat2.format(dtmp);
		}  else if (columnIndex == BB) {
			return m.field(KandangdDao.bagusbutir);
		}  else if (columnIndex == BK) {
			double dtmp=m.field(KandangdDao.baguskg);
			return App.paymentFormat2.format(dtmp);
		}  else if (columnIndex == RB) {
			return m.field(KandangdDao.retakbutir);
		}  else if (columnIndex == RK) {
			double dtmp=m.field(KandangdDao.retakkg);
			return App.paymentFormat2.format(dtmp);
		}  else if (columnIndex == HD) {
			return App.paymentFormat2.format(App.getKandangdDao().getHD(m));
		}  else if (columnIndex == P) {
			return App.paymentFormat2.format(App.getKandangdDao().getP(m));
		}  else if (columnIndex == FCR) {
			return App.paymentFormat2.format(App.getKandangdDao().getFcr(m));
		}  else if (columnIndex == K1) {
			return App.getKandangdDao().getKet1(m);
		}  else if (columnIndex == A) {
			return App.paymentFormat2.format(App.getKandangdDao().getA(m));
		}  else if (columnIndex == K2) {
			return App.getKandangdDao().getKet2(m);
		}   else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
		setKandang(model);
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		reload(db);
		db.close();
	}



	@Override
	public void initDao() {
		dao=App.getKandangdDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
	public ODocument getKandang() {
		return kandang;
	}
	public void setKandang(ODocument kandang) {
		if (kandang!=null && kandang.field("@class").equals(App.getKandangDao().getClassName())) {
			this.kandang = kandang;
		}
	}

}

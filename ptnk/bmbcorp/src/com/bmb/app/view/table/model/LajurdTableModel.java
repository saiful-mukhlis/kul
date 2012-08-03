package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.KandangdDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdTableModel  extends TableModelAbstract{

	public LajurdTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
	private ODocument lajur;
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (lajur!=null) {
			if (paging != null) {
				int tmp=paging.getCurentHalaman()-1
						* paging.getJumlahPerHalaman();
				if (tmp<0) {
					tmp=0;
				}
				model = getDao().getAllByColumn(db, LajurdDao.lajur, lajur.getIdentity(), tmp
						,
						paging.getJumlahPerHalaman());
			} else {
				model = (List<ODocument>) getDao().getAllByColumn(db, LajurdDao.lajur, lajur.getIdentity());
			}
		}else{
			model=new ArrayList<ODocument>();
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			if (lajur!=null) {
				paging.setJumlahData(dao.getCountByColumn(db, LajurdDao.lajur, lajur.getIdentity()));
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
	protected final int POPULASI = 2;
	protected final int MATI = 3;
	protected final int PB = 4;
	protected final int PR = 5;
	protected final int SB = 6;
	protected final int SR = 7;
	protected final int TB = 8;
	protected final int TR = 9;
	
	protected final int KPB = 10;
	protected final int KPR = 11;
	protected final int KSB = 12;
	protected final int KSR = 13;
	protected final int KTB = 14;
	protected final int KTR = 15;
	
	protected final int HD = 16;
	protected final int P = 17;
	
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[18];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[TGL]=App.getT(db, LajurdDao.ftgl);
		namaKolom[POPULASI]=App.getT(db, LajurdDao.fpupulasi);
		namaKolom[MATI]=App.getT(db, LajurdDao.fmati);
		namaKolom[PB]=App.getT(db, LajurdDao.fbutirpagibagus);
		namaKolom[PR]=App.getT(db, LajurdDao.fbutirpagiretak);
		namaKolom[SB]=App.getT(db, LajurdDao.fbutirsorebagus);
		namaKolom[SR]=App.getT(db, LajurdDao.fbutirsoreretak);
		namaKolom[TB]=App.getT(db, LajurdDao.fbagus);
		namaKolom[TR]=App.getT(db, LajurdDao.fretak);
		
		namaKolom[KPB]=App.getT(db, LajurdDao.fkgpagibagus);
		namaKolom[KPR]=App.getT(db, LajurdDao.fkgpagiretak);
		namaKolom[KSB]=App.getT(db, LajurdDao.fkgsorebagus);
		namaKolom[KSR]=App.getT(db, LajurdDao.fkgsoreretak);
		namaKolom[KTB]=App.getT(db, LajurdDao.fbaguskg);
		namaKolom[KTR]=App.getT(db, LajurdDao.fretakkg);
		namaKolom[HD]=App.getT(db, LajurdDao.fhd);
		namaKolom[P]=App.getT(db, LajurdDao.fp);
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
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
		} else if (columnIndex == 	TGL) {
			Date dtmp=m.field(LajurdDao.tgl);
			if (dtmp==null) {
				return null;
			}else{
				return App.dateFormat.format(dtmp);
			}
		} else if (columnIndex == POPULASI) {
			return m.field(LajurdDao.pupulasi);
		}  else if (columnIndex == MATI) {
			return m.field(LajurdDao.mati);
		}  else if (columnIndex == PB) {
			return m.field(LajurdDao.butirpagibagus);
		}  else if (columnIndex == PR) {
			return m.field(LajurdDao.butirpagiretak);
		}  else if (columnIndex == TB) {
			return App.getLajurdDao().getTotalButirBagus(m);
		}  else if (columnIndex == TR) {
			return App.getLajurdDao().getTotalButirRetak(m);
		}  else if (columnIndex == KPB) {
			return App.paymentFormat2.format(m.field(LajurdDao.kgpagibagus));
		}  else if (columnIndex == KPR) {
			return App.paymentFormat2.format(m.field(LajurdDao.kgpagiretak));
		}  else if (columnIndex == KSB) {
			return App.paymentFormat2.format(m.field(LajurdDao.kgsorebagus));
		}  else if (columnIndex == KSR) {
			return App.paymentFormat2.format(m.field(LajurdDao.kgsoreretak));
		}  else if (columnIndex == KTB) {
			return App.paymentFormat2.format(App.getLajurdDao().getTotalKgBagus(m));
		}  else if (columnIndex == KTR) {
			return App.paymentFormat2.format(App.getLajurdDao().getTotalKgRetak(m));
		}  else if (columnIndex == HD ) {
			return App.paymentFormat2.format(App.getLajurdDao().getHD(m));
		}  else if (columnIndex == P) {
			return App.paymentFormat2.format(App.getLajurdDao().getP(m));
		}    else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
		setLajur(model);
	}





	@Override
	public void initDao() {
		dao=App.getLajurdDao();
	}
	@Override
	public List getModel2() {
		return null;
	}
	public ODocument getLajur() {
		return lajur;
	}
	public void setLajur(ODocument lajur) {
		if (lajur!=null && lajur.field("@class").equals(App.getLajurDao().getClassName())) {
			this.lajur = lajur;
			ODatabaseDocumentTx db=App.getDbd();
			ODatabaseRecordThreadLocal. INSTANCE.set(db);
			reload(db);
			db.close();
		}else{
			this.lajur = null;
			ODatabaseDocumentTx db=App.getDbd();
			ODatabaseRecordThreadLocal. INSTANCE.set(db);
			reload(db);
			db.close();
		}
	}

}

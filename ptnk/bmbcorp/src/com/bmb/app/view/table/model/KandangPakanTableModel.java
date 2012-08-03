package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangPakanTableModel  extends TableModelAbstract{

	public KandangPakanTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
	protected Date tgla;
	protected List<Double> pakan;
	protected List<Integer> umur;
	protected List<ODocument> kandangds;
	protected List<Boolean> editableUmur;
	
	

	protected final int NO = 0;
	protected final int CODE = 1;
	protected final int NAMA = 2;
	protected final int PAKAN = 3;
	protected final int UMUR = 4;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[5];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[CODE]=App.getT(db, "Kode");
		namaKolom[NAMA]=App.getT(db, "Kandang");
		namaKolom[PAKAN]=App.getT(db, "Pakan");
		namaKolom[UMUR]=App.getT(db, "Umur");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(CODE).setPreferredWidth(27);
			t.getColumn(NAMA).setPreferredWidth(100);
			t.getColumn(PAKAN).setPreferredWidth(27);
			t.getColumn(UMUR).setPreferredWidth(27);
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
		} 
		else if (columnIndex == CODE) {
			return m.field(KandangDao.code);
		} 
		else if (columnIndex == NAMA) {
			return m.field(KandangDao.name);
		}
		else if (columnIndex == PAKAN) {
			double tmp=pakan.get(rowIndex);
			if (tmp==0) {
				return "";
			}else{
				return tmp;
			}
		} 
		else if (columnIndex == UMUR) {
			int tmp=umur.get(rowIndex);
			if (tmp==0) {
				return "";
			}else{
				return tmp;
			}
		} 
		else {
			return null;
		}
	}
	


	@Override
	public void editObjModel(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getKandangdDao().getClassName())) {
			Date tgltmp=model.field(KandangdDao.tgl);
			if (tgltmp!=null) {
				if (!tgltmp.equals(tgla)) {
					setTgla(tgltmp);
				}
			}
		}
	}





	@Override
	public void initDao() {
		dao=App.getKandangDao();
	}
	@Override
	public List getModel2() {
		return kandangds;
	}
	public Date getTgla() {
		return tgla;
	}
	public void setTgla(Date tgla) {
		this.tgla = tgla;
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		tanggalBerubah(db);
		db.close();
	}

	public void tanggalBerubah(ODatabaseDocumentTx db){
		kandangds=new ArrayList<ODocument>();
		int i=0;
		for (ODocument o : model) {
			ODocument tmp=App.getKandangdDao().getOne(db, KandangdDao.kandang, o.getIdentity(), KandangdDao.tgl, tgla);
			kandangds.add(tmp);
			if (tmp!=null) {
				Double pakantmp=tmp.field(KandangdDao.pakan);
				if (pakantmp!=null) {
					pakan.set(i, pakantmp);
				}else{
					pakan.set(i, (double) 0);
				}
				Integer umurtmp=tmp.field(KandangdDao.umur);
				if (umurtmp!=null) {
					umur.set(i, umurtmp);
				}else{
					umur.set(i, 0);
				}
				
				ODocument kandang=model.get(i);
				ODocument tmp2=App.getKandangdDao().getOneOrderByTgl(db, kandang.getIdentity());
				if (tmp2.getIdentity().equals(tmp.getIdentity())) {
					//awal
					editableUmur.set(i, true);
				}else{
					editableUmur.set(i, false);
					pakan.set(i, (double) 0);
					umur.set(i, 0);
				}
				
			}else{
				editableUmur.set(i, false);
			}
			
			i++;
		}
		fireTableDataChanged();
	}
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAll(db, tmp
					,
					paging.getJumlahPerHalaman());
			
			
		} else {
			model = (List<ODocument>) getDao().getAll(db);
		}
		
		pakan=new ArrayList<Double>();
		umur=new ArrayList<Integer>();
		editableUmur=new ArrayList<Boolean>();
		for (ODocument o : model) {
			pakan.add((double) 0);
			umur.add(0);
			editableUmur.add(false);
		}
		if (tgla!=null) {
			tanggalBerubah(db);
		}
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (tgla!=null) {
			if (columnIndex==3) {
				return true;
			}else if(columnIndex==4){
				return editableUmur.get(rowIndex);
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex==3) {
			//pakan
			try {
				String x=aValue+"";
				if (!x.equals("")) {
					double tmp=Double.parseDouble(aValue+"");
					if (tmp>0) {
						ODocument otmp=kandangds.get(rowIndex);
						otmp.field(KandangdDao.pakan, tmp, OType.DOUBLE);
						otmp.save();
						pakan.set(rowIndex, tmp);
						fireTableCellUpdated(rowIndex, columnIndex);
					}
				}
			} catch (Exception e) {
				App.printErr(e);
			}
		}else if (columnIndex==4) {
			//umur
			try {
				String x=aValue+"";
				if (!x.equals("")) {
					int tmp=Integer.parseInt(aValue+"");
					
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				    
					ODocument otmp=kandangds.get(rowIndex);
					Date tgla2=otmp.field(KandangdDao.tgl);
					otmp.field(KandangdDao.umur, tmp, OType.INTEGER);
					try {
						db.begin();
						otmp.save();
						ODocument ktmp=otmp.field(KandangdDao.kandang);
						List<ODocument> os=App.getKandangdDao().getAllSesudahOrderByTgl(db, ktmp, tgla2);
						int i=0;
						for (ODocument oDocument : os) {
							Date tglini=oDocument.field(KandangdDao.tgl);
							Calendar cini=Calendar.getInstance();
							cini.setTime(tglini);
							long angkatglini=cini.getTimeInMillis();
							if (i==0) {
								Calendar cawal=Calendar.getInstance();
								cawal.setTime(tgla2);
								long angkatglawal=cawal.getTimeInMillis();
								long selisih=angkatglini-angkatglawal;
								long daySelisih=selisih/86400000;
								int um=(int) (daySelisih+tmp);
								oDocument.field(KandangdDao.umur, um, OType.INTEGER);
								oDocument.save();
							}else{
								ODocument oatas=os.get(i-1);
								int umatas=oatas.field(KandangdDao.umur);
								Date tglatas=oatas.field(KandangdDao.tgl);
								Calendar catas=Calendar.getInstance();
								catas.setTime(tglatas);
								long ankaatas=catas.getTimeInMillis();
								long selisih=angkatglini-ankaatas;
								long daySelisih=selisih/86400000;
								int um=(int) (daySelisih+umatas);
								oDocument.field(KandangdDao.umur, um, OType.INTEGER);
								oDocument.save();
							}
							i++;
						}
						db.commit();
						umur.set(rowIndex, tmp);
						fireTableCellUpdated(rowIndex, columnIndex);
					} catch (Exception e) {
						App.printErr(e);
						db.rollback();
					}finally{
						db.close();
					}
					
				}
				
			} catch (Exception e) {
				App.printErr(e);
			}
		}
	}
	
	
	
	
}

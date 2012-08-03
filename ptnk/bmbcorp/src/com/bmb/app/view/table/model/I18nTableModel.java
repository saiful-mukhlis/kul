package com.bmb.app.view.table.model;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.I18nDao;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class I18nTableModel  extends TableModelAbstract{

	public I18nTableModel(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
	protected final int NAMA = 1;
	protected final int TRANSLATE = 2;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[3];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[NAMA]=App.getT(db, I18nDao.fname);
		namaKolom[TRANSLATE]=App.getT(db, I18nDao.ftranslate);
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(27);
			t.getColumn(NAMA).setPreferredWidth(27);
			t.getColumn(TRANSLATE).setPreferredWidth(27);
			}
		
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
		} else if (columnIndex == NAMA) {
			return m.field(I18nDao.name);
		} else if (columnIndex == TRANSLATE) {
			return m.field(I18nDao.translate);
		}   else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		if (model.field("@class").equals(dao.getClassName())) {
			this.model.add(model);
		fireTableDataChanged();
		}
	}

	@Override
	public void editObjModel(ODocument model) {
	}

	@Override
	public void initDao() {
		dao=App.getI18nDao();
	}
	@Override
	public List getModel2() {
		return null;
	}

}

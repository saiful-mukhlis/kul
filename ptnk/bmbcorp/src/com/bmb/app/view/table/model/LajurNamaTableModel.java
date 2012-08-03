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

public class LajurNamaTableModel  extends LajurTableModel{

	public LajurNamaTableModel(ODatabaseDocumentTx db) {
		super(db);
	}

	protected final int NO = 0;
	protected final int NAMA = 1;
	protected final int NAMA2 = 2;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[3];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[NAMA]=App.getT(db, "Kandang");
		namaKolom[NAMA2]=App.getT(db, "Lajur");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(5);
			t.getColumn(NAMA).setPreferredWidth(27);
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
		}  else if (columnIndex == NAMA) {
			return m.field(LajurDao.kandang);
		}  else if (columnIndex == NAMA2) {
			return m.field(LajurDao.name);
		}  else {
			return null;
		}
	}

}

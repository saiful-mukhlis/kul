package com.bmb.app.view.table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.bmb.app.view.abst.table.model.TableModelAbstract;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrTableModel  extends TableModelAbstract{

	public UsrTableModel(ODatabaseDocumentTx db) {
		super(db);
	}
	
	protected List<ODocument> grps;

	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		super.loadDataModel(db);
		grps=new ArrayList<ODocument>();
		for (ODocument oDocument : model) {
			ODocument tmp=oDocument.field("grp");
			tmp.field(GrpDao.name);
			grps.add(tmp );
		}
	}

	protected final int NO = 0;
	protected final int NAMA = 1;
	protected final int USERNAME = 2;
	protected final int GROUP = 3;
	public void initNamaKolom(ODatabaseDocumentTx db){
		namaKolom=new String[4];
		namaKolom[NO]=App.getT(db, "No");
		namaKolom[NAMA]=App.getT(db, "Nama");
		namaKolom[USERNAME]=App.getT(db, "Username");
		namaKolom[GROUP]=App.getT(db, "Group");
	}
	@Override
	public void setDefaultLebar(JTable table) {
		if (table!=null) {
			TableColumnModel t=table.getColumnModel();
			t.getColumn(NO).setPreferredWidth(10);
			t.getColumn(NAMA).setPreferredWidth(75);
			t.getColumn(USERNAME).setPreferredWidth(50);
			t.getColumn(GROUP).setPreferredWidth(50);
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
		} else if (columnIndex == NAMA) {
			return m.field("nikName");
		} else if (columnIndex == USERNAME) {
			return m.field("username");
		} else if (columnIndex == GROUP) {
			ODocument g=grps.get(rowIndex);
				return g.field("name");
		}  else {
			return null;
		}
	}
	

	@Override
	public void addObjModel(ODocument model) {
		if (model.field("@class").equals("Usr")) {
			this.model.add(model);
		}else if (model.field("@class").equals("Grp")) {
			this.grps.add(model);
		}
		fireTableDataChanged();
	}

	@Override
	public void editObjModel(ODocument model) {
	}





	@Override
	public void initDao() {
		dao=App.getUsrDao();
	}
	@Override
	public List getModel2() {
		return grps;
	}

}

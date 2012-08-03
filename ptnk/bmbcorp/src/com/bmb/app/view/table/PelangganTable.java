package com.bmb.app.view.table;


import org.bmb.app.view.abst.TableAbstract;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PelangganTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
	}


	




}

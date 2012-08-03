package com.bmb.app.view.table;


import org.bmb.app.view.abst.TableAbstract;
import org.jdesktop.swingx.JXTable;

import com.bmb.app.view.table.model.KandangTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new KandangTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
	}



	


}

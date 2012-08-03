package com.bmb.app.view.table;


import org.bmb.app.view.abst.TableAbstract;

import com.bmb.app.view.table.model.I18nTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class I18nTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new I18nTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
	}



	


}

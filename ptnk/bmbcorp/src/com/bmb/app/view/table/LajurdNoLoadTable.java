package com.bmb.app.view.table;


import org.bmb.app.view.abst.TableAbstract;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.KandangTableModel;
import com.bmb.app.view.table.model.LajurdNoLoadTableModel;
import com.bmb.app.view.table.model.LajurdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdNoLoadTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new LajurdNoLoadTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		tableModel.editObjModel(model);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		if (model.field("@class").equals(App.getLajurdDao().getClassName())) {
			tableModel.getModel().add(model);
			tableModel.fireTableDataChanged();
		}
	}

	


}

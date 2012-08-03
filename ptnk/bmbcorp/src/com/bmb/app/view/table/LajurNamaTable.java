package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.KandangTableModel;
import com.bmb.app.view.table.model.LajurNamaTableModel;
import com.bmb.app.view.table.model.LajurTableModel;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.UsrTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurNamaTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new LajurNamaTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
	}




	


}

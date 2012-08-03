package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.PenjualanTableModel;
import com.bmb.app.view.table.model.PenjualanhTableModel;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanhTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PenjualanhTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getPenjualanbDao().getClassName())) {
			tableModel.editObjModel(model);
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			tableModel.reload(db);
			db.close();
		}else{
			if (model==null) {
				tableModel.editObjModel(model);
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				tableModel.reload(db);
				db.close();
			}
			tableModel.fireTableDataChanged();
		}
	}



}

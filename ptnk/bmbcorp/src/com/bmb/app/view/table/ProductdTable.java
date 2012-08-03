package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.ProductTableModel;
import com.bmb.app.view.table.model.ProductdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductdTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new ProductdTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void modelWidgetAdd(ODocument model) {
		tableModel.addObjModel(model);
	}





}

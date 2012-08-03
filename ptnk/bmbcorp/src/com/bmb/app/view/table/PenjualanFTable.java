package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;
import org.jdesktop.swingx.JXTable;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.PenjualanFTableModel;
import com.bmb.app.view.table.model.PenjualanTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanFTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PenjualanFTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		//tableModel.editObjModel(model);
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		tableModel.addObjModel(model);
	}

	
	public void selectedValid(int i) {
//		if (widgets.size() != 0) {
//			for (WidgetAdapter view : widgets) {
//				if (view!=null) {
//					view.modelWidgetChange((ODocument) tableModel.getModel().get(
//							table.convertRowIndexToModel(i)));
//					view.modelWidgetChange((ODocument) tableModel.getModel2().get(
//							table.convertRowIndexToModel(i)));
//				}
//			}
//		}
	}

//	public void initTable() {
//		setTable(new JXTable(tableModel));
//		if (getTable() instanceof JXTable) {
//			setJXTable((JXTable) getTable());
//			JXTable t=(JXTable) getTable();
//			while (t.getColumnCount()>4) {
//				t.getColumnExt(t.getColumnCount()-1).setVisible(false);
//				
//			}
//			t.getColumnExt(0).setVisible(false);
//			t.getColumnExt(0).setVisible(false);
//			
//		}
//	}



}

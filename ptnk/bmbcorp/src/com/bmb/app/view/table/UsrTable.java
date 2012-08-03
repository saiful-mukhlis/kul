package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.UsrTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new UsrTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
	}


	@Override
	public void modelWidgetAdd(ODocument model) {
			tableModel.addObjModel(model);
			selected();
		
	}
	public void selectedNotValid(){
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				view.modelWidgetChange(null);
			}
		}
	}

	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				int indtmp=table.convertRowIndexToModel(i);
				view.modelWidgetChange((ODocument) tableModel.getModel().get(indtmp));
				view.modelWidgetChange((ODocument) tableModel.getModel2().get(indtmp));
			}
		}
	}

	int indexBerubah=-1;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model!=null && model.field("@class").equals(App.getUsrDao().getClassName())) {
			indexBerubah=this.tableModel.getModel().indexOf(model);
		}
		if (model!=null && model.field("@class").equals(App.getGrpDao().getClassName())) {
			if (indexBerubah!=-1) {
				tableModel.getModel2().set(indexBerubah, model);
			}
		}
		tableModel.fireTableDataChanged();
	}

}

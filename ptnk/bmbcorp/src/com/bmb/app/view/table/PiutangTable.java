package com.bmb.app.view.table;

import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.PiutangTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new PiutangTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
	}

	public void selectedValid(int i) {
		if (widgets.size() != 0) {
			for (WidgetAdapter view : widgets) {
				if (view!=null) {
					view.modelWidgetChange((ODocument) tableModel.getModel().get(
							table.convertRowIndexToModel(i)));
					view.modelWidgetChange((ODocument) tableModel.getModel2().get(
							table.convertRowIndexToModel(i)));
				}
			}
		}
	}



}

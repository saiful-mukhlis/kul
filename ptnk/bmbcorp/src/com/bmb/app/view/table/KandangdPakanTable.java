package com.bmb.app.view.table;


import java.util.Date;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;
import org.jdesktop.swingx.JXTable;

import com.bmb.app.global.App;
import com.bmb.app.view.master.KandangdInputPakanMaster;
import com.bmb.app.view.table.model.KandangPakanTableModel;
import com.bmb.app.view.table.model.KandangTableModel;
import com.bmb.app.view.table.model.KandangdTableModel;
import com.bmb.app.view.table.model.LajurdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdPakanTable extends TableAbstract{

	private KandangdInputPakanMaster master;
	

	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new KandangPakanTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
		tableModel.editObjModel(model);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		tableModel.editObjModel(model);
	}
	
	public void selectedValid(int i) {
		if (master!=null) {
			Date dateM=master.getTgla().getDate();
			Date dateT=((KandangPakanTableModel)tableModel).getTgla();
			if (!dateM.equals(dateT)) {
				((KandangPakanTableModel)tableModel).setTgla(dateM);
			}
		}
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

	public KandangdInputPakanMaster getMaster() {
		return master;
	}

	public void setMaster(KandangdInputPakanMaster master) {
		this.master = master;
	}

//	public void initTable() {
//		setTable(new JXTable(tableModel));
//		if (getTable() instanceof JXTable) {
//			setJXTable((JXTable) getTable());
//			JXTable t=(JXTable) getTable();
////			for (int i = 0; i < t.getColumnCount(); i++) {
////				t.getColumnExt(i).setVisible(false);
////				App.info("asd"+i);
////			}
//			while (t.getColumnCount()>2) {
//				t.getColumnExt(t.getColumnCount()-1).setVisible(false);
//				
//			}
//			t.getColumnExt(0).setVisible(false);
//			
//			//t.getColumnExt(1).setVisible(true);
//		}
//	}

	


}

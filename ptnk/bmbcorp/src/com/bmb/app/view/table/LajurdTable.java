package com.bmb.app.view.table;



import java.util.List;

import javax.swing.table.TableColumn;

import org.bmb.app.view.abst.TableAbstract;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.TableColumnExt;

import com.bmb.app.view.table.model.LajurdTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new LajurdTableModel(db);
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

	public void initTable() {
		setTable(new JXTable(tableModel));
		if (getTable() instanceof JXTable) {
			setJXTable((JXTable) getTable());
//			JXTable t=(JXTable) getTable();
////			for (int i = 0; i < t.getColumnCount(); i++) {
////				t.getColumnExt(i).setVisible(false);
////				App.info("asd"+i);
////			}
//			while (t.getColumnCount()>2) {
//				t.getColumnExt(t.getColumnCount()-1).getIdentifier();
//				t.getColumnExt(t.getColumnCount()-1).setVisible(false);
//				
//			}
//			t.getColumnExt(0).setVisible(false);
			
			//t.getColumnExt(1).setVisible(true);
		}
		
		setSimple();
	}
	
	
	public void setSimple(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(false);
				}
			}
			
			TableColumnExt tcx=t.getColumnExt(x[1]);
			if (tcx!=null) {
				tcx.setVisible(true);
			}
			
			
			
			
		}
	}
	public void setShowAll(){
		if (getTable() instanceof JXTable) {
			JXTable t=(JXTable) getTable();
			String [] x=tableModel.getNamaKolom();
			for (String string : x) {
				TableColumnExt tcx=t.getColumnExt(string);
				if (tcx!=null) {
					tcx.setVisible(true);
				}
			}
			
		}
	}


}

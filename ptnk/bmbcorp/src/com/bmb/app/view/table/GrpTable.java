package com.bmb.app.view.table;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.bmb.app.view.abst.TableAbstract;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.global.App;
import com.bmb.app.view.table.model.GrpTableModel;
import com.bmb.app.view.table.model.KandangTableModel;
import com.bmb.app.view.table.model.PelangganTableModel;
import com.bmb.app.view.table.model.UsrTableModel;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpTable extends TableAbstract{


	@Override
	public void initTableModel(ODatabaseDocumentTx db) {
		tableModel=new GrpTableModel(db);
		tableModel.setTable(this);
		
	}

	@Override
	public void load(ODocument model) {
	}


	public void aksiDelete(ODatabaseDocumentTx db) {
		if (getTable() != null) {
			int i = getTable().getSelectedRow();
			if (i != -1) {
				if (JOptionPane.showConfirmDialog(null,
						App.getT(db, "Apakah Anda yakin akan menghapus? \nMenghapus Hak Akses akan menghapus Pegawai yang memililki Hak Akses ini."),
						App.getT(db, "Konfirmasi Delete"),
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
					int itmp=table.convertRowIndexToModel(i);
					ODocument tmp=(ODocument) tableModel.getModel().get(itmp	);
					try {
//						tmp.delete();
						tableModel.getDao().delete(db, tmp);
						tableModel.getModel().remove(itmp);
						tableModel.fireTableDataChanged();
						selected();
					} catch (Exception e) {
						App.printErr(e);
					}
				}
			} 
		}

	}
	


}

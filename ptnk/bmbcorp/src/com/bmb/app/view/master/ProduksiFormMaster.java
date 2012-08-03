package com.bmb.app.view.master;

import javax.swing.JLabel;

import org.bmb.app.view.abst.master.MasterFAbstract;
import org.bmb.app.view.form.ProduksiForm;

import com.bmb.app.global.App;
import com.bmb.app.view.table.LajurdNoLoadTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProduksiFormMaster extends MasterFAbstract {

	

	public int getDevide() {
		Double tmp = App.getW()*0.4;
		return tmp.intValue();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		form = new ProduksiForm();
	}


	@Override
	public void initTable(ODatabaseDocumentTx db) {
		table = new LajurdNoLoadTable();
		table.build(db);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		//tampilan default
		form.getPanel().setVisible(true);
		tampilkanDefault();
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub

	}
	
	public void initLabelTitle(ODatabaseDocumentTx db){
		label = new JLabel(App.getIcon(db, "icon pegawai 16"));
		label.setText(App.getT(db, "Input Produksi"));
	}

	public void tampilkanDefault() {
		splitPane.setDividerLocation(260);
	}

}

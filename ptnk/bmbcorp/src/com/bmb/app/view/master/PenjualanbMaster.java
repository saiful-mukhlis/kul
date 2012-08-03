package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.adapter.FormAdapter;
import org.bmb.app.view.adapter.FormEditAdapter;
import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.builder.FormBuilder;

import com.bmb.app.global.App;
import com.bmb.app.view.table.UsrTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanbMaster extends MasterAbstract {

	

	public int getDevide() {
		Double tmp = App.getW()*0.4;
		App.info(tmp.intValue()+"");
		return tmp.intValue();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
//		setForm(new UsrForm());
//		setEditForm(new UsrEditForm());
//		setViewForm(new UsrViewForm());
	}


	public void initTable(ODatabaseDocumentTx db) {
		table = new UsrTable();
		table.build(db);
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		//tampilan default
//		cardLayout.show(cardPanel, "lihat");
		aksiLihat();

	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tampilkanDefault() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEditForm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setForm() {
		// TODO Auto-generated method stub
		
	}

}

package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.KandangComponetEdit;
import org.bmb.app.view.form.KandangComponetNew;
import org.bmb.app.view.form.KandangComponetView;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.view.table.KandangTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class KandangMaster extends MasterAbstract {
	
	public KandangMaster() {
		super();
		lebar=0.2;
		title="   Master Data Kandang";
		icon="icon kandang 16";
		viewForm=new ComponentView(new KandangComponetView());//LajurViewForm();
		table = new KandangTable();
	}
	private LajurMaster lajurMaster;
	private JSplitPane ao;

	public void tampilkanForm() {
		tampilkanDefault();
		ao.setDividerLocation(200);
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		
		ao=new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				viewForm.getPanel(), lajurMaster.getPanel());
		ao.setDividerLocation(200);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), ao);

		splitPane.setDividerSize(1);
		

		ao.setDividerSize(1);

		splitPane.setOneTouchExpandable(true);
		
		ao.setBackground(Color.RED);
		tampilkanDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		lajurMaster=new LajurMaster();
		lajurMaster.build(db);
		table.addWidgetChange(lajurMaster.getTable());
		table.addWidgetChange(lajurMaster.getForm());
		table.addWidgetChange(lajurMaster);
		setViewForm(new ComponentView(new KandangComponetView()));
	}
	
	public void aksiTambah() {
		if (form==null) {
			setForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			form.build(db);
			form.addWidgetModel(table);
			db.close();
		}
		ao.setTopComponent(form.getPanel());
		tampilkanForm();
		getForm().aksiReset();
	}

	public void aksiLihat() {
		tampilkanForm();
		ao.setTopComponent(viewForm.getPanel());
	}

	public void aksiEdit() {
		if (editForm==null) {
			setEditForm();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			editForm.build(db);
			db.close();
			table.addWidgetChange(getEditForm());
			editForm.addWidgetModel(table);
			editForm.addWidgetModel(this);
			table.selected();
		}
		tampilkanForm();
		ao.setTopComponent(editForm.getPanel());
	}



	@Override
	public void setEditForm() {
		setEditForm(new ComponentEdit(new KandangComponetEdit()));
	}



	@Override
	public void setForm() {
		setForm(new ComponentNew(new KandangComponetNew()));
		
	}
	
	
	public void changeHakAkses() {
		lajurMaster.changeHakAkses();
		tambah.setEnabled(getAdd());
		edit.setEnabled(getEdit());
		hapus.setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.KANDANG_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.KANDANG_DEL;
	}

	public boolean getLihat() {
		return DataUser.KANDANG_VIEW;
	}

	public boolean getEdit() {
		return DataUser.KANDANG_EDIT;
	}

}

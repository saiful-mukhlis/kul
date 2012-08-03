package com.bmb.app.view.master;



import javax.swing.JLabel;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.LajurComponetEdit;
import org.bmb.app.view.form.LajurComponetNew;
import org.bmb.app.view.form.LajurComponetView;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.view.table.LajurTable;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurMaster extends MasterAbstract {
	
	
	public LajurMaster() {
		super();
		lebar=0.4;
		title="   Data Lajur";
		icon="icon kandang 16";
		viewForm=new ComponentView(new LajurComponetView());
		table = new LajurTable();
	}

	


	public void aksiReload() {
		super.aksiReload();
		modelWidgetChange(kandang);
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		tambah.setEnabled(false);
	}

	@Override
	public void setEditForm() {
		setEditForm(new ComponentEdit(new LajurComponetEdit()));
	}

	@Override
	public void setForm() {
		setForm(new ComponentNew(new LajurComponetNew()));
		
	}

	private ODocument kandang;
	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		aksiLihat();
		if (model==null) {
			edit.setEnabled(false);
			lihat.setEnabled(false);
			hapus.setEnabled(false);
			tambah.setEnabled(false);
		}else{
			
			if (model.field("@class").equals(App.getLajurDao().getClassName())) {
				edit.setEnabled(true);
				lihat.setEnabled(true);
				hapus.setEnabled(true);
				viewForm.modelWidgetChange(model);
			}else if(model.field("@class").equals(App.getKandangDao().getClassName())){
				if (form==null) {
					setForm();
					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
					form.build(db);
					form.addWidgetModel(table);
					db.close();
				}
				kandang=model;
				getForm().modelWidgetChange(model);
				tambah.setEnabled(true);
			}
		}

	}
	
	
	public void changeHakAkses() {
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

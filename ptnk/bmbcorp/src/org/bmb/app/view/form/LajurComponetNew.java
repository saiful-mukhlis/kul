package org.bmb.app.view.form;


import org.bmb.app.view.adapter.ComponetNewAdapter;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurComponetNew extends LajurComponetFormDefault implements
		ComponetNewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Menambahkan Data Lajur Baru";
		icon = "icon tambah 16";
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	private ODocument kandang;

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}


	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 5, 7);
		builder.append( reset, 7, 7);
	}
	
	
	
	
	
	
	
	
	

	@Override
	public void aksiReset() {
		clearText(name);
		clearText(note);
		code.setText("AUTO");
	}
	public void setFocusEnter(){
		setFocusEnter(name, note);
		//setFocusEnter(note, save);
		setFocusEnter(save, reset);
		setFocusEnter(reset, code);
		setFocusEnter(code, name);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		return App.getLajurDao().factoryModel(kandang, code.getText(), name.getText(), note.getText());
	}
	public boolean validate(ODatabaseDocumentTx db){
		if (!(code.getText().equalsIgnoreCase("AUTO") || code.getText().trim().equalsIgnoreCase("")) ) {
			long tmp=App.getLajurDao().getCountByColumn(db, LajurDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LajurDao.fcode);
				return false;
			}
		}
		if (kandang==null) {
			App.showErrorFieldEmpty(db, "Kandang");
			return false;
		}
		
		if (!validate(db, name, LajurDao.fname)) {
			return false;
		}
		
		long tmp=App.getLajurDao().getCountByColumn(db, LajurDao.name, name.getText());
		if (tmp>0) {
			App.showErrorDataSudahAda(db, LajurDao.fname);
			return false;
		}
		
//		if (!validate(db, note, LajurDao.fnote)) {
//			return false;
//		}
		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		dao.save(db, model);
	}



	public ODocument getKandang() {
		return kandang;
	}

	public void setKandang(ODocument kandang) {
		if (kandang==null) {
			this.kandang =null;
		}else{
			if (kandang.field("@class").equals(App.getKandangDao().getClassName())) {
				this.kandang = kandang;
			}
		}
	}


	@Override
	public void modelWidgetChange(ODocument model) {
		setKandang(model);
	}

	
}

package org.bmb.app.view.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.bmb.app.view.adapter.ComponetNewAdapter;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganComponetNew extends PelangganComponetFormDefault implements
		ComponetNewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Menambahkan Data Pelanggan Baru";
		icon = "icon tambah 16";
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	public void afterOk() {
		name.requestFocus();
	}


	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}


	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append(save, 5, 13);
		builder.append(reset, 7, 13);
	}
	
	
	
	
	
	
	
	
	

	public void aksiReset() {
		code.setText("AUTO");
		clearText(name);
		clearText(pemilik);
		clearText(notelp);
		clearText(alamat);
	}
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, pemilik);
		setFocusEnter(pemilik, notelp);
		setFocusEnter(notelp, alamat);
		setFocusEnter(save, code);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */
	public ODocument createDataBaru() {
		return App.getPelangganDao().factoryModel(code.getText(),
				name.getText(), pemilik.getText(), notelp.getText(),
				alamat.getText());
	}
	public boolean validate(ODatabaseDocumentTx db){
		if (!(code.getText().equalsIgnoreCase("AUTO") || code.getText().trim().equalsIgnoreCase("")) ) {
			long tmp=App.getPelangganDao().getCountByColumn(db, PelangganDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, PelangganDao.fcode);
				return false;
			}
		}
		if (!validate(db, name, "Nama")) {
			return false;
		}
		long tmp=App.getPelangganDao().getCountByColumn(db, PelangganDao.name, name.getText());
		if (tmp>0) {
			App.showErrorDataSudahAda(db, PelangganDao.fname);
			return false;
		}
		
		if (!validate(db, pemilik, PelangganDao.fpemilik)) {
			return false;
		}
		
		return true;
	}
	public void save(ODatabaseDocumentTx db, ODocument model) {
		dao.save(db, model);
	}


	
}

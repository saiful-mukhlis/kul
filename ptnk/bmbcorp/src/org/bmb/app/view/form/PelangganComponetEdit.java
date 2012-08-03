package org.bmb.app.view.form;


import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.ComponetNewAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganComponetEdit extends PelangganComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Edit Data Pelanggan";
		icon = "icon edit 16";
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			setContentComponent(model);
		}
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 13);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(code, name);
		setFocusEnter(name, pemilik);
		setFocusEnter(pemilik, notelp);
		setFocusEnter(notelp, alamat);
		setFocusEnter(save, code);
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		if (!code.getText().equalsIgnoreCase((String) model.field(PelangganDao.code))) {
			long tmp=App.getPelangganDao().getCountByColumn(db, PelangganDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, PelangganDao.fcode);
				return false;
			}
		}
		if (!validate(db, name, PelangganDao.fname)) {
			return false;
		}
		if (!validate(db, pemilik, PelangganDao.pemilik)) {
			return false;
		}
		return true;
	}
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			tmp.field(PelangganDao.code, code.getText());
			tmp.field(PelangganDao.name, name.getText());
			tmp.field(PelangganDao.pemilik, pemilik.getText());
			tmp.field(PelangganDao.notelp, notelp.getText());
			tmp.field(PelangganDao.alamat, alamat.getText());
			
			try {
				tmp.save();
				for (WidgetAdapter w: widgeds) {
					w.modelWidgetChange(tmp);
					this.model=tmp;
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				
			}finally{
				db.close();
			}
			
		}
	
		
	}


	
}

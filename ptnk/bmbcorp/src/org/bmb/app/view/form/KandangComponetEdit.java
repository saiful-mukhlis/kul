package org.bmb.app.view.form;


import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.ComponetNewAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangComponetEdit extends KandangComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Edit Data Kandang";
		icon = "icon tambah 16";
		
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
		builder.append( save, 7, 7);
	}
	
	
	public void setFocusEnter(){
		setFocusEnter(name, note);
		//setFocusEnter(note, save);
		setFocusEnter(save, reset);
		setFocusEnter(reset, code);
		setFocusEnter(code, name);
	}
	
	
	public boolean validate(ODatabaseDocumentTx db){
		if (!code.getText().equalsIgnoreCase((String) model.field(KandangDao.code))) {
			long tmp=App.getKandangDao().getCountByColumn(db, KandangDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, KandangDao.fcode);
				return false;
			}
		}
		if (!validate(db, name, KandangDao.fname)) {
			return false;
		}
		
		if (!name.getText().equalsIgnoreCase((String) model.field(KandangDao.name))) {
			long tmp=App.getKandangDao().getCountByColumn(db, KandangDao.name, name.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, KandangDao.fname);
				return false;
			}
		}
		
		
//		if (!validate(db, note, KandangDao.fnote)) {
//			return false;
//		}
		return true;
	}
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			tmp.field(KandangDao.code, code.getText());
			tmp.field(KandangDao.name, name.getText());
			tmp.field(KandangDao.note, note.getText());
			
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

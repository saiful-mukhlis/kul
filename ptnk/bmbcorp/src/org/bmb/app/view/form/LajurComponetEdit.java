package org.bmb.app.view.form;


import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.dao.LajurDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurComponetEdit extends LajurComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Edit Data Lajur";
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
		if (!code.getText().equalsIgnoreCase((String) model.field(LajurDao.code))) {
			long tmp=App.getLajurDao().getCountByColumn(db, LajurDao.code, code.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LajurDao.fcode);
				return false;
			}
		}
		if (!validate(db, name, LajurDao.fname)) {
			return false;
		}
		
		if (!name.getText().equalsIgnoreCase((String) model.field(LajurDao.name))) {
			long tmp=App.getLajurDao().getCountByColumn(db, LajurDao.name, name.getText());
			if (tmp>0) {
				App.showErrorDataSudahAda(db, LajurDao.fname);
				return false;
			}
		}
		
		
//		if (!validate(db, note, LajurDao.fnote)) {
//			return false;
//		}
		return true;
	}
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			tmp.field(LajurDao.code, code.getText());
			tmp.field(LajurDao.name, name.getText());
			tmp.field(LajurDao.note, note.getText());
			
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

package org.bmb.app.view.form;

import org.bmb.app.view.adapter.ComponetViewAdapter;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdComponetView extends KandangdComponetFormDefault implements
		ComponetViewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Lihat Data Produksi Kandang";
		icon = "icon lihat 16";
		
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else if (modelIsTrue(model)) {
			setKandangdModel(model);
			setContentComponent(model);
		}else if (model.field("@class").equals(App.getKandangDao().getClassName())) {
			setContentComponent(model);
		}
		
	}
	
}

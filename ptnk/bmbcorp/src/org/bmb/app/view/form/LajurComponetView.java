package org.bmb.app.view.form;

import java.awt.Component;

import org.bmb.app.view.adapter.ComponetViewAdapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurComponetView extends LajurComponetFormDefault implements
		ComponetViewAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Lihat Data Lajur";
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
			setContentComponent(model);
		}
		
	}


	
}

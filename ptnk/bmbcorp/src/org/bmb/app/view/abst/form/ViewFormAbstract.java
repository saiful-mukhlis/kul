package org.bmb.app.view.abst.form;

import org.bmb.app.view.adapter.ViewAdapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public abstract class ViewFormAbstract extends FormAbstract  implements ViewAdapter {
	@Override
	public void modelWidgetChange(ODocument model) {
		load(model);
	}
	@Override
	public void modelWidgetAdd(ODocument model) {
	}
	
	@Override
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
	}

	public void initComponent(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);
		buildForm(db);
	}
	public void buildForm(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}
}

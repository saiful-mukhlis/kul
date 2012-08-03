package org.bmb.app.view.form;

import java.awt.Component;

import javax.swing.JPanel;

import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.FormEditAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ComponentEdit implements FormEditAdapter{
	private ComponetEditAdapter edit;

	public ComponentEdit(ComponetEditAdapter edit) {
		super();
		this.edit = edit;
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		edit.init(db);
	}

	@Override
	public void load(ODocument model) {
		edit.load(model);
		
	}

	@Override
	public JPanel getPanel() {
		return edit.getPanel();
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		edit.load(model);
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
	}



	@Override
	public void addWidgetModel(WidgetAdapter e) {
		edit.addWidgetModel(e);
		
	}

	@Override
	public Component getPanelForm() {
		// TODO Auto-generated method stub
		return edit.getPanelForm();
	}

	@Override
	public Component getLabelTitle() {
		// TODO Auto-generated method stub
		return edit.getLabelTitle();
	}



	
	

	

	
	

}

package org.bmb.app.view.form;

import java.awt.Component;

import javax.swing.JPanel;

import org.bmb.app.view.adapter.ComponetNewAdapter;
import org.bmb.app.view.adapter.FormAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ComponentNew implements FormAdapter{
	private ComponetNewAdapter form;

	public ComponentNew(ComponetNewAdapter form) {
		super();
		this.form = form;
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		form.init(db);
		
	}

	@Override
	public void load(ODocument model) {
	}

	@Override
	public JPanel getPanel() {
		return form.getPanel();
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		form.modelWidgetChange(model);
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
	}

	@Override
	public void aksiReset() {
		form.aksiReset();
		
	}

	@Override
	public void addWidgetModel(WidgetAdapter table) {
		form.addWidgetModel(table);
	}

	@Override
	public Component getPanelForm() {
		return form.getPanelForm();
	}

	@Override
	public Component getLabelTitle() {
		return form.getLabelTitle();
	}
	

	

	
	

}

package org.bmb.app.view.form;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bmb.app.view.adapter.ComponetViewAdapter;
import org.bmb.app.view.adapter.ViewAdapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ComponentView implements ViewAdapter{
	private ComponetViewAdapter view;
	
	public ComponentView(ComponetViewAdapter view) {
		super();
		this.view = view;
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		view.init(db);
		
	}

	@Override
	public void load(ODocument model) {
		view.load(model);
	}

	@Override
	public JPanel getPanel() {
		return view.getPanel();
	}
	
	public Component getPanelForm() {
		return view.getPanelForm();
	}
	
	public Component getLabelTitle() {
		return view.getLabelTitle();
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		view.load(model);
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
	}

	
	

}

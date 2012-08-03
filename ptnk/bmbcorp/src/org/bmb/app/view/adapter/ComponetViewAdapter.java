package org.bmb.app.view.adapter;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface ComponetViewAdapter {
	public JPanel getPanel();
	public void init(ODatabaseDocumentTx db);
	public void load(ODocument model);
	public Component getPanelForm();
	public Component getLabelTitle();
}

package org.bmb.app.view.adapter;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public interface WidgetAdapter {
	public void build(ODatabaseDocumentTx db);
	public void load(ODocument model);
	public JPanel getPanel();
//	public void setPanel(JPanel panel);
	public void modelWidgetChange(ODocument model);
	public void modelWidgetAdd(ODocument model);
}

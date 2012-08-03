package org.bmb.app.view.adapter;

import javax.swing.JPanel;

import org.bmb.app.view.listener.HakAksesListener;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface ToolbarAdapter extends HakAksesListener{
	public void build(ODatabaseDocumentTx db);
	public JPanel getPanel();
	public void setWindow(WindowAdapter window);
}

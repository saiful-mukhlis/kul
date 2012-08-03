package org.bmb.app.view.adapter;

import javax.swing.JMenuBar;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface MenuAdapter {
	public void build(ODatabaseDocumentTx db);
	public JMenuBar getMenu();
	public void setWindow(WindowAdapter window);
}

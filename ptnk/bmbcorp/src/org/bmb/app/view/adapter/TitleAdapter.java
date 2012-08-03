package org.bmb.app.view.adapter;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface TitleAdapter {
public void setTextTitle(ODatabaseDocumentTx db);
public void setIcon(ODatabaseDocumentTx db);
}

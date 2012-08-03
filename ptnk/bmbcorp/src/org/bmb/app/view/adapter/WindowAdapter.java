package org.bmb.app.view.adapter;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;

import org.bmb.app.view.impl.Komponent;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.view.ViewContextChangeListener;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface WindowAdapter extends WidgetAdapter {
public void initToolbar(ODatabaseDocumentTx db);
public void initMenu(ODatabaseDocumentTx db);
public void initContext(ODatabaseDocumentTx db);
public void showWelcome();
public Component getComponentWelcome();
public void seta(ODatabaseDocumentTx db);
public void buildMaster(ODatabaseDocumentTx db);
public void buildActions(ODatabaseDocumentTx db);

//public AddContentAction getUsrAca();
public AddContentAction getWelcomeAca();
//public AddContentAction getKandangAca();

public List<Komponent> getKomponents();

public void showToolbar();

public void login();
}

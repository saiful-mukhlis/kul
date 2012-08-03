package org.bmb.app.view.impl;

import java.awt.Component;

import javax.swing.Icon;

import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.listener.HakAksesListener;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;
import org.noos.xing.yasaf.view.ViewContext;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Komponent {
	private MasterAdapter master;
	private Component component;
	private AddContentAction add;
	private ViewContextAction view;
	private ToolWindowManager toolWindowManager;
	private HakAksesListener hakAksesListener;
	
	
	private String title;
	private char shortCut;
	private String icon;
	
	
	public Komponent() {
		super();
	}
	
	public Komponent(ODatabaseDocumentTx db,ToolWindowManager toolWindowManager,MasterAdapter master, String title, char shortCut,
			String icon) {
		super();
		this.master = master;
		this.setHakAksesListener((HakAksesListener) master);
		this.setTitle(title);
		this.shortCut = shortCut;
		this.setIcon(icon);
		this.toolWindowManager=toolWindowManager;
		
		master.build(db);
		component=master.getPanel();
		
		setAdd(factoryContentAction(db, title, App.getIcon(db, icon+" 16"), component, (int)shortCut));
	}

	public MasterAdapter getMaster() {
		return master;
	}
	public void setMaster(MasterAdapter master) {
		this.master = master;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public ViewContextAction getView() {
		return view;
	}
	public void setView(ODatabaseDocumentTx db, Object key, ViewContext myDoggySetContext) {
		this.view = new ViewContextAction(
				App.getT(db, getTitle()), App.getIcon(db, getIcon()+" 16"),
				myDoggySetContext, key);
	}
	
	public AddContentAction factoryContentAction(ODatabaseDocumentTx db, String title, Icon icon,
			Component component, int shortCut) {
		return new AddContentAction(toolWindowManager,
				master.getClass().toString(), App.getT(db, title),
				icon, component, App.getT(db, title), shortCut);
	}

	public AddContentAction getAdd() {
		return add;
	}

	public void setAdd(AddContentAction add) {
		this.add = add;
	}

	public HakAksesListener getHakAksesListener() {
		return hakAksesListener;
	}

	public void setHakAksesListener(HakAksesListener hakAksesListener) {
		this.hakAksesListener = hakAksesListener;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}

package org.bmb.app.view.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.bmb.app.view.adapter.MenuAdapter;
import org.bmb.app.view.adapter.WindowAdapter;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class Menu implements MenuAdapter, HakAksesListener{
	private JMenuBar menu;
	protected JMenu setting;
	protected JMenu help;
	protected JMenu toolbar;
	
	protected JMenuItem pwd;
	protected JMenuItem registrasi;
	protected JMenuItem about;
	
	private WindowAdapter window;
	
	
	
	public void init(ODatabaseDocumentTx db){
		UIManager.put("Menu.borderPainted", true);
		menu=new JMenuBar();
		menu.setVisible(false);
		menu.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		menu.setBackground(Color.RED);
		menu.setOpaque(true);
		
		setting=new JMenu(App.getT(db, "Setting"));
		help=new JMenu(App.getT(db, "Help"));
		toolbar=new JMenu(App.getT(db, "Menu"));
		toolbar.setBackground(Color.WHITE);
		toolbar.setOpaque(true);
		toolbar.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		
		
		toolbar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me){
				window.showToolbar();
			}
		});
	}
	public void build(ODatabaseDocumentTx db){
		init(db);
		menu.add(toolbar);
		menu.add(setting);
		menu.add(help);
		
//		Component c[]=menu.getComponents();
//		for (Component x : c) {
//			x.setBackground(Color.RED);
//			System.out.println("asdfasdfasdf");
//		}
	}
	
	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub
		
	}
	public JMenuBar getMenu() {
		return menu;
	}
	public void setMenu(JMenuBar menu) {
		this.menu = menu;
	}
	public WindowAdapter getWindow() {
		return window;
	}
	public void setWindow(WindowAdapter window) {
		this.window = window;
	}

}

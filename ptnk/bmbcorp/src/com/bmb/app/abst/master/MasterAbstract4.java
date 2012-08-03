package com.bmb.app.abst.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.LajurdComponetView;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.global.App;
import com.bmb.app.view.table.KandangTable;
import com.bmb.app.view.table.LajurTable;
import com.bmb.app.view.table.LajurdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class MasterAbstract4 implements MasterAdapter, HakAksesListener {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String icon;
	
	protected TableAdapter table1;
	protected TableAdapter table2;
	protected TableAdapter table3;
	protected ViewAdapter viewForm;
	protected JSplitPane splitPane;
	protected JSplitPane splitPane1;
	protected JSplitPane splitPane2;
	
	protected JButton show1w;
	protected JButton show2w;
	
	protected JButton reload;
	protected JButton print;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public void initComponent(){
		icon="icon pegawai 16";
		title="Data Pegawai";
		table1=new KandangTable();
		table2=new LajurTable();
		table3=new LajurdTable();
		viewForm=new ComponentView(new LajurdComponetView());
	}	
	public void init(ODatabaseDocumentTx db){
		initComponent();
		
		table1.build(db);
		table2.build(db);
		table3.build(db);
		viewForm.build(db);
		
		
		table1.addWidgetChange(table2);
		table2.addWidgetChange(table3);
		table1.addWidgetChange(viewForm);
		table2.addWidgetChange(viewForm);
		table3.addWidgetChange(viewForm);
		
		table1.addWidgetChange(this);
		table2.addWidgetChange(this);
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
		print = new JButton(App.getIcon(db, "icon print 16"));
		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
		label = new JLabel(App.getIcon(db, icon));
		label.setText(App.getT(db, title));
		
		reload.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);
		show1w.setBackground(Color.WHITE);
		show2w.setBackground(Color.WHITE);
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiPrint();
			}
		});
		
		
		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), table2.getPanel());
		splitPane1.setDividerSize(1);
//		splitPane1.setDividerSize(1);
		//splitPane1.setOneTouchExpandable(true);
		
		splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table3.getPanel(), viewForm.getPanel());
		splitPane2.setDividerSize(1);
		//splitPane2.setOneTouchExpandable(true);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				splitPane1, splitPane2);
		splitPane.setDividerSize(1);
//		splitPane.setDividerSize(0);
		//splitPane.setOneTouchExpandable(true);
		
		Double d = App.getW()*0.48;
		Double d1 = App.getW()*0.25;
		Double d2 = App.getW()*0.2;
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		splitPane2.setDividerLocation(d2.intValue());

		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
	}
	
	public void aksiReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table1.reload(db);
		db.close();
		
	}
	public void aksiPrint() {

		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHakAkses() {
		// TODO Auto-generated method stub
		
	}
	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,     4dlu,p,     4dlu",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
		toolBar.add(show1w, cc.xy(6, 1));
		toolBar.add(show2w, cc.xy(8, 1));
//		toolBar.add(show3w, cc.xy(10, 1));
		toolBar.add(reload, cc.xy(14, 1));
		toolBar.add(print, cc.xy(16, 1));
	}
	

}

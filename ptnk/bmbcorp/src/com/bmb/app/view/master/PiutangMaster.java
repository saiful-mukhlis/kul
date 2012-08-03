package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.FormEditAdapter;
import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.KandangComponetEdit;
import org.bmb.app.view.form.PiutangComponetEdit;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.global.App;
import com.bmb.app.view.table.PiutangTable;
import com.bmb.app.view.table.PiutangdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangMaster implements MasterAdapter, HakAksesListener {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String icon;
	
	protected TableAdapter table1;
	protected TableAdapter table2;
	protected ComponentEdit editForm;
	protected JSplitPane splitPane;
	protected JSplitPane splitPane1;
	
//	protected JButton show1w;
//	protected JButton show2w;
	
	protected JButton reload;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	protected PiutangComponetEdit pedit;
	

	public void init(ODatabaseDocumentTx db){
		icon="icon pegawai 16";
		title="Data Piutang";
		table1=new PiutangTable();
		table2=new PiutangdTable();
		pedit=new PiutangComponetEdit();
		editForm=new ComponentEdit(pedit);
		//viewForm=new ComponentView(new KandangdComponetView());
		
		table1.build(db);
		table2.build(db);
		editForm.build(db);
		
		
		table1.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (editForm.getPanel().isVisible()) {
						tampilkanTable1();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		table1.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (editForm.getPanel().isVisible()) {
						tampilkanTable1();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		table2.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (editForm.getPanel().isVisible()) {
						tampilkanTable2();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		table2.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (editForm.getPanel().isVisible()) {
						tampilkanTable2();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		editForm.getPanelForm().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (table1.getPanel().isVisible()) {
						tampilkanView();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		editForm.getLabelTitle().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (table1.getPanel().isVisible()) {
						tampilkanView();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		
		table1.addWidgetChange(table2);
		table1.addWidgetChange(editForm);
		editForm.addWidgetModel(table1);
		editForm.addWidgetModel(table2);
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
//		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
//		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
		label = new JLabel(App.getIcon(db, icon));
		label.setText(App.getT(db, title));
		
		reload.setBackground(Color.WHITE);
//		show1w.setBackground(Color.WHITE);
//		show2w.setBackground(Color.WHITE);
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				editForm.getPanel(), table2.getPanel());
//		splitPane1.setDividerSize(1);
		//splitPane1.setOneTouchExpandable(true);
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), splitPane1);
//		splitPane.setDividerSize(0);
		//splitPane.setOneTouchExpandable(true);
		
//		Double d = App.getW()*0.3;
//		Double d1 = App.getH()*0.32;
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());

		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
	}
	
	private Double d = App.getW()*0.3;
	private Double d1 = App.getH()*0.32;
	
	public void aksiReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table1.reload(db);
		db.close();
		
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
		pedit.changeHakAkses();
		
	}
	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,     4dlu,",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
//		toolBar.add(show1w, cc.xy(6, 1));
//		toolBar.add(show2w, cc.xy(8, 1));
//		toolBar.add(show3w, cc.xy(10, 1));
		toolBar.add(reload, cc.xy(14, 1));
	}
	
	
	
	public void tampilkanDefault() {
		
//		protected TableAdapter table1;
//		protected TableAdapter table2;
//		protected ComponentEdit editForm;
//		protected JSplitPane splitPane;
//		protected JSplitPane splitPane1;
		
		splitPane1.setTopComponent(editForm.getPanel());
		splitPane1.setBottomComponent(table2.getPanel());
		
		splitPane.setLeftComponent(table1.getPanel());
		splitPane.setRightComponent(splitPane1);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(true);
		editForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		
		
	}
	
	public void tampilkanTable1() {
		
		splitPane.setLeftComponent(table1.getPanel());
		
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		editForm.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
	}
	
	public void tampilkanTable2() {
		
		splitPane.setLeftComponent(table2.getPanel());
		
		table2.getPanel().setVisible(true);
		table1.getPanel().setVisible(false);
		editForm.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
	}


	public void tampilkanView() {
		splitPane.setLeftComponent(new JScrollPane(editForm.getPanel()));
		editForm.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		table1.getPanel().setVisible(false);
		
		splitPane1.setVisible(false);
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
	}
	

}

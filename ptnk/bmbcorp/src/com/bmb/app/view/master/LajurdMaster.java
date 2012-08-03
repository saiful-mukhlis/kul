package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.LajurdComponetView;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.other.DialogLoading;
import com.bmb.app.other.NamaBulan;
import com.bmb.app.print.LajurdPrint;
import com.bmb.app.view.adapter.LoadingAdapter;
import com.bmb.app.view.table.KandangTable;
import com.bmb.app.view.table.LajurTable;
import com.bmb.app.view.table.LajurdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import ar.com.fdvs.dj.domain.DynamicReport;
public class LajurdMaster implements MasterAdapter, HakAksesListener {

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
	
//	protected JButton show1w;
//	protected JButton show2w;
	
	protected JButton reload;
	protected JButton print;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public void init(ODatabaseDocumentTx db){
		icon="icon lajur 16";
		title="Laporan Produksi Lajur";
		table1=new KandangTable();
		table2=new LajurTable();
		table3=new LajurdTable();
		viewForm=new ComponentView(new LajurdComponetView());
		
		table1.build(db);
		table2.build(db);
		table3.build(db);
		viewForm.build(db);
		
		
		
		table1.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
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
					if (viewForm.getPanel().isVisible()) {
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
					if (viewForm.getPanel().isVisible()) {
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
					if (viewForm.getPanel().isVisible()) {
						tampilkanTable2();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		table3.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						LajurdTable t=(LajurdTable) table3;
						t.setShowAll();
						tampilkanTable3();
					}else{
						LajurdTable t=(LajurdTable) table3;
						t.setSimple();
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		table3.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						LajurdTable t=(LajurdTable) table3;
						t.setShowAll();
						tampilkanTable3();
					}else{
						LajurdTable t=(LajurdTable) table3;
						t.setSimple();
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		viewForm.getPanelForm().addMouseListener(new MouseAdapter() {
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
		
		viewForm.getLabelTitle().addMouseListener(new MouseAdapter() {
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
		table1.addWidgetChange(table3);
		table2.addWidgetChange(table3);
		table1.addWidgetChange(viewForm);
		table2.addWidgetChange(viewForm);
		table2.addWidgetChange(this);
		table3.addWidgetChange(viewForm);
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
//		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
//		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
		print = new JButton(App.getIcon(db, "icon print 16"));
		label = new JLabel(App.getIcon(db, icon));
		label.setText(App.getT(db, title));
		
		reload.setBackground(Color.WHITE);
//		show1w.setBackground(Color.WHITE);
//		show2w.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);
		
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
		
//		Double d = App.getW()*0.4;
//		Double d1 = App.getW()*0.2;
//		Double d2 = App.getW()*0.2;
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		splitPane2.setDividerLocation(d2.intValue());

		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
		
	}
	
	private Double d = App.getW()*0.4;
	private Double d1 = App.getW()*0.2;
	private Double d2 = App.getW()*0.2;
	
	public JFrame getFrame(Object o){
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return  getFrame(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
	public void aksiPrint() {
		if (lajur!=null) {
			LajurdPrint p=new LajurdPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, lajur);
			db.close();
		}
		
	}

	public Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	
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

	private ODocument lajur;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			lajur=null;
		}else{
			if (model.field("@class").equals(App.getLajurDao().getClassName())) {
				lajur=model;
			}
		}
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
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
//		toolBar.add(show1w, cc.xy(6, 1));
//		toolBar.add(show2w, cc.xy(8, 1));
//		toolBar.add(show3w, cc.xy(10, 1));
		toolBar.add(reload, cc.xy(14, 1));
		toolBar.add(print, cc.xy(16, 1));
	}
	
	
	
	
	
	
	
	public void changeHakAkses() {
		print.setEnabled(getPrint());
	}

	public boolean getPrint() {
		return DataUser.LAJURD_PRINT;
	}
	
	
	
	public void tampilkanDefault() {
//		protected TableAdapter table1;
//		protected TableAdapter table2;
//		protected TableAdapter table3;
//		protected ViewAdapter viewForm;
//		protected JSplitPane splitPane;
//		protected JSplitPane splitPane1;
//		protected JSplitPane splitPane2;
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(true);
		table3.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(d.intValue());
		splitPane1.setDividerLocation(d1.intValue());
		splitPane2.setDividerLocation(d2.intValue());
	}
	public void tampilkanTable1() {
		table1.getPanel().setVisible(true);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
		splitPane1.setDividerLocation(1.0);
//		splitPane2.setDividerLocation(d2.intValue());
	}
	public void tampilkanTable2() {
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(true);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(true);
		splitPane2.setVisible(false);
		
		splitPane.setDividerLocation(1.0);
		splitPane1.setDividerLocation(0.0);
//		splitPane2.setDividerLocation(d2.intValue());
	}
	public void tampilkanTable3() {
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(0.0);
//		splitPane1.setDividerLocation(0.0);
		splitPane2.setDividerLocation(1.0);
	}
	public void tampilkanView() {
		table1.getPanel().setVisible(false);
		table2.getPanel().setVisible(false);
		table3.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		splitPane1.setVisible(false);
		splitPane2.setVisible(true);
		
		splitPane.setDividerLocation(0.0);
//		splitPane1.setDividerLocation(0.0);
		splitPane2.setDividerLocation(0.0);
	}
	
	
	
	
	
	

	

}

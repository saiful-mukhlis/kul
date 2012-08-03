package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.FormEditAdapter;
import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ProductdComponetEdit;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.view.table.ProductdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductdMaster   implements MasterAdapter, HakAksesListener{

	public ProductdMaster() {
		super();
		lebar=0.7;
		title="   Stock Telor";
		icon="icon customer 16";
		editForm=new ComponentEdit(new ProductdComponetEdit());
		table = new ProductdTable();
		
		
	}




	protected JPanel panel;
	protected TableAdapter table;
	
//	protected FormAdapter form;
	protected FormEditAdapter editForm;
//	protected ViewAdapter viewForm;

//	protected JPanel cardPanel;
//	protected CardLayout cardLayout;

	protected JSplitPane splitPane;

//	protected JButton showTable;
//	protected JButton showForm;

	protected JButton reload;
//	protected JButton tambah;
	protected JButton edit;
//	protected JButton hapus;
//	protected JButton lihat;

	protected JPanel aksi;

	protected JToolBar toolBar;
	protected double lebar;
	protected String title;
	protected String icon;
	
	public int getDevide() {
		Double tmp = App.getW()*lebar;
		return tmp.intValue();
	}
	
	public void initLabelTitle(ODatabaseDocumentTx db) {
		label = new JLabel(App.getIcon(db, icon));
		label.setText(App.getT(db, title));
	}
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.build(db);
		
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if ( editForm.getPanel().isVisible()) {
						tampilkanTable();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		table.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if ( editForm.getPanel().isVisible()) {
						tampilkanTable();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
//		tambah = new JButton(App.getIcon(db, "icon tambah 16"));
		edit = new JButton(App.getIcon(db, "icon edit 16"));
//		hapus = new JButton(App.getIcon(db, "icon hapus 16"));
//		lihat = new JButton(App.getIcon(db, "icon lihat 16"));

//		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
//		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
		reload.setBackground(Color.WHITE);
//		tambah.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
//		hapus.setBackground(Color.WHITE);
//		lihat.setBackground(Color.WHITE);
//		showTable.setBackground(Color.WHITE);
//		showForm.setBackground(Color.WHITE);
		
	}
	
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		editForm.build(db);
		
		editForm.getLabelTitle().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (table.getPanel().isVisible()) {
						tampilkanView();
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
					if (table.getPanel().isVisible()) {
						tampilkanView();
					}else{
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
//		table.addWidgetChange(getEditForm());
		editForm.addWidgetModel(table);
		editForm.addWidgetModel(this);
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), editForm.getPanel());
		

		splitPane.setDividerLocation(getDevide());
		splitPane.setDividerSize(1);
		
		splitPane.setOneTouchExpandable(true);
		tampilkanDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
		
		
	}


	@Override
	public JPanel getPanel() {
		return panel;
	}




	public TableAdapter getTable() {
		return table;
	}
	
	public void aksiReload() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.reload(db);
		db.close();
		
	}
	
	public void aksiDelete(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		table.aksiDelete(db);
		db.close();
	}

	public void tampilkanForm() {
		splitPane.setDividerLocation(getDevide());
	}

	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,  2dlu,  p,  2dlu,p,  2dlu,p,  2dlu,p,   4dlu,",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		toolBar.setBackground(Color.WHITE);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
//		toolBar.add(showTable, cc.xy(6, 1));
//		toolBar.add(showForm, cc.xy(8, 1));
		toolBar.add(reload, cc.xy(12, 1));
//		toolBar.add(lihat, cc.xy(14, 1));
//		toolBar.add(tambah, cc.xy(16, 1));
		toolBar.add(edit, cc.xy(14, 1));
//		toolBar.add(hapus, cc.xy(20, 1));
	}
	protected JLabel label;
	
	public void buildAksiListener(){

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aksiEdit();
			}
		});

		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
	}

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		
		buildAksi(db);
		buildAksiListener();
		setBinding();
	}
	
	public void setBinding(){
		table.addWidgetChange(this);

		
	}


	public FormEditAdapter getEditForm() {
		return editForm;
	}

	public void setEditForm(FormEditAdapter editForm) {
		this.editForm = editForm;
	}



	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	
	@Override
	public void modelWidgetChange(ODocument model) {

	}

	@Override
	public void modelWidgetAdd(ODocument model) {

	}

	@Override
	public void load(ODocument model) {

	}
	
	
	
	public void aksiEdit() {
		editForm.modelWidgetChange(DataUser.getProduct());
		editForm.getPanel().setVisible(true);
		splitPane.setDividerLocation(getDevide());
		//splitPane.setRightComponent(editForm.getPanel());
	}
	
	public void tampilkanDefault() {
		editForm.modelWidgetChange(DataUser.getProduct());
		table.getPanel().setVisible(true);
		editForm.getPanel().setVisible(true);
		splitPane.setDividerLocation(getDevide());
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}
	
	public void setEditForm() {
	}

	public void setForm() {
		
	}

	
	public void changeHakAkses() {
		edit.setEnabled(getEdit());
	}

	public boolean getEdit() {
		return DataUser.PRODUCTD_EDIT;
	}
	
	public void tampilkanTable() {
		table.getPanel().setVisible(true);
		splitPane.setDividerLocation(1.0);
		editForm.getPanel().setVisible(false);
	}
	public void tampilkanView() {
		table.getPanel().setVisible(false);
		editForm.getPanel().setVisible(true);
		splitPane.setDividerLocation(0.0);
	}

}

package org.bmb.app.view.abst.master;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.FormAdapter;
import org.bmb.app.view.adapter.FormEditAdapter;
import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.global.App;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class MasterFAbstract implements MasterAdapter, HakAksesListener{

	protected JPanel panel;
	protected TableAdapter table;
	
	protected FormAdapter form;
	
	public FormAdapter getForm() {
		return form;
	}

	public FormEditAdapter getEditForm() {
		return null;
	}

	public ViewAdapter getViewForm() {
		return null;
	}

//	protected JPanel cardPanel;
//	protected CardLayout cardLayout;

	protected JSplitPane splitPane;

	protected JButton showTable;
	protected JButton showForm;


	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		panel.setBackground(Color.WHITE);
		initTable(db);
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
//		cardLayout = new CardLayout();
//		cardPanel = new JPanel(cardLayout);
		

		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
		showTable.setBackground(Color.WHITE);
		showForm.setBackground(Color.WHITE);
		
	}
	
	public void initTable(ODatabaseDocumentTx db) {
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
//		cardPanel.add(form.getPanel(), "tambah");
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				 form.getPanel(), table.getPanel());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerSize(1);
		tampilkanDefault();
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
	}

	public void tampilkanDefault() {
	}

	@Override
	public void load(ODocument model) {
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
	}

	@Override
	public void changeHakAkses() {
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

//	public void tampilkanForm() {
//		cardPanel.setVisible(true);
//		splitPane.setDividerLocation(getDevide());
//	}
//
//	public void aksiTambah() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "tambah");
//		form.aksiReset();
//	}

//	public void aksiLihat() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "lihat");
//	}

//	public void aksiEdit() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "edit");
//	}
	
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
		toolBar.add(showTable, cc.xy(6, 1));
		toolBar.add(showForm, cc.xy(8, 1));
//		toolBar.add(reload, cc.xy(12, 1));
//		toolBar.add(lihat, cc.xy(14, 1));
//		toolBar.add(tambah, cc.xy(16, 1));
//		toolBar.add(edit, cc.xy(18, 1));
//		toolBar.add(hapus, cc.xy(20, 1));
	}
	protected JLabel label;
	public void initLabelTitle(ODatabaseDocumentTx db){
		label = new JLabel(App.getIcon(db, "icon pegawai 16"));
		label.setText(App.getT(db, "Data Pegawai"));
	}
	
	public void buildAksiListener(){
		showTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				form.getPanel().setVisible(false);

			}
		});
		showForm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				tampilkanDefault();

			}
		});

	}

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		
		form.build(db);
		
		buildAksi(db);
		buildAksiListener();
		setBinding();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
	}

	public void setBinding(){
		table.addWidgetChange(this);

		form.addWidgetModel(table);
	}
	

}

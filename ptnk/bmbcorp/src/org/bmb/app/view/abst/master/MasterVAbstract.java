package org.bmb.app.view.abst.master;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public abstract class MasterVAbstract implements MasterAdapter, HakAksesListener{

	protected JPanel panel;
	protected TableAdapter table;
	
//	protected FormAdapter form;
//	protected FormEditAdapter editForm;
	protected ViewAdapter viewForm;

	protected JPanel cardPanel;
	protected CardLayout cardLayout;

	protected JSplitPane splitPane;

	protected JButton showTable;
	protected JButton showForm;

	protected JButton reload;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		initTable(db);
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));

		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
	}
	
	private void initTable(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		buildBody(db);
		setLayout();
	}
	
	public void setLayout() {
		panel.setLayout(new BorderLayout());
		cardPanel.add(viewForm.getPanel(), "lihat");
//		cardPanel.add(form.getPanel(), "tambah");
//		cardPanel.add(editForm.getPanel(), "edit");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), cardPanel);
		splitPane.setOneTouchExpandable(true);
		// splitPane.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// splitPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		//splitPane.setDividerLocation(50000);
		// splitPane.setLastDividerLocation(5000);
		// App.info(splitPane.setc+"");
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
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
		if (table.getPanel().isVisible()) {
			cardPanel.setVisible(true);
			splitPane.setDividerLocation(getDevide());
		}
		
	}

	private double getDevide() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void aksiTambah() {
//		tampilkanForm();
//		cardLayout.show(cardPanel, "tambah");
//		form.aksiReset();
	}

	public void aksiLihat() {
		tampilkanForm();
		cardLayout.show(cardPanel, "lihat");
	}

	public void aksiEdit() {
		tampilkanForm();
		cardLayout.show(cardPanel, "edit");
	}
	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,     4dlu,",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
		toolBar.add(showTable, cc.xy(6, 1));
		toolBar.add(showForm, cc.xy(8, 1));
		toolBar.add(reload, cc.xy(12, 1));
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
				tampilkanTable();

			}
		});
		showForm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				tampilkanForm();

			}
		});

		
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		
	}
	

	public void tampilkanTable() {
		if (cardPanel.isVisible()) {
			cardPanel.setVisible(false);
			table.getPanel().setVisible(true);
		}else{
			cardPanel.setVisible(true);
			table.getPanel().setVisible(false);
		}
		
	}

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		
		viewForm.build(db);
		
		buildAksi(db);
		buildAksiListener();
		setBinding();
	}
	
	private void initBody(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	public void setBinding(){
//		table.addWidgetChange(editForm);
		table.addWidgetChange(viewForm);
		table.addWidgetChange(this);

//		form.addWidgetModel(table);
	}
	

}

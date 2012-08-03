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

public abstract class MasterVW3Abstract implements MasterAdapter, HakAksesListener{

	protected JPanel panel;
	protected TableAdapter table;
	
//	protected FormAdapter form;
//	protected FormEditAdapter editForm;
	protected ViewAdapter viewForm;

//	protected JPanel cardPanel;
//	protected CardLayout cardLayout;

	protected JSplitPane splitPane;

	protected JButton show1w;
	protected JButton show2w;
//	protected JButton show3w;

	protected JButton reload;

	protected JPanel aksi;

	protected JToolBar toolBar;
	
	public FormAdapter getForm() {
		// TODO Auto-generated method stub
		return null;
	}

	public FormEditAdapter getEditForm() {
		// TODO Auto-generated method stub
		return null;
	}

	public ViewAdapter getViewForm() {
		// TODO Auto-generated method stub
		return viewForm;
	}
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		initTable(db);
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
//		cardLayout = new CardLayout();
//		cardPanel = new JPanel(cardLayout);
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));

		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
//		show3w = new JButton(App.getIcon(db, "icon 3w 16"));
		
	}
	
	public void initTable(ODatabaseDocumentTx db) {
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
//		cardPanel.add(viewForm.getPanel(), "lihat");
//		cardPanel.add(form.getPanel(), "tambah");
//		cardPanel.add(editForm.getPanel(), "edit");
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table.getPanel(), viewForm.getPanel());
		splitPane.setOneTouchExpandable(true);
		// splitPane.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		// splitPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tampilkanDefault();
//		
//		splitPane.setLastDividerLocation(5000);
		// App.info(splitPane.setc+"");
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(toolBar, BorderLayout.NORTH);
	}


	public void tampilkanDefault() {
	}

	@Override
	public JPanel getPanel() {
		return panel;
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




//	public void aksiLihat() {
//		tampilkan2w();
//		cardLayout.show(cardPanel, "lihat");
//	}
//
//	public void aksiEdit() {
//		tampilkan2w();
//		cardLayout.show(cardPanel, "edit");
//	}
	
	public void buildAksi(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				" 4dlu,  	f:p,  4dlu,   p:g,  4dlu,   	p,  4dlu,   	p,  4dlu, p,  4dlu,   	p:g,  4dlu,   	"
						+ "p,     4dlu,",

				"p,3dlu");

		toolBar = new JToolBar();
		toolBar.setLayout(layout);
		CellConstraints cc = new CellConstraints();
		toolBar.add(label, cc.xy(2, 1));
		toolBar.add(show1w, cc.xy(6, 1));
		toolBar.add(show2w, cc.xy(8, 1));
//		toolBar.add(show3w, cc.xy(10, 1));
		toolBar.add(reload, cc.xy(14, 1));
	}
	protected JLabel label;
	public void initLabelTitle(ODatabaseDocumentTx db){
		label = new JLabel(App.getIcon(db, "icon pegawai 16"));
		label.setText(App.getT(db, "Data Pegawai"));
	}
	
	public void buildAksiListener(){
		show1w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				if (splitPane.getDividerLocation()!=getDevide()) {
					tampilkanDefault();
				}else{
					splitPane.setDividerLocation(1.0);
				}

			}
		});
		show2w.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				if (splitPane.getDividerLocation()==getDevide()) {
					splitPane.setDividerLocation(0.0);
				}else{
					tampilkanForm();
				}

			}
		});
//		show3w.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				tampilkan3w();
//
//			}
//		});
		
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		
	}
	

//	public void tampilkan3w() {
//		
//		
//	}
//	
//	public void tampilkan2w() {
//		cardPanel.setVisible(true);
//		table.getPanel().setVisible(true);
//		splitPane.setDividerLocation(getDevide());
//	
//}
//
//	public void tampilkan1w() {
//		cardPanel.setVisible(true);
//		table.getPanel().setVisible(false);
//		
//	}

	public void buildBody(ODatabaseDocumentTx db){
		initBody(db);
		
		viewForm.build(db);
		
		buildAksi(db);
		buildAksiListener();
		setBinding();
	}
	
	public void initBody(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	public void setBinding(){
		table.addWidgetChange(viewForm);
		table.addWidgetChange(this);
	}
	
	public void tampilkanForm() {
		splitPane.setDividerLocation(getDevide());
	}
	

	
	
	
	public void aksiLihat() {
		tampilkanForm();
		splitPane.setRightComponent(viewForm.getPanel());
	}
	
	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		aksiLihat();

	}

	@Override
	public void modelWidgetAdd(ODocument model) {

	}

	@Override
	public void load(ODocument model) {

	}
	
	
	
//	public void aksiEdit() {
//		if (editForm==null) {
//			setEditForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			editForm.build(db);
//			db.close();
//			table.addWidgetChange(getEditForm());
//			editForm.addWidgetModel(table);
//			editForm.addWidgetModel(this);
//			table.selected();
//		}
//		tampilkanForm();
//		splitPane.setRightComponent(editForm.getPanel());
//	}
	
//	public void aksiTambah() {
//		if (form==null) {
//			setForm();
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			form.build(db);
//			form.addWidgetModel(table);
//			db.close();
//		}
//		splitPane.setRightComponent(form.getPanel());
//		tampilkanForm();
//		getForm().aksiReset();
//	}

//	public void setForm(FormAdapter form) {
//		this.form = form;
//	}
//
//	public void setEditForm(FormEditAdapter editForm) {
//		this.editForm = editForm;
//	}

	public void setViewForm(ViewAdapter viewForm) {
		this.viewForm = viewForm;
	}
	
	public int getDevide() {
		Double tmp = App.getW()*0.5;
		return tmp.intValue();
	}
}

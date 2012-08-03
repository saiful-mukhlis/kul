package com.bmb.app.view.master;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.FormatComponetEdit;
import org.bmb.app.view.form.FormatComponetView;
import org.bmb.app.view.form.PelangganComponetEdit;
import org.bmb.app.view.form.PelangganComponetNew;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.view.table.FormatTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatMaster extends MasterAbstract {

	public FormatMaster() {
		super();
		lebar=0.35;
		title="   Format Laporan";
		icon="icon format 16";
		viewForm=new ComponentView(new FormatComponetView());
		table = new FormatTable();
	}


	@Override
	public void setEditForm() {
		setEditForm(new ComponentEdit(new FormatComponetEdit()));
	}

	@Override
	public void setForm() {
		//setForm(new ComponentNew(new PelangganComponetNew()));
	}
	
	public void changeHakAkses() {
		edit.setEnabled(getEdit());
	}

	

	public boolean getLihat() {
		return DataUser.FORMAT_VIEW;
	}

	public boolean getEdit() {
		return DataUser.FORMAT_EDIT;
	}
	
	
	
	
	
	
	
	
	
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		
		table.build(db);
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
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
					if (viewForm.getPanel().isVisible()) {
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
		edit = new JButton(App.getIcon(db, "icon edit 16"));
		lihat = new JButton(App.getIcon(db, "icon lihat 16"));

//		showTable = new JButton(App.getIcon(db, "icon 1w 16"));
//		showForm = new JButton(App.getIcon(db, "icon 2l 16"));
		
		reload.setBackground(Color.WHITE);
		edit.setBackground(Color.WHITE);
		lihat.setBackground(Color.WHITE);
//		showTable.setBackground(Color.WHITE);
//		showForm.setBackground(Color.WHITE);
		
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
		toolBar.add(lihat, cc.xy(14, 1));
		toolBar.add(edit, cc.xy(16, 1));
	}
	
	public void buildAksiListener(){
//		showTable.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				if (splitPane.getDividerLocation()!=getDevide()) {
//					tampilkanDefault();
//				}else{
//					splitPane.setDividerLocation(1.0);
//				}
//
//			}
//		});
//		showForm.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent actionevent) {
//				if (splitPane.getDividerLocation()==getDevide()) {
//					splitPane.setDividerLocation(0.0);
//				}else{
//					tampilkanForm();
//				}
//			}
//		});


		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aksiEdit();
			}
		});

		lihat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aksiLihat();
			}
		});
		
		reload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		
		edit.setEnabled(false);
		lihat.setEnabled(false);
	}
	
	@Override
	public void modelWidgetChange(ODocument model) {
		// tampilan default
		aksiLihat();
		if (model==null) {
			edit.setEnabled(false);
			lihat.setEnabled(false);
		}else{
			edit.setEnabled(getEdit());
			lihat.setEnabled(getLihat());
			viewForm.modelWidgetChange(model);
		}

	}

}

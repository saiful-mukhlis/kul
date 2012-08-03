package com.bmb.app.view.master;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.KandangallComponetView;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.KandangallDao;
import com.bmb.app.global.App;
import com.bmb.app.print.KandangallPrint;
import com.bmb.app.view.table.KandangallTable;
import com.bmb.app.view.table.KandangdTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangallMaster extends MasterAbstract {

	public KandangallMaster() {
		super();
		lebar=0.4;
		title="   Laporan Total Produksi Semua Kandang";
		icon="icon kandang 16";
		viewForm=new ComponentView(new KandangallComponetView());
		table = new KandangallTable();
	}
	

	@Override
	public void setEditForm() {
//		setEditForm(new UsrEditForm());
	}

	@Override
	public void setForm() {
//		setForm(new UsrForm());
	}

	
	private Date tgla;
	
	
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			tgla=null;
		}else if (model.field("@class").equals(App.getKandangallDao().getClassName())) {
			tgla=model.field(KandangallDao.tgl);
		}
		// tampilan default
		aksiLihat();

	}
	
	public void buildAksiListener(){
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
		
	}
	
	public void aksiPrint() {
		if (tgla!=null) {
			KandangallPrint p=new KandangallPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, tgla);
			db.close();
		}
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
		toolBar.add(print, cc.xy(14, 1));
	}
	
	protected JButton print;
	public void init(ODatabaseDocumentTx db){
		panel=new JPanel();
		table.build(db);
		
		table.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (viewForm.getPanel().isVisible()) {
						KandangallTable tx=(KandangallTable) table;
						tx.setShowAll();
						tampilkanTable();
					}else{
						KandangallTable tx=(KandangallTable) table;
						tx.setSimple();
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
						KandangallTable tx=(KandangallTable) table;
						tx.setShowAll();
						tampilkanTable();
					}else{
						KandangallTable tx=(KandangallTable) table;
						tx.setSimple();
						tampilkanDefault();
					}
				}
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		initLabelTitle(db);
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
		print = new JButton(App.getIcon(db, "icon print 16"));
		
		reload.setBackground(Color.WHITE);
		print.setBackground(Color.WHITE);

		
		reload.setBackground(Color.WHITE);
		
	}
	
	
	
	
	
	
	public void changeHakAkses() {
		print.setEnabled(getPrint());
	}

	public boolean getPrint() {
		return DataUser.KANDANGD_PRINT;
	}
	
	
	public void tampilkanDefault() {
		
		
		splitPane.setLeftComponent(table.getPanel());
		splitPane.setRightComponent(viewForm.getPanel());
		
		splitPane.setDividerLocation(getDevide());
		
		table.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		
		
	}
	
	public void tampilkanTable() {
		
		splitPane.setLeftComponent(table.getPanel());
		
		table.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(false);
		
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(1.0);
	}


	public void tampilkanView() {
		splitPane.setLeftComponent(new JScrollPane(viewForm.getPanel()));
		table.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		
		splitPane.setVisible(true);
		
		splitPane.setDividerLocation(1.0);
//		splitPane1.setDividerLocation(0.0);
	}
	
	
}

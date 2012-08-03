package com.bmb.app.view.master;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import org.bmb.app.view.adapter.MasterAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.DatePicker;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.KandangdComponetView;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.bmb.app.print.KandangdPrint;
import com.bmb.app.view.table.KandangdPakanTable;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdInputPakanMaster implements MasterAdapter, HakAksesListener {

	protected JPanel panel;
	protected JLabel label;
	protected String title;
	protected String icon;
	
	protected TableAdapter table1;
	protected ViewAdapter viewForm;
	protected JSplitPane splitPane;
	
	protected JButton show1w;
	protected JButton show2w;
	
	protected JButton reload;
	
	protected JPanel aksi;

	protected JToolBar toolBar;
	
	protected DatePicker tgla;
	
	
	public void init(ODatabaseDocumentTx db){
		icon="icon pakan 16";
		title="Input Pakan dan Umur";
		table1=new KandangdPakanTable();
		
		
		
		((KandangdPakanTable)table1).setMaster(this);
		viewForm=new ComponentView(new KandangdComponetView());
		
		table1.build(db);
		table1.getTable().addMouseListener(new MouseAdapter() {
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
		table1.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
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
		
		viewForm.build(db);
		setAksiTampilan();
		
		table1.addWidgetChange(this);
		table1.addWidgetChange(viewForm);
		
		tgla=new DatePicker();
		tgla.setDate(new Date());
		tgla.getEditor().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
					Date tmp=tgla.getDate();
					if (tmp!=null) {
						ODatabaseDocumentTx db = App.getDbd();
					    ODatabaseRecordThreadLocal. INSTANCE.set(db);
						ODocument o=new ODocument(App.getKandangdDao().getClassName());
						o.field(KandangdDao.tgl, tmp, OType.DATE);
						table1.modelWidgetChange(o);
						db.close();
					
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		reload = new JButton(App.getIcon(db, "icon reload 16"));
		show1w = new JButton(App.getIcon(db, "icon 1w 16"));
		show2w = new JButton(App.getIcon(db, "icon 2l 16"));
		//print = new JButton(App.getIcon(db, "icon print 16"));
		label = new JLabel(App.getIcon(db, icon));
		label.setText(App.getT(db, title));
		
		reload.setBackground(Color.WHITE);
		show1w.setBackground(Color.WHITE);
		show2w.setBackground(Color.WHITE);
		
		reload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReload();
			}
		});
		
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				table1.getPanel(), viewForm.getPanel());
		
		splitPane.setDividerLocation(getDevide());

		buildAksi(db);
		
		panel=new JPanel(new BorderLayout());
		
		FormLayout layout = new FormLayout(
				"r:p,   	10px,   	f:max(200px;p), 	10px,",

				"p,3dlu");

		 FormBuilder builder = new FormBuilder(layout, true);

		builder.append(db, "Tanggal", tgla, 1, 1, 1);
		JPanel p2=builder.getPanel();
		p2.setBackground(Color.WHITE);
		JPanel p=new JPanel(new BorderLayout());
		p.setBackground(Color.WHITE);
		p.add(p2, BorderLayout.NORTH);
		p.add(splitPane, BorderLayout.CENTER);
		
		JPanel p3=new JPanel(new BorderLayout());
		p3.setBackground(Color.WHITE);
		p3.add(toolBar, BorderLayout.NORTH);
		p3.add(new JSeparator(), BorderLayout.SOUTH);
		
		panel.setBackground(Color.WHITE);
		panel.add(p, BorderLayout.CENTER);
		panel.add(p3, BorderLayout.NORTH);
		
		
		
	}
	
	public void aksiPrint() {
		if (kandang!=null) {
			KandangdPrint p=new KandangdPrint(getPanel());
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			p.run(db, kandang);
			db.close();
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
		ODocument o=new ODocument(App.getKandangdDao().getClassName());
		o.field(KandangdDao.tgl, new Date(), OType.DATE);
		table1.modelWidgetChange(o);
	}

	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	private ODocument kandang;
	@Override
	public void modelWidgetChange(ODocument model) {
		if (model==null) {
			kandang=null;
		}else{
			if (model.field("@class").equals(App.getKandangDao().getClassName())) {
				kandang=model;
			}
		}
		
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
//		toolBar.add(print, cc.xy(16, 1));
	}

	public DatePicker getTgla() {
		return tgla;
	}

	public void setTgla(DatePicker tgla) {
		this.tgla = tgla;
	}
	

	
	
	
	
	
	public void tampilkanDefault() {
		table1.getPanel().setVisible(true);
		viewForm.getPanel().setVisible(true);
		splitPane.setDividerLocation(getDevide());
	}
	public void tampilkanTable() {
		table1.getPanel().setVisible(true);
		splitPane.setDividerLocation(1.0);
		viewForm.getPanel().setVisible(false);
	}
	public void tampilkanView() {
		table1.getPanel().setVisible(false);
		viewForm.getPanel().setVisible(true);
		splitPane.setDividerLocation(0.0);
	}
	
	public int getDevide() {
		Double tmp = App.getW()*0.5;
		return tmp.intValue();
	}
	
	public void setAksiTampilan(){
		if (viewForm.getPanelForm()!=null) {
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
		}
		
		if (viewForm.getLabelTitle()!=null) {
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
		}
		
	}
}

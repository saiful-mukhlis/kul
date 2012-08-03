package org.bmb.app.view.component;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.bmb.app.view.builder.FormBuilder;


import com.bmb.app.config.UtilAccount;
import com.bmb.app.dao.BosDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class FormRegistrasi {

	private TextField kodeField;
	protected JPanel panelTitle;
	protected JLabel titleLabel;
	protected JLabel ketLabel;
	protected Icon icon;
	
	protected JButton saveButton;
	protected JButton resetButton;
	protected JButton cancelButton;
	
	protected JPanel panelAksi;
	protected JPanel panelBesic;
	protected JPanel panelForm;

	protected Focus focus;
	
	public void initComponent() {
		kodeField = new TextField();

	}

	public void buildPanelTitle(ODatabaseDocumentTx db) {
		ODocument bos=App.getBosDao().getOne(db, BosDao.id, 2);
		buildPanelTitle(db, "Serial Number Program :", "Silahkan Hubungi Kami untuk mendapatkan Kode Aktifasi", "icon reg 32");
		titleLabel.setText(titleLabel.getText()+" RGHJT-OJAD-"+App.getBosDao().getName(bos).toUpperCase());
	}

	public void buildPanelForm(ODatabaseDocumentTx db) {
		saveButton.setText(App.getT(db, "Proses"));
		FormLayout layout = new FormLayout(
				"r:p,   	4dlu,   	f:max(200dlu;p):g(.4),    	15dlu,",

				"p,3dlu,    p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu");

		FormBuilder builder = new FormBuilder(layout, true);

		builder.append(db, "Kode", kodeField, 1, 1, 1);

		panelForm = builder.getPanel();
		panelForm.setBackground(Color.WHITE);
	}

	public void setCursor() {
		kodeField.requestFocus();
	}

	public void aksiReset() {
		kodeField.setText("");
		setCursor();
	}


	public void buildFocus() {
		List<Component> p = new ArrayList<Component>();

		p.add(kodeField);

		p.add(saveButton);
		p.add(resetButton);
		p.add(cancelButton);

		focus = new Focus(p);
	}


	public void aksiSave() {
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (!kodeField.getText().trim().equalsIgnoreCase("")) {
		    ODocument o=App.getBosDao().getOne(db, BosDao.id, 2);
			String kodeaktivasi=App.getBosDao().getName(o).toUpperCase();
			try {
				String kode=new UtilAccount().md5(kodeaktivasi+"@#-mefghj678X");
				String ac=kode.substring(1, 7);
				//System.out.println(ac);
				if (ac.equalsIgnoreCase(kodeField.getText())) {
					App.getBosDao().setJml(o, 1);
					o.save();
					Preferences userPref = Preferences.userRoot();
			        userPref.put("ortptnk", App.getBosDao().getName(o).toUpperCase());
			        JFrame f=getFrame(panelBesic);
			        if (f!=null) {
			        	f.setTitle("Application Peternakan");
					}
					dispose(panelBesic);
				}else{
					App.showErrorSN();
				}
			} catch (Exception e) {
				App.printErr(e);
			}
		}else{
			App.showErrorFieldEmpty(db, "Kode");
		}

				db.close();

			

	}
	
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
	
	
	public void setPanelTitle(JPanel panel, JLabel titleLabel,
			JLabel ketLabel, Icon icon) {
		setLabelTitle(titleLabel);
		FormLayout layout = new FormLayout(
				"10dlu,f:p:g, 10dlu, f:32px,  10dlu", // cols
				"5dlu, p, 3dlu,p, 3dlu,p, 3dlu"); // rows

		PanelBuilder builder = new PanelBuilder(layout);
		GradientPanel p = new GradientPanel();
		p.setBackground(new Color(240, 255, 255));
		p.setForeground(SystemColor.WHITE);
		builder.setDefaultDialogBorder();
		CellConstraints cc = new CellConstraints();

		builder.add(titleLabel, cc.xy(2, 2));
		//builder.add(ketLabel, cc.xy(2, 4));
		//ketLabel.setLineWrap(true);
		JLabel l = new JLabel();
		l.setIcon(icon);
		builder.add(l, cc.xywh(4, 2, 1, 3, "center, top"));
		//builder.addSeparator("", cc.xyw(1, 3, 2));
		// builder.getPanel().setBackground(SystemColor.WHITE);
		builder.getPanel().setOpaque(false);
		p.add(builder.getPanel());
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(p, BorderLayout.CENTER);
		JSeparator s = new JSeparator();
		panel.add(s, BorderLayout.SOUTH);
	}
	
	public void setLabelTitle(JLabel titleLabel) {
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	public void buildPanelTitle(ODatabaseDocumentTx db, String title, String ket, String img) {
		titleLabel = new JLabel(App.getT(db, title));
		ketLabel = new JLabel(App.getT(db, ket));
		icon = App.getIcon(db, img);
	}
	
	public void dispose(Object o) {
		if (o instanceof Window) {
			((Window) o).dispose();
		} else {
			if (o instanceof Component) {
				dispose(((Component) o).getParent());
			}
		}
	}
	
	public void buildComponent(ODatabaseDocumentTx db) {
		initFirst(db);
		initComponent();
		buildPanelTitle(db);
		buildPanelAksi();
		buildPanelForm(db);
		setTitle();
		buildFocus();
		panelBesic.setLayout(new BorderLayout());
		panelBesic.add(panelForm, BorderLayout.CENTER);
		panelBesic.add(panelAksi, BorderLayout.SOUTH);
		panelBesic.add(panelTitle, BorderLayout.NORTH);
		aksiReset();
	}
	
	public void initFirst(ODatabaseDocumentTx db) {
		panelBesic = new JPanel();
		panelBesic.setLayout(new BorderLayout());

		panelTitle = new JPanel();

		panelForm = new JPanel();
		panelForm.setLayout(new BorderLayout());

		saveButton = new JButton(App.getT(db, "Save"));
		resetButton = new JButton(App.getT(db, "Reset"));
		cancelButton = new JButton(App.getT(db, "Cancel"));

	}
	
	public void buildPanelAksi() {

		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
		builderButton
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		builderButton.addGlue();
		builderButton.addButton(saveButton, resetButton, cancelButton);
		panelAksi = new JPanel();
		panelAksi.setBackground(Color.WHITE);
		panelAksi.setLayout(new BorderLayout());
		panelAksi.add(new JSeparator(), BorderLayout.NORTH);
		panelAksi.add(builderButton.getPanel(), BorderLayout.CENTER);

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				aksiCancel();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiSave();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReset();
			}
		});
	}
	
	public void setTitle() {
		setPanelTitle(panelTitle, titleLabel, ketLabel, icon);
	}
	public void aksiCancel() {
		dispose(panelBesic);
	}
	public JPanel getPanel() {
		return panelBesic;
	}
	public Focus getFocus() {
		return focus;
	}
}

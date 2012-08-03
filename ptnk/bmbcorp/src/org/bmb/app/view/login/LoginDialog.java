package org.bmb.app.view.login;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.bmb.app.view.component.GradientPanel;
import org.bmb.app.view.component.PasswordField;
import org.bmb.app.view.component.TextField;



import com.bmb.app.config.DataUser;
import com.bmb.app.config.UtilAccount;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LoginDialog {
	private JPanel panelBesic;

	private JPanel panelLogin;
	private TextField usernameField;
	private PasswordField passwordField;
	private JButton okButton;
	private JButton cancelButton;

	protected JPanel panelTitle;
	protected JLabel titleLabel;
	protected JLabel ketLabel;
	protected Icon icon;


	private void initComponent(ODatabaseDocumentTx db) {
		panelBesic = new JPanel();
		panelBesic.setBackground(Color.WHITE);

		panelLogin = new JPanel();
		panelLogin.setBackground(Color.WHITE);
		usernameField = new TextField();
		usernameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField.requestFocus();
			}
		});
		passwordField = new PasswordField();
		okButton = new JButton(App.getT(db, "Login"));
		cancelButton = new JButton(App.getT(db, "Cancel"));

		panelTitle = new JPanel();
		titleLabel = new JLabel(App.getT(db, "Login"));
		ketLabel = new JLabel(App.getT(db, "Masuk ke Applikasi"));
		icon = App.getIcon(db, "icon login 32");
		setAksiButton();
	}

	public void buildComponent(ODatabaseDocumentTx db) {
		initComponent(db);
		setPanelTitle(panelTitle, titleLabel,ketLabel, icon);

		FormLayout layout = new FormLayout(
				"r:p:g(.1), 4dlu, f:max(200dlu;p):g(.4), 4dlu:g");
		// layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();

		builder.append(App.getT(db, "Username"), usernameField);
		builder.append(App.getT(db, "Password"), passwordField);
		JPanel panel = builder.getPanel();
		panel.setBackground(Color.WHITE);

		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
		builderButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		builderButton.addGlue();
		builderButton.addButton(okButton, cancelButton);
		JPanel panel2 = builderButton.getPanel();

		panelLogin.setLayout(new BorderLayout());
		panelLogin.add(panel, BorderLayout.CENTER);
		panelLogin.add(new JSeparator(), BorderLayout.SOUTH);
		panelBesic.setLayout(new BorderLayout());
		panelBesic.add(panelTitle, BorderLayout.NORTH);
		panelBesic.add(panelLogin, BorderLayout.CENTER);
		panelBesic.add(panel2, BorderLayout.SOUTH);

	}
	
	public void setAksiButton(){
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiCancel();
			}
		});

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiOk();
			}
		});
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiOk();
			}
		});
	}

	protected void aksiOk() {
		String u=usernameField.getText();
		String p=new String(passwordField.getPassword());
		try {
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			ODocument usr=App.getUsrDao().getOne(db, UsrDao.username, u);
			if (usr==null) {
				App.showErrorUsernameTidakTerdaftar();
			}else{
				UtilAccount util=new UtilAccount();
				String tmp=util.md5(p);
				String pwd=usr.field(UsrDao.password);
				if (tmp.equalsIgnoreCase(pwd)) {
					DataUser.setUsr(usr);
					ODocument grp = App.getGrpDao().getOne(db, "@rid", DataUser.getUsr().field(UsrDao.grp));
					DataUser.setGrp(grp);
					App.getBosDao().minus(db);
//					perp();
					db.close();
					dispose(panelBesic);
				}else{
					db.close();
					App.showErrorPasswordSalah();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//	public void perp(){
//		DaoPerp daoPerp=(DaoPerp) Db.PERP.get();
//		Perp p1=daoPerp.getById((long) 1);
//		if (p1!=null) {
//			int j=p1.getJml();
//			if (j==0) {
//				Perp p2=daoPerp.getById((long) 2);
//				if (p2!=null) {
//					int j2=p2.getJml();
//					if (j2<0) {
//						JOptionPane.showMessageDialog(null, "Masa Trial Sudah Habis");
//						System.exit(0);
//					}
//					j2--;
//					p2.setJml(j2);
//					daoPerp.save(p2);
//				}else{
//					System.exit(1);
//				}
//			}else{
//				//System.exit(1);
//			}
//		}else{
//			System.exit(1);
//		}
//	}

	protected void aksiCancel() {
			dispose(panelBesic);
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

	public JPanel getPanel() {
		return panelBesic;
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
		JPanel pan=builder.getPanel();
		//pan.setBackground(Color.WHITE);
		p.add(pan);
		//p.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(p, BorderLayout.CENTER);
		JSeparator s = new JSeparator();
		panel.add(s, BorderLayout.SOUTH);
	}
	
	public void setLabelTitle(JLabel titleLabel) {
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
}

package org.bmb.app.view.component;


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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;


import com.bmb.app.global.App;
import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class AboutDialog {
	private JPanel panelBesic;

	private JPanel panelLogin;
	private JButton okButton;

	protected JPanel panelTitle;
	protected JLabel titleLabel;
	protected JLabel ketLabel;
	protected Icon icon;

	protected String title;
	protected String ket;

	private void initComponent(ODatabaseDocumentTx db) {
		panelBesic = new JPanel();

		panelLogin = new JPanel();
		okButton = new JButton("Ok");

		panelTitle = new JPanel();
		titleLabel = new JLabel("About Application");
		ketLabel = new JLabel("Tentang Aplikasi Peternakan");
		icon = App.getIcon(db, "icon app 32");
		setAksiButton();
	}

	public void buildComponent(ODatabaseDocumentTx db) {
		initComponent(db);
		setPanelTitle(panelTitle, titleLabel,ketLabel, icon);
		panelTitle.setBorder(BorderFactory.createEmptyBorder());

		FormLayout layout = new FormLayout(
				"r:p:g(.1), 4dlu, f:max(150dlu;p):g(.4), 4dlu:g");
		// layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();

		builder.append(App.getT(db, "Application Name :"), new JLabel("Peternakan"));
		builder.append(App.getT(db, "Version :"), new JLabel("1.0.0"));
		builder.append(App.getT(db, "Powered By :"), new JLabel("<html><br/><br/><br/>Mandiri Prima<br/>Jl. Sumargo - Gg. Bogenvile no. 19 <br/> Lamongan<br/> Telp . 081 330 93 000 8 </html>"));
		JPanel panel = builder.getPanel();
		panel.setBackground(Color.WHITE);

		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
		builderButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		builderButton.addGlue();
		builderButton.addButton(okButton);
		JPanel panel2 = builderButton.getPanel();
		panel2.setBackground(Color.WHITE);
		
		JLabel l=new  JLabel();
		l.setIcon(new ImageIcon(getClass().getResource("/image/about.jpg")));

		panelLogin.setLayout(new BorderLayout());
		panelLogin.add(panel, BorderLayout.CENTER);
		panelLogin.setBorder(BorderFactory.createEmptyBorder());
		panelBesic.setLayout(new BorderLayout());
		panelBesic.add(panelTitle, BorderLayout.NORTH);
		
		JPanel p=new JPanel(new BorderLayout());
		p.add(l, BorderLayout.WEST);
		p.add(panelLogin, BorderLayout.CENTER);
		p.add(new JSeparator(), BorderLayout.SOUTH);
		
		panelBesic.add(p, BorderLayout.CENTER);
		panelBesic.add(panel2, BorderLayout.SOUTH);
		panelBesic.setBackground(Color.WHITE);

	}
	
	public void setAksiButton(){

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiOk();
			}
		});
	}

	protected void aksiOk() {
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
		p.add(builder.getPanel());
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(p, BorderLayout.CENTER);
		JSeparator s = new JSeparator();
		panel.add(s, BorderLayout.SOUTH);
	}
	
	public void setLabelTitle(JLabel titleLabel) {
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
}


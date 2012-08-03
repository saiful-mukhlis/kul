package org.bmb.app.view.abst.title;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.bmb.app.view.adapter.TitleAdapter;
import org.bmb.app.view.component.GradientPanel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public abstract class TitleAbstract implements TitleAdapter{
	protected JPanel panelTitle;
	protected JLabel titleLabel;
	protected JLabel ketLabel;
	protected Icon icon;
	public void inti(ODatabaseDocumentTx db){
		panelTitle=new JPanel();
		initTitle();
		setTextTitle(db);
		setIcon(db);
	}
	public void initTitle(){
		titleLabel=new JLabel();
		ketLabel=new JLabel();
	}
	public void setLabelTitle(JLabel titleLabel) {
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
	public void build(ODatabaseDocumentTx db) {
		inti(db);
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
		builder.addSeparator("", cc.xyw(1, 3, 2));
		// builder.getPanel().setBackground(SystemColor.WHITE);
		builder.getPanel().setOpaque(false);
		p.add(builder.getPanel());
		getPanelTitle().setLayout(new BorderLayout(0, 0));
		getPanelTitle().add(p, BorderLayout.CENTER);
		JSeparator s = new JSeparator();
		getPanelTitle().add(s, BorderLayout.SOUTH);
		getPanelTitle().setBorder(BorderFactory.createEmptyBorder());
	}
	public JPanel getPanelTitle() {
		return panelTitle;
	}
}

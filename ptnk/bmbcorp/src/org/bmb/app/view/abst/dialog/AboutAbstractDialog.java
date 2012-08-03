package org.bmb.app.view.abst.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.bmb.app.view.abst.title.TitleAbstract;
import org.bmb.app.view.adapter.AboutAdapter;

import com.bmb.app.global.App;
import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public abstract class AboutAbstractDialog extends TitleAbstract implements AboutAdapter {
	protected JPanel panel;

	protected JButton ok;


	
	public void build(ODatabaseDocumentTx db){
		super.build(db);
		setTextTitle(db);
		panel.setLayout(new BorderLayout());
		panel.add(panelTitle, BorderLayout.NORTH);
		buildButtonAksi(db);
		buildContent(db);
		
		
	}
	public void buildButtonAksi(ODatabaseDocumentTx db){
		ButtonBarBuilder2 builderButton = new ButtonBarBuilder2();
		builderButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		builderButton.addGlue();
		builderButton.addButton(ok);
		JPanel panel2 = builderButton.getPanel();
		panel.add(panel2, BorderLayout.SOUTH);
	}
	public void buildContent(ODatabaseDocumentTx db){
		FormLayout layout = new FormLayout(
				"r:p:g(.1), 4dlu, f:max(150dlu;p):g(.4), 4dlu:g");
		// layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		setTextAbout(db, builder);
		JPanel panelBody=new JPanel();
		panelBody.setLayout(new BorderLayout());
		JPanel panelIsi = builder.getPanel();
		panelBody.add(panelIsi, BorderLayout.CENTER);
		panelBody.setBorder(BorderFactory.createEmptyBorder());
		JPanel p=new JPanel(new BorderLayout());
		JLabel l=new  JLabel();
		l.setIcon(App.getIcon(db, "icon about 32"));
		p.add(l, BorderLayout.WEST);
		p.add(panelBody, BorderLayout.CENTER);
		p.add(new JSeparator(), BorderLayout.SOUTH);
		
		panel.add(p, BorderLayout.CENTER);
	}
	public void init(ODatabaseDocumentTx db){
		super.inti(db);
		panel=new JPanel();
		initButton(db);
	}
	public void initButton(ODatabaseDocumentTx db){
		ok=new JButton(App.getT(db, "Ok"));
	}
	
	public void setAksiButton(){
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiOk();
			}
		});
	}

	protected void aksiOk() {
		dispose(panel);
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
		return panel;
	}
	@Override
	public void setTextTitle(ODatabaseDocumentTx db) {
		titleLabel=new JLabel(App.getT(db, "About Application"));
		ketLabel=new JLabel(App.getT(db, "Tentang Applikasi ini."));
		
		
	}
	@Override
	public void setIcon(ODatabaseDocumentTx db) {
		icon=App.getIcon(db, "icon about 32");
		
	}
}

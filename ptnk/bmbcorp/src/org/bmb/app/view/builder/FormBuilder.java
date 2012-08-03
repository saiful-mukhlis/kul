package org.bmb.app.view.builder;


import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.bmb.app.global.App;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class FormBuilder extends PanelBuilder{

	public FormBuilder(FormLayout layout, boolean border) {
		super(layout);
		if (border) {
			setDefaultDialogBorder();
		}
	}

	public FormBuilder(FormLayout layout) {
		super(layout);
	}
	public CellConstraints getCc() {
		return cc;
	}
	private CellConstraints cc = new CellConstraints();
	
	public void append(ODatabaseDocumentTx db, String i8n, Component c,int x, int y, int w,int h){
		addLabel(App.getT(db, i8n), cc.xy(x, y));
		add(c, cc.xywh(x+2, y, w, h));
	}
	
	public void append(ODatabaseDocumentTx db, Font font,String i8n, Component c,int x, int y, int w,int h){
		JLabel l=addLabel(App.getT(db, i8n), cc.xywh(x, y ,w ,h));
		l.setFont(font);
		add(c, cc.xywh(x+2, y, w, h));
	}
	
	public void append(JButton button, Component c,int x, int y, int w,int h){
		add(c, cc.xywh(x, y, w, h));
		add(button, cc.xywh(x+2, y, w, h));
	}
	
	public void append(ODatabaseDocumentTx db, String i8n, Component c,int x, int y, int w){
		addLabel(App.getT(db, i8n), cc.xy(x, y));
		add(c, cc.xyw(x+2, y, w));
	}
	
	public void append(ODatabaseDocumentTx db, Font font,String i8n, Component c,int x, int y, int w){
		JLabel l=addLabel(App.getT(db, i8n), cc.xy(x, y));
		l.setFont(font);
		add(c, cc.xyw(x+2, y, w));
	}
	
	public void append(Component c,int x, int y){
		add(c, cc.xy(x, y));
	}
	
	public void append(ODatabaseDocumentTx db, String i8n, Component c,int x, int y){
		addLabel(App.getT(db, i8n), cc.xy(x, y));
		add(c, cc.xy(x+2, y));
	}
	
	public void addSeparator(ODatabaseDocumentTx db, String i8n,int x, int y, int w){
		addSeparator(App.getT(db, i8n),cc.xyw(x, y, w) );
	}
}

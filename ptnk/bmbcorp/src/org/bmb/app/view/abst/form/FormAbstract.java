package org.bmb.app.view.abst.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.bmb.app.view.adapter.FormAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public abstract class FormAbstract  {
	protected JPanel panel;
	protected List<WidgetAdapter> widgeds=new ArrayList<WidgetAdapter>();

	public void init(ODatabaseDocumentTx db) {
		initPanel();
	}
	
	public void initPanel(){
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
	}

	public void build(ODatabaseDocumentTx db) {
		init(db);
	}


	public JPanel getPanel() {
		return panel;
	}

	public void clearText(JTextComponent field) {
		field.setText("");
	}
	
	public void setFocusEnter(JTextComponent sebelum, final JTextComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER==e.getKeyCode()) {
					sesudah.requestFocus();
					sesudah.selectAll();
				}
			}
		});
	}
	//JFormattedTextField
	public void setFocusEnter(JTextComponent sebelum, final JFormattedTextField sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER==e.getKeyCode()) {
					sesudah.requestFocus();
					sesudah.selectAll();
				}
			}
		});
	}
	public void setFocusEnter(JTextComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER==e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}
	public void setFocusEnter(JComponent sebelum, final JComponent sesudah) {
		sebelum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER==e.getKeyCode()) {
					sesudah.requestFocus();
				}
			}
		});
	}
	
	public boolean validate(ODatabaseDocumentTx db, JTextField field, String namaField){
		if (field.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(db, namaField);
			return false;
		}
		return true;
	}
	public boolean validate(ODatabaseDocumentTx db, JTextArea field, String namaField){
		if (field.getText().trim().equalsIgnoreCase("")) {
			App.showErrorFieldEmpty(db, namaField);
			return false;
		}
		return true;
	}
	
	public void addWidgetModel(WidgetAdapter e){
		widgeds.add(e);
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

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	

}

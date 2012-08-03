package org.bmb.app.view.component;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class ComboBox extends JComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8738097170338146861L;

	public ComboBox() {
		super();
		setFirst();
	}

	public ComboBox(ComboBoxModel aModel) {
		super(aModel);
		setFirst();
	}

	public ComboBox(Object[] items) {
		super(items);
		setFirst();
	}

	public ComboBox(Vector items) {
		super(items);
		setFirst();
	}
	
	public void setFirst(){
		//setBorder(App.border);
		setBackground(Color.WHITE);
	}

}

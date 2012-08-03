package org.bmb.app.view.component;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.bmb.app.global.App;

public class TextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850291374364481785L;

	public TextArea() {
		super();
		setBackground(Color.WHITE);
		setCaretColor(App.aqua);
		setLineWrap(true);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (pane!=null) {
					pane.setBorder(App.border);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (pane!=null) {
					pane.setBorder(App.borderSelected);
				}
			}
		});
	}
	
	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}

	private JScrollPane pane;

}

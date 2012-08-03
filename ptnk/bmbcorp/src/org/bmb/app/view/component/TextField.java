package org.bmb.app.view.component;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import com.bmb.app.global.App;

public class TextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501974976360534771L;

	public TextField() {
		super();
		setBorder(App.border);
		setBackground(Color.WHITE);
		setCaretColor(App.aqua);
		addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				setBorder(App.border);
			}
			@Override
			public void focusGained(FocusEvent e) {
				setBorder(App.borderSelected);
			}
		});
	}

}

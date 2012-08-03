package org.bmb.app.view.component;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;

import org.jdesktop.swingx.JXDatePicker;

import com.bmb.app.global.App;

public class DatePicker extends JXDatePicker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7914592521691664079L;

	public DatePicker() {
		super();
		setFirst();
	}

	public DatePicker(Date selection, Locale locale) {
		super(selection, locale);
		setFirst();
	}

	public DatePicker(Date selected) {
		super(selected);
		setFirst();
	}

	public DatePicker(Locale locale) {
		super(locale);
		setFirst();
	}

	public void setFirst(){
		setFormats(App.dateFormat);
		setBorder(App.border);
		setBackground(Color.WHITE);
		getEditor().setBorder(BorderFactory.createEmptyBorder());
		getEditor().setBackground(Color.WHITE);
		getEditor().setCaretColor(App.aqua);
		getEditor().addFocusListener(new FocusListener() {
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

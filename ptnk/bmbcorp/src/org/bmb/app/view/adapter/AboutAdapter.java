package org.bmb.app.view.adapter;

import javax.swing.JLabel;

import com.bmb.app.global.App;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface AboutAdapter {
	/**
	 * <code><br/>
	 	public void setTextAbout(ODatabaseDocumentTx db, DefaultFormBuilder builder){<br/>
		builder.append(App.getT(db, "Nama"), new JLabel(App.getT(db, "Applikasi Peternakan")));<br/>
		builder.append(App.getT(db, "Versi"), new JLabel(App.getT(db, "1.0.0")));<br/>
		builder.append(App.getT(db, "Powered"), new JLabel(App.getT(db, "BMB Corp")));<br/>
	}<br/>
	 * </code>
	 * @param db
	 * @param builder
	 */
	public void setTextAbout(ODatabaseDocumentTx db, DefaultFormBuilder builder);
}

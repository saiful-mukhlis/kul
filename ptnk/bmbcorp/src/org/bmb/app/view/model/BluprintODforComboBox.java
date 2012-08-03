package org.bmb.app.view.model;

import com.orientechnologies.orient.core.record.impl.ODocument;

public class BluprintODforComboBoxxx  {
	private String nameField;
	private ODocument o;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (getO()==null) {
			return "";
		}
		return getO().field(nameField);
	}

	public String getNameField() {
		return nameField;
	}

	public void setNameField(String nameToS) {
		this.nameField = nameToS;
	}

	public ODocument getO() {
		return o;
	}

	public void setO(ODocument o) {
		this.o = o;
	}

}

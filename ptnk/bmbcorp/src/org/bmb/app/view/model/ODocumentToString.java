package org.bmb.app.view.model;

import com.bmb.app.dao.adapter.DaoAdapter;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ODocumentToString {
	private DaoAdapter dao;
	private ODocument o;
	private String ket="";
	public ODocument getO() {
		return o;
	}

	public void setO(ODocument o) {
		this.o = o;
	}

	public String toString() {
		if (o != null) {
			if (dao.getNameFielsToString() != null) {
				String tmp = o.field(dao.getNameFielsToString());
				if (tmp != null) {
					return tmp;
				} else {
					return "";
				}
			}
		}

		return ket;
	}

	public ODocumentToString(DaoAdapter dao, ODocument o) {
		super();
		this.dao = dao;
		this.o = o;
	}

	public ODocumentToString(DaoAdapter dao, String ket) {
		super();
		this.dao = dao;
		this.ket = ket;
	}
	

}

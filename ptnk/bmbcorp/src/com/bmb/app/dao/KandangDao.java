package com.bmb.app.dao;


import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangDao extends DaoAbstract {
	
	public static final  String code="code";
	public static final  String name="name";
	public static final  String note="note";
	
	public static final  String fcode="Kode";
	public static final  String fname="Kandang";
	public static final  String fnote="Keterangan";
	
	public KandangDao() {
		super("Kandang");
	}
	
	public ODocument factoryModel(String codea, String namea, String notea){
		ODocument doc=new ODocument(getClassName());
		doc.field(code, codea);
		doc.field(name, namea);
		doc.field(note, notea);
		doc.field("x", 0, OType.INTEGER);
		return doc;
	}

	@Override
	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		// lajur
		// kandangd
		List<ODocument> lajurs=App.getLajurDao().getAllByColumn(db, LajurDao.kandang, o.getIdentity());
		for (ODocument oDocument : lajurs) {
			App.getLajurDao().setNull(db, oDocument);
		}
		
		List<ODocument> kandangds=App.getKandangdDao().getAllByColumn(db, KandangdDao.kandang, o.getIdentity());
		for (ODocument oDocument : kandangds) {
			App.getKandangdDao().setNull(db, oDocument);
		}
		
		return super.beforeDelete(db, o);
	}

	@Override
	public void afterDelete(ODatabaseDocumentTx db, ODocument o) {
		App.getLajurDao().delNull(db);
		App.getKandangdDao().delNull(db);
		super.afterDelete(db, o);
	}

	

}

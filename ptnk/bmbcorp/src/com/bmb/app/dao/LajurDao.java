package com.bmb.app.dao;



import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

public class LajurDao extends DaoAbstract {
	public static final  String kandang="kandang";
	public static final  String code="code";
	public static final  String name="name";
	public static final  String note="note";
	
	public static final  String fcode="Kode";
	public static final  String fname="Lajur";
	public static final  String fnote="Keterangan";
	
	public LajurDao() {
		super("Lajur");
	}
	public ODocument factoryModel(ODocument kandanga, String codea, String namea, String notea){
		ODocument doc=new ODocument(getClassName());
		doc.field(kandang, kandanga, OType.LINK);
		doc.field(code, codea);
		doc.field(name, namea);
		doc.field(note, notea);
		doc.field("x", 0, OType.INTEGER);
		return doc;
	}
	
	public ODocument setNull(ODatabaseDocumentTx db,ODocument o){
		List<ODocument> lajurds=App.getLajurdDao().getAllByColumn(db, LajurdDao.lajur, o.getIdentity());
		for (ODocument oDocument : lajurds) {
			App.getLajurdDao().setNull(db, oDocument);
		}
		super.setNull(db, o);
		return o;
	}
	
	public int delNull(ODatabaseDocumentTx db){
		int jml=App.getLajurdDao().delNull(db);
//		StringBuilder sql=new StringBuilder("delete from ");
//		sql.append(getClassName());
//		sql.append(" where ");
//		sql.append(kandang);
//		sql.append(" is null ");
//		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp=super.delNull(db);
		return tmp+jml;
	}
	
	@Override
	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		// lajurd
		List<ODocument> lajurds=App.getLajurdDao().getAllByColumn(db, LajurdDao.lajur, o.getIdentity());
		for (ODocument oDocument : lajurds) {
			App.getLajurdDao().setNull(db, oDocument);
		}
		
		return super.beforeDelete(db, o);
	}
	
	@Override
	public void afterDelete(ODatabaseDocumentTx db, ODocument o) {
		App.getLajurdDao().delNull(db);
		super.afterDelete(db, o);
	}
}

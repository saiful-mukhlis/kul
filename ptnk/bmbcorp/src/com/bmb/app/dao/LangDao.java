package com.bmb.app.dao;


import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Lang;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class LangDao extends DaoAbstract {

	public LangDao() {
		super("Lang");
	}
	
//	public Lang getLangByCode(OObjectDatabaseTx db, String value){
//		return getOne(db, "code", value);
//	}
	
	public ODocument getLangByCode(ODatabaseDocumentTx db, String value){
		return getOne(db, "code", value);
	}

	public void createContentDefaultLang() {
		ODocument en=factoryLang("en", "English");
		en.save();
		ODocument id=factoryLang("id", "Indonesia");
		id.save();
	}
	

//	public void factoryLang(ODatabaseDocumentTx db, final String kode,
//			String name) {
//		App.info("cek apakah lang dengan kode ini ada?");
//		long count=getCountByColumn(db, "kode", kode);
//		if (count == 0) {
//			App.info("buat lang baru dengan factory");
//			factoryLang(kode, name);
//		}else{
//			App.info("lang dengan kode :"+kode+" sudah ada dalam database");
//		}
//
//	}

	public ODocument factoryLang(String code, String name) {
		ODocument doc = new ODocument(getClassName());
		doc.field("code", code);
		doc.field("name", name);
		return doc;
	}
	
	public int updateWithDocumentByCode(ODatabaseDocumentTx db, String code, String name, boolean codeIsChange){
		StringBuilder sql=new StringBuilder("update ");
		sql.append(getClassName());
		if (codeIsChange) {
			sql.append(" set code = ? ,");
		}
		sql.append(" set name = ?");
		sql.append(" where code = ? ");
		App.info(sql.toString());
		OCommandSQL query = new OCommandSQL(sql.toString());
		if (codeIsChange) {
			return db.command(query).execute(code, name, code);
		}else{
			return db.command(query).execute(name, code);
		}
	}

}

package com.bmb.app.dao;


import com.bmb.app.config.DataUser;
import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.I18n;
import com.bmb.app.db.Lang;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class I18nDao extends DaoAbstract {
	
	public static final   String lang="lang";
	public static final   String name="name";
	public static final   String translate="translate";
	
	public static final   String fname="Kunci";
	public static final   String ftranslate="Bahasa";

	public I18nDao() {
		super("I18n");
	}
	
	
	public ODocument getI18nByName(ODatabaseDocumentTx db, String name){
		return getOne(db, "lang", DataUser.getLang().getIdentity() , "name", name);
	}
	
	public String getTranslate(ODatabaseDocumentTx db, String name){
		ODocument doc=getI18nByName(db, name);
		if (doc==null) {
			return name;
		}else{
			return doc.field("translate");
		}
	}
	
	/**
	 * <code><br/>
	 * ODatabaseDocumentTx db=App.getDbd(); <br/>
		ODocument t=App.getI18nDao().factoryModel(DataUser.getLang().getIdentity(), "Nama", "Nama Adalah");<br/>
		t.save();<br/>
		</code>
	 * @param lang
	 * @param name
	 * @param translate
	 * @return ODocument
	 */
	public ODocument factoryModel(ORID lang, String name, String translate) {
//		ODatabaseDocumentTx db=App.getDbd();
//		ODatabaseRecordThreadLocal.INSTANCE.set( db );
		ODocument doc = new ODocument(getClassName());
		doc.field("lang", lang);
		doc.field("name", name);
		doc.field("translate", translate);
		return doc;
//		db.close();
	}


	
	
//	public int updateWithDocumentByCode(ODatabaseDocumentTx db, String code, String name, boolean codeIsChange){
//		StringBuilder sql=new StringBuilder("update ");
//		sql.append(className);
//		if (codeIsChange) {
//			sql.append(" set code = ? ,");
//		}
//		sql.append(" set name = ?");
//		sql.append(" where code = ? ");
//		App.info(sql.toString());
//		OCommandSQL query = new OCommandSQL(sql.toString());
//		if (codeIsChange) {
//			return db.command(query).execute(code, name, code);
//		}else{
//			return db.command(query).execute(name, code);
//		}
//	}

}

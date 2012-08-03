package com.bmb.app.dao;


import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Lang;
import com.bmb.app.db.NumberId;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class NumberIdDao extends DaoAbstract {

	public NumberIdDao() {
		super("NumberId");
	}
	

	public ODocument getLangByNamaTable(ODatabaseDocumentTx db, String value){
		return getOne(db, "namaTable", value);
	}

	public ODocument createContentDefaultModel(ODatabaseDocumentTx db, String nameTable, String prefix, String format, boolean useFormat) {
		if (getCountByNameTable(db, nameTable)==0) {
			ODocument tmp=factoryLang(nameTable, 1, 0, 1, 1, prefix, format, useFormat);
					tmp.save();
					return tmp;
		}else{
			return null;
		}
	}
	public ODocument createContentDefaultModel(ODatabaseDocumentTx db, String nameTable) {
		if (getCountByNameTable(db, nameTable)==0) {
			ODocument tmp=factoryLang(nameTable, 1, 0, 1, 1, "", null, false);
			tmp.save();		
			return tmp;
		}else{
			return null;
		}
	}


	public ODocument factoryLang(String nameTable, long start,
			long end, int increment, long now, String prefix,
			String format, boolean useFormat ) {
		ODocument doc = new ODocument(getClassName());
		doc.field("namaTable", nameTable);
		doc.field("start", start, OType.LONG);
		doc.field("end", end, OType.LONG);
		doc.field("increment", increment, OType.INTEGER);
		doc.field("now", now, OType.LONG);
		doc.field("prefix", prefix);
		doc.field("format", format);
		doc.field("useFormat", useFormat, OType.BOOLEAN);
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
	
	public long getCountByNameTable(ODatabaseDocumentTx db, String nameTable){
		return getCountByColumn(db, "namaTable", nameTable);
	}

}

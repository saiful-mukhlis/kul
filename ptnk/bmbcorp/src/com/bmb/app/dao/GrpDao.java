package com.bmb.app.dao;

import java.util.Date;
import java.util.List;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Grp;
import com.bmb.app.db.Logdb;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class GrpDao extends DaoAbstract {
	
	public static final   String code="code";
	public static final   String name="name";
	public static final   String note="note";
	public static final   String logdb="logdb";
	public static final   String key="key";
	
	public static final   String fcode="Kode";
	public static final   String fname="Nama";
	public static final   String fnote="Ket";
	public static final   String flogdb="Log";
	public static final   String fkey="Key";

	public GrpDao() {
		super("Grp");
	}
	
	public List<ODocument> getAll(ODatabaseDocumentTx db){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" order by name asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}

	public ODocument factoryModel(String codea, String namea, String notea){
		ODocument doc=factoryModel(namea, codea, notea, (String) DataUser.getUsr().field(UsrDao.username), new Date());
		doc.field(code, codea);
		doc.field(name, namea);
		doc.field(note, notea);
		return doc;
	}
	
	public ODocument factoryModel(String name, String code, String note, String createdBy, Date  createdAt) {
		ODocument doc = new ODocument(getClassName());
		doc.field("name", name);
		doc.field("code", code);
		doc.field("note", note);
		ODocument logdb=new ODocument("Logdb");
		logdb.field("createdBy", createdBy );
		logdb.field("createdAt", createdAt, OType.DATE);
		doc.field("logdb", logdb, OType.EMBEDDED);
		return doc;

	}
	
	public void factoryModelFirst(ODatabaseDocumentTx db){
		if (getCount(db)==0) {
			ODocument admin=factoryModel("Admin", "ADM", "Hak Akses untuk Super User", "admin", new Date());
			StringBuffer tmp=new StringBuffer();
			for (int i = 1; i < 32; i++) {
				tmp.append("x"+i+"x");
			}
			admin.field(key, tmp.toString());
			save(db, admin);
			ODocument pegawai=factoryModel("Pegawai", "PEG", "Hak Akses untuk Pegawai", "admin", new Date());
			save(db, pegawai);
			ODocument umum=factoryModel("Umum", "UMU", "Hak Akses untuk umum", "admin", new Date());
			save(db, umum);
		}
	}
	
	public long modelIsExist(ODatabaseDocumentTx db, String name, String code){
		return getCountByColumn(db, "name", name, "code", code, "or");
	}
	
	public boolean beforeDelete(ODatabaseDocumentTx db,ODocument o) {
		App.getUsrDao().delByColoumn(db, UsrDao.grp, o.getIdentity());
		return true;
	}

	@Override
	public String getNameFielsToString() {
		return name;
	}

	
}

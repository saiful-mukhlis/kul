package com.bmb.app.dao;

import java.util.Date;

import com.bmb.app.config.DataUser;
import com.bmb.app.config.UtilAccount;
import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Grp;
import com.bmb.app.db.I18n;
import com.bmb.app.db.Lang;
import com.bmb.app.db.Logdb;
import com.bmb.app.db.Usr;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class UsrDao extends DaoAbstract {

	public static final  String username="username";
	public static final  String password="password";
	public static final  String nikName="nikName";
	public static final  String status="status";
	public static final  String grp="grp";
	public static final  String logdb="logdb";
	
	public static final  String fusername="Username";
	public static final  String fpassword="Password";
	public static final  String fulang="Ketik Ulang";
	public static final  String fnikName="Nama";
	public static final  String fstatus="Status";
	public static final  String fgrp="Hak Akses";
	public static final  String flogdb="Log";
	
	public UsrDao() {
		super("Usr");
	}


	public ODocument factoryModel( String username,
			String passwordBeforMd5, String nikName, byte status, ODocument grp,
			ODocument logdb) {
			ODocument doc = new ODocument(getClassName());
			doc.field(this.username, username);

			try {
				doc.field(this.password, new UtilAccount().md5(passwordBeforMd5));
			} catch (Exception e) {
				App.printErr(e);
			}
			doc.field(this.nikName, nikName);
			doc.field(this.status, status);
			doc.field(this.grp, grp, OType.LINK);
			doc.field(this.logdb, logdb, OType.EMBEDDED);
			return doc;

	}

	public ODocument factoryModelFirst(ODatabaseDocumentTx db){
			GrpDao grpDao=App.getGrpDao();
//			ODatabaseDocumentTx db=App.getDbd();
//			ODatabaseRecordThreadLocal.INSTANCE.set( db );
			ODocument grp=grpDao.getOne(db, "code", "ADM");
			if (grp==null) {
				grpDao.factoryModelFirst(db);
				//grp=grpDao.factoryModel("Admin", "ADM", "Super User", "admin", new Date());
				grp=grpDao.getOne(db, "code", "ADM");
			}
			ODocument logdb=new ODocument("Logdb");
			logdb.field("createdBy", "admin" );
			logdb.field("createdAt", new Date(), OType.DATE);
			ODocument usr=factoryModel("admin", "admin", "Admin", (byte)1, grp, logdb);
			usr.save();
			return usr;
	}

	// public int updateWithDocumentByCode(ODatabaseDocumentTx db, String code,
	// String name, boolean codeIsChange){
	// StringBuilder sql=new StringBuilder("update ");
	// sql.append(className);
	// if (codeIsChange) {
	// sql.append(" set code = ? ,");
	// }
	// sql.append(" set name = ?");
	// sql.append(" where code = ? ");
	// App.info(sql.toString());
	// OCommandSQL query = new OCommandSQL(sql.toString());
	// if (codeIsChange) {
	// return db.command(query).execute(code, name, code);
	// }else{
	// return db.command(query).execute(name, code);
	// }
	// }

}

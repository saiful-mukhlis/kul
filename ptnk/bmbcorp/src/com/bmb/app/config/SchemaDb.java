package com.bmb.app.config;

import java.io.IOException;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

//class ini hanya di gunakan untuk percobaan
//klo sudah mohon di hapus
public class SchemaDb {
	public void createDbIfNotExist(String url) throws IOException {
//		ODatabaseFlat db = new ODatabaseFlat(url);
//		if (ODatabaseHelper.existsDatabase(db))
//			db.open("admin", "admin");
//		else
//			db.create();
//		 if (db.getMetadata().getSchema().existsClass("Usr"))
//             return;
//		 
		
		url="local:c://test/db";
		String user="admin";
		String pwd="admin";
		
		String packDb="com.bmb.app.db";
		OObjectDatabaseTx db=OObjectDatabasePool.global().acquire(url, user, pwd);
		
		 OClass usr = db.getMetadata().getSchema().getClass("Grp");
//		 OClass usr = db.getMetadata().getSchema().createClass("Grp");
		 usr.createIndex("GrpId", OClass.INDEX_TYPE.UNIQUE, "name");
		 
		 OClass logdb = db.getMetadata().getSchema().getClass("Logdb");
		 usr.createProperty("logdb",OType.EMBEDDED, logdb ).setNotNull(true);
		 
		 
		 
		 
		 db.getMetadata().getSchema().save();
		
		
//		db.command( new OCommandSQL("CREATE PROPERTY Grp.name STRING")).execute();
//		db.command( new OCommandSQL("CREATE INDEX Grp.name UNIQUE")).execute();
		db.close();
		
	}
}

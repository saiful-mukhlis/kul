package com.bmb.app.dao.abst;

import java.util.List;

import com.bmb.app.dao.adapter.DaoAdapter;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.iterator.ORecordIteratorClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public abstract class DaoAbstract implements DaoAdapter {
	public String className;

	public DaoAbstract(String table) {
		super();
		this.setClassName(table);
	}
	
	public ODocument save(ODatabaseDocumentTx db, ODocument model){
		String tmp=model.field("code");
		if (tmp==null || tmp.trim().equalsIgnoreCase("AUTO") || tmp.trim().equalsIgnoreCase("")) {
			ODocument num=App.getNumberIdDao().getLangByNamaTable(db, getClassName());
			if (num==null) {
				num=App.getNumberIdDao().createContentDefaultModel(db, getClassName());
			}
			long now=num.field("now");
			model.field("code", now+"");
			model.save();
			int aut=num.field("increment");
			now=now+aut;
			num.field("now", now , OType.LONG);
			num.save();
		}else{
			model.save();
		}
		return model;
	}

	public ORecordIteratorClass<ODocument> getAllLazy(ODatabaseDocumentTx db){
		return db.browseClass(getClassName());
	}
	
	public List<ODocument> getAll(ODatabaseDocumentTx db){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}
	
	public List<ODocument> getAll(ODatabaseDocumentTx db, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip "+start+" limit "+end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}
	
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(" skip "+start+" limit "+end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}
	public List<ODocument> getAllByColumnLike(ODatabaseDocumentTx db, String kolom, String value, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" like ? ");
		sql.append(" skip "+start+" limit "+end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(" skip "+start+" limit "+end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}
	
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, String value){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}
	
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}


	
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, String value) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
	public List<ODocument> getAllLazy(ODatabaseDocumentTx db, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip "+start+" limit "+end);
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute();
		return result;
	}


	

	
//	public T getOne(OObjectDatabaseTx db, String kolom, Object value, String kolom2, Object vallue2, String operator) {
//		StringBuilder sql=new StringBuilder("select * from ");
//		sql.append(className);
//		sql.append(" where ");
//		sql.append(kolom);
//		sql.append(" = ? ");
//		sql.append(operator);
//		sql.append(" ");
//		sql.append(kolom2);
//		sql.append(" = ? limit 1");
//		OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
//		List<T> result = db.command(query).execute(value, vallue2);
//		if (result.size() == 0) {
//			return null;
//		} else {
//			return (T) result.get(0);
//		}
//	}
	
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object value2, String operator) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(operator);
		sql.append(" ");
		sql.append(kolom2);
		sql.append(" = ? limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value, value2);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
//	public T getOne(OObjectDatabaseTx db, String kolom, Object value, String kolom2, Object vallue2) {
//		return getOne(db, kolom, value, kolom2, vallue2, "and");
//	}
	
	public ODocument getOne(ODatabaseDocumentTx db, String kolom, Object value, String kolom2, Object vallue2) {
		return getOne(db, kolom, value, kolom2, vallue2, "and");
	}

	public long getCount(ODatabaseDocumentTx db) {
		return db.countClass(getClassName());
	}

	public long getCount(ODatabaseDocumentTx db, String sql, String as) {
		List<ODocument> result = db.query(new OSQLSynchQuery<ODocument>(sql));
		try {
			long count = Long.parseLong(result.get(0).field(as).toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}
	
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value) {
		StringBuilder sql=new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, Object value) {
		StringBuilder sql=new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}
	
	public long getCountByColumn(ODatabaseDocumentTx db, String colom, String value, String colom2, String value2, String operator) {
		StringBuilder sql=new StringBuilder("select count(*) as x from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		sql.append(" ");
		sql.append(operator);
		sql.append(" ");
		sql.append(colom);
		sql.append(" = ?");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value, value2);
		try {
			long count = Long.parseLong(result.get(0).field("x").toString());
			return count;
		} catch (Exception e) {
			App.printErr(e);
			return 0;
		}
	}
	
	
	
	public void printAll(ODatabaseDocumentTx db){
		for (ODocument obj : db.browseClass(getClassName())) {
			App.info(obj.toJSON());
		}
	}
	
	
	
	
	public long truncetClass(ODatabaseDocumentTx db) {
		return db.command(new OCommandSQL("TRUNCATE CLASS "+getClassName())).execute();
	}
	/**
	 * contoh dari sql
	 * TRUNCATE RECORD [20:0, 20:1, 20:2], 
	 * TRUNCATE RECORD 20:3
	 * @param db
	 * @param rid
	 * @return
	 */
	public int truncetRecord(ODatabaseDocumentTx db, String rid) {
		return db.command(new OCommandSQL("TRUNCATE CLASS "+rid)).execute();
	}
	public long deleteAll(ODatabaseDocumentTx db) {
		return truncetClass(db);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	/**
	 * menghapus data yang kosong
	 * @param db
	 * @param namaColom
	 * @return
	 */
	public int delNull(ODatabaseDocumentTx db) {
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append("x");
		sql.append(" = ? ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp=db.command(query).execute(1);
		return tmp;
	}
	
	public int delByColoumn(ODatabaseDocumentTx db, String namaColom, Object value){
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(namaColom);
		sql.append(" = ? ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		return db.command(query).execute(value);
	}
	
	/**
	 * sql delete dengan operator =
	 * untuk parameter colom harus lowerCase
	 * @param db
	 * @param colom
	 * @param value
	 * @return int (jumlah data yang di manipulasi/ di delete)
	 */
	public int delByColoumn(ODatabaseDocumentTx db, String colom, String value) {
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(colom);
		sql.append(" = ?");
		OCommandSQL query = new OCommandSQL(sql.toString());
		return db.command(query).execute(value);
	}
	

	
	public ODocument delete(ODatabaseDocumentTx db,ODocument o){
		try {
			db.begin(TXTYPE.OPTIMISTIC);
			if (beforeDelete( db, o)) {
				o.delete();
			}
			db.commit();
		}catch( Exception e ){
		  db.rollback();
		} 
		afterDelete( db, o);
		return o;
	}
	

	public void afterDelete(ODatabaseDocumentTx db,ODocument o) {
		
	}

	public boolean beforeDelete(ODatabaseDocumentTx db,ODocument o) {
		return true;
	}

	public ODocument setNull(ODatabaseDocumentTx db, ODocument o) {
		o.field("x", 1, OType.INTEGER);
		o.save();
		return o;
	}
	
	public String getNameFielsToString(){
		return null;
	}
	
	
//	public List<T> getResult(OObjectDatabaseTx db, OSQLSynchQuery<T> query) {
//		return db.query(query);
//	}
//
//	public List<T> getAll(OObjectDatabaseTx db) {
//		StringBuilder sql=new StringBuilder("select * from ");
//		sql.append(className);
//		OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
//		return getResult(db, query);
//	}
	
//	public T getOne(OObjectDatabaseTx db, String kolom, String value) {
//		StringBuilder sql=new StringBuilder("select * from ");
//		sql.append(className);
//		sql.append(" where ");
//		sql.append(kolom);
//		sql.append(" = ? limit 1");
//		OSQLSynchQuery<T> query = new OSQLSynchQuery<T>(sql.toString());
//		List<T> result = db.command(query).execute(value);
//		if (result.size() == 0) {
//			return null;
//		} else {
//			return (T) result.get(0);
//		}
//	}
}

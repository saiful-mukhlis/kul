package com.bmb.app.dao;



import java.util.Date;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Kandang;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class KandangallDao extends DaoAbstract {
	
	
	public static final  String  tgl="tgl";
	//public static final  String  umur="umur";
	public static final  String  pupulasi="pupulasi";
	public static final  String  mati="mati";
	public static final  String  pakan="pakan";
	
	public static final  String  bagusbutir="bagusbutir";
	public static final  String  baguskg="baguskg";
	
	public static final  String  retakbutir="retakbutir";
	public static final  String  retakkg="retakkg";
	
	public ODocument factoryModel(Date tgla,
			long pupulasia, int matia, double pakana, int bagusbutira,
			double baguskga, int retakbutira, double retakkga){
		ODocument o=new ODocument(getClassName());
		o.field(tgl, tgla, OType.DATE);
		//o.field(umur, umura, OType.INTEGER);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
		o.field(pakan, pakana, OType.DOUBLE);
		o.field(bagusbutir, bagusbutira, OType.INTEGER);
		o.field(baguskg, baguskga, OType.DOUBLE);
		o.field(retakbutir, retakbutira, OType.INTEGER);
		o.field(retakkg, retakkga, OType.DOUBLE);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModel(Date tgla,
			long pupulasia, int matia, int bagusbutira,
			double baguskga, int retakbutira, double retakkga){
		ODocument o=new ODocument(getClassName());
		o.field(tgl, tgla, OType.DATE);
		//o.field(umur, umura, OType.INTEGER);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
//		o.field(pakan, pakana, OType.DOUBLE);
		o.field(bagusbutir, bagusbutira, OType.INTEGER);
		o.field(baguskg, baguskga, OType.DOUBLE);
		o.field(retakbutir, retakbutira, OType.INTEGER);
		o.field(retakkg, retakkga, OType.DOUBLE);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModelSementara(Date tgla,
			long pupulasia, int matia, int bagusbutira,
			double baguskga, int retakbutira, double retakkga, int x){
		ODocument o=new ODocument(getClassName());
		o.field(tgl, tgla, OType.DATE);
		//o.field(umur, umura, OType.INTEGER);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
//		o.field(pakan, pakana, OType.DOUBLE);
		o.field(bagusbutir, bagusbutira, OType.INTEGER);
		o.field(baguskg, baguskga, OType.DOUBLE);
		o.field(retakbutir, retakbutira, OType.INTEGER);
		o.field(retakkg, retakkga, OType.DOUBLE);
		o.field("x", x, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModelUpdate(ODocument o,Date tgla,
			long pupulasia, int matia, int bagusbutira,
			double baguskga, int retakbutira, double retakkga){
		o.field(tgl, tgla, OType.DATE);
		//o.field(umur, umura, OType.INTEGER);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
//		o.field(pakan, pakana, OType.DOUBLE);
		o.field(bagusbutir, bagusbutira, OType.INTEGER);
		o.field(baguskg, baguskga, OType.DOUBLE);
		o.field(retakbutir, retakbutira, OType.INTEGER);
		o.field(retakkg, retakkga, OType.DOUBLE);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	
	public KandangallDao() {
		super("Kandangall");
	}
	
	
	@Override
	public int delNull(ODatabaseDocumentTx db) {
		Date tanggalnull=new Date();
		tanggalnull.setTime(0);
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(tgl);
		sql.append(" = ? ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp=db.command(query).execute(tanggalnull);
		return tmp;
	}


	public List<ODocument> getAllSesudahOrderByTgl(ODatabaseDocumentTx db, Date tgla) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
//		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" > ?  ");
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		return  db.command(query).execute(tgla);
	}

	public List<ODocument> getAll(ODatabaseDocumentTx db, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip "+start+" limit "+end);
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}
	
	public List<ODocument> getAll(ODatabaseDocumentTx db){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}
	
//	public ODocument factoryModelNull(ODatabaseDocumentTx db){
//		Date tanggalnull=new Date();
//		tanggalnull.setTime(0);
//		ODocument o=factoryModel(tanggalnull, 0, 0, 0, 0, 0, 0);
//		o.save();
//		return o;
//	}

}

package com.bmb.app.dao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class PenjualanhDao extends DaoAbstract {
	public static final  String  tgl="tgl";
	public static final  String  total="total";
	public PenjualanhDao() {
		super("Penjualanh");
	}

	public ODocument factoryModel(Date tgla, double totala){
		ODocument o=new ODocument(getClassName());
		o.field(tgl, tgla, OType.DATE);
		o.field(total, totala, OType.DOUBLE);
		return o;
	}

	
	public List<ODocument> getAllByColumnTgl(ODatabaseDocumentTx db,  ODocument value, int start, int end){
		if (value!=null && value.field("@class").equals(App.getPenjualanbDao().getClassName())) {
			int bln=value.field(PenjualanbDao.b);
			int th=value.field(PenjualanbDao.t);
			Calendar c=Calendar.getInstance();
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.YEAR, th);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.add(Calendar.DATE, -1);
			Date tglA=c.getTime();
			
			c=Calendar.getInstance();
			c.set(Calendar.YEAR, th);
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
			Date tglB=c.getTime();
			
			StringBuilder sql=new StringBuilder("select * from ");
			sql.append(getClassName());
			sql.append(" where ");
			sql.append(tgl);
			sql.append(" between ? and ? ");
			sql.append(" skip "+start+" limit "+end);
			sql.append(" order by tgl asc ");
			OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
			List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(tglA, tglB);
			return result;
		}else{
			return new ArrayList<ODocument>();
		}
		
	}
	public List<ODocument> getAllByColumnTgl(ODatabaseDocumentTx db,  ODocument value){
		if (value!=null && value.field("@class").equals(App.getPenjualanbDao().getClassName())) {
			int bln=value.field(PenjualanbDao.b);
			int th=value.field(PenjualanbDao.t);
			Calendar c=Calendar.getInstance();
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.add(Calendar.DATE, -1);
			Date tglA=c.getTime();
			
			c=Calendar.getInstance();
			c.set(Calendar.YEAR, th);
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
			Date tglB=c.getTime();
			
			StringBuilder sql=new StringBuilder("select * from ");
			sql.append(getClassName());
			sql.append(" where ");
			sql.append(tgl);
			sql.append(" between ? and ? ");
			OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
			List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(tglA, tglB);
			return result;
		}else{
			return new ArrayList<ODocument>();
		}
		
	}
	
	public long getCountByColumnTgl(ODatabaseDocumentTx db, ODocument value) {
		if (value!=null && value.field("@class").equals(App.getPenjualanbDao().getClassName())) {
			int bln=value.field(PenjualanbDao.b);
			int th=value.field(PenjualanbDao.t);
			Calendar c=Calendar.getInstance();
			c.set(Calendar.YEAR, th);
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.add(Calendar.DATE, -1);
			Date tglA=c.getTime();
			
			c=Calendar.getInstance();
			c.set(Calendar.YEAR, th);
			c.set(Calendar.MONTH, bln);
			c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
			Date tglB=c.getTime();
			
			StringBuilder sql=new StringBuilder("select count(*) as x from ");
			sql.append(getClassName());
			sql.append(" where ");
			sql.append(tgl);
			sql.append(" between ? and ? ");
			OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
			List<ODocument> result = db.command(query).execute(tglA, tglB);
			try {
				long count = Long.parseLong(result.get(0).field("x").toString());
				return count;
			} catch (Exception e) {
				App.printErr(e);
				return 0;
			}
		}else{
			return 0;
		}
		
	}
}

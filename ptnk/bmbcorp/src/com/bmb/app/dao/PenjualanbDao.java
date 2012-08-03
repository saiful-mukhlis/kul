package com.bmb.app.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class PenjualanbDao extends DaoAbstract {
	
	public static final   String b="b";
	public static final   String t="t";
	public static final   String total="total";

	public PenjualanbDao() {
		super("Penjualanb");
	}

	public ODocument factoryModel(int ba, int ta, double totala){
		ODocument o=new ODocument(getClassName());
		o.field(b, ba, OType.INTEGER);
		o.field(t, ta, OType.INTEGER);
		o.field(total, totala, OType.DOUBLE);
		return o;
	}	
	
	public List<ODocument> getAll(ODatabaseDocumentTx db, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" skip "+start+" limit "+end);
		sql.append(" order by t asc , b asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute();
		return result;
	}

}

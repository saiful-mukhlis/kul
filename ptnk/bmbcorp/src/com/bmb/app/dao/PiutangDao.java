package com.bmb.app.dao;


import java.math.BigDecimal;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Pelanggan;
import com.bmb.app.db.Penjualan;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

public class PiutangDao extends DaoAbstract {
	public static final  String  pelanggan="pelanggan";
	public static final  String 	total="total";
	public PiutangDao() {
		super("Piutang");
	}

	public ODocument factoryModel(ODocument pelanggana, double totala){
		ODocument o=new ODocument(getClassName());
		o.field(pelanggan, pelanggana, OType.LINK);
		o.field(total, totala, OType.DOUBLE);
		return o;
	}
	
	
	@Override
	public ODocument setNull(ODatabaseDocumentTx db, ODocument o) {
		List<ODocument> piutangds=App.getPiutangdDao().getAllByColumn(db, PiutangdDao.piutang, o.getIdentity());
		for (ODocument oDocument : piutangds) {
			App.getPiutangdDao().setNull(db, oDocument);
		}
		o.field(pelanggan,null, OType.LINK);
		o.save();
		return super.setNull(db, o);
	}
	
	public int delNull(ODatabaseDocumentTx db){
		int jml=App.getPiutangdDao().delNull(db);
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(pelanggan);
		sql.append(" is null ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp=db.command(query).execute();
		return tmp+jml;
	}


	
	

}

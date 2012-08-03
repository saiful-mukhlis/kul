package com.bmb.app.dao;


import java.util.Date;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.bmb.app.db.Piutang;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

public class PiutangdDao extends DaoAbstract {
	public static final  String 	tgl="tgl";
	public static final  String  piutang="piutang";
	public static final  String  type="type";
	public static final  String  d="d";
	public static final  String  k="k";
	public static final  String  sisa="sisa";
	public static final  String  ref="ref";
	
	public static final  String 	ftgl="tgl";
	public static final  String  fpiutang="piutang";
	public static final  String  ftype="type";
	public static final  String  fd="d";
	public static final  String  fk="k";
	public static final  String  fsisa="sisa";
	public static final  String  fref="ref";
	public PiutangdDao() {
		super("Piutangd");
	}

	public ODocument factoryModel(Date tgla, ODocument piutanga, int typea, double da, double ka, double sisaa, String refa){
		ODocument o=new ODocument(getClassName());
		o.field(tgl, tgla, OType.DATE);
		o.field(piutang, piutanga, OType.LINK);
		o.field(type, typea, OType.INTEGER);
		o.field(d, da, OType.DOUBLE);
		o.field(k, ka, OType.DOUBLE);
		o.field(sisa, sisaa, OType.DOUBLE);
		o.field(ref, refa);
		return o;
	}
	
	@Override
	public ODocument setNull(ODatabaseDocumentTx db, ODocument o) {
		o.field(piutang,null, OType.LINK);
		o.save();
		return super.setNull(db, o);
	}
	
	public int delNull(ODatabaseDocumentTx db){
		StringBuilder sql=new StringBuilder("delete from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(piutang);
		sql.append(" is null ");
		OCommandSQL query = new OCommandSQL(sql.toString());
		int tmp=db.command(query).execute();
		return tmp;
	}

}

package com.bmb.app.dao;


import java.util.Date;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.bmb.app.db.Product;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;

public class ProductdDao extends DaoAbstract {
	public static final  String 	  product="product";
	public static final  String 	  tgl="tgl";
	public static final  String 	  type="type";
	public static final  String 	  d="d";
	public static final  String 	  k="k";
	public static final  String 	  sisa="sisa";
	public static final  String 	  ref="ref";
	
	public static final  String 	  ftgl="Tanggal";
	public static final  String 	  ftype="Uraian";
	public static final  String 	  fd="Debit";
	public static final  String 	  fk="Kredit";
	public static final  String 	  fsisa="Sisa";
	public static final  String 	  fref="Ref";
	
	public ProductdDao() {
		super("Productd");
	}

	public ODocument factoryModel(ODocument producta, Date tgla, int typea, 
			double da, double ka, double sisaa, String refa
			){
		ODocument o=new ODocument(getClassName());
		o.field(product, producta, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(type, typea, OType.INTEGER);
		o.field(d, da, OType.DOUBLE);
		o.field(k, ka, OType.DOUBLE);
		o.field(sisa, sisaa, OType.DOUBLE);
		o.field(ref, refa);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModel(ODocument producta, Date tgla, int typea, 
			double da, double ka, double sisaa, String refa, int x
			){
		ODocument o=new ODocument(getClassName());
		o.field(product, producta, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(type, typea, OType.INTEGER);
		o.field(d, da, OType.DOUBLE);
		o.field(k, ka, OType.DOUBLE);
		o.field(sisa, sisaa, OType.DOUBLE);
		o.field(ref, refa);
		o.field("x", x, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModelUpdate(ODocument o,ODocument producta, Date tgla, int typea, 
			double da, double ka, double sisaa, String refa
			){
		o.field(product, producta, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(type, typea, OType.INTEGER);
		o.field(d, da, OType.DOUBLE);
		o.field(k, ka, OType.DOUBLE);
		o.field(sisa, sisaa, OType.DOUBLE);
		o.field(ref, refa);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	@Override
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

}

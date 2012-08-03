package com.bmb.app.dao;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Kandang;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class KandangdDao extends DaoAbstract {
	
	
	public static final  String  kandang="kandang";
	public static final  String  tgl="tgl";
	public static final  String  umur="umur";
	public static final  String  pupulasi="pupulasi";
	public static final  String  mati="mati";
	public static final  String  pakan="pakan";
	
	public static final  String  bagusbutir="bagusbutir";
	public static final  String  baguskg="baguskg";
	
	public static final  String  retakbutir="retakbutir";
	public static final  String  retakkg="retakkg";
	
	public static final  String  ftgl="Tanggal";
	public static final  String  fumur="Umur";
	public static final  String  fpupulasi="Populasi";
	public static final  String  fmati="Mati/Afkir";
	public static final  String  fjumlah="Jumlah";
	public static final  String  fpakan="Pakan/hari";
	public static final  String  fproduksi="Produksi Telur";
	public static final  String  fretak="Julah yg Retak";
	public static final  String  fbutirb="Butir";
	public static final  String  fkgb="Kg";
	public static final  String  fbutir="Butir*";
	public static final  String  fkg="Kg*";
	public static final  String  ftotaltelor="Total Telur";
	public static final  String  fpp="Peforma Produksi";
	public static final  String  fhd="% HD";
	public static final  String  fp="Btr";
	public static final  String  ffcr="FCR";
	public static final  String  fket="Ket";
	public static final  String  fket2="Ket*";
	public static final  String  fa="Abnormalitas";
	
	
	public List<ODocument> getAllForPrint(ODatabaseDocumentTx db, ODocument kandanga, int bln, int th){
		if (kandanga==null) {
			return new ArrayList<ODocument>();
		}
		Calendar c=Calendar.getInstance();
		c.set(Calendar.MONTH, bln);
		c.set(Calendar.YEAR, th);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DATE, -1);
		Date tglA=c.getTime();
		
		c=Calendar.getInstance();
		c.set(Calendar.MONTH, bln);
		c.set(Calendar.YEAR, th);
		c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
		Date tglB=c.getTime();
		
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kandang);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" between ? and ? ");
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(kandanga.getIdentity(), tglA, tglB);
		return result;
	}
	

	public ODocument factoryModel(ODocument kandanga, Date tgla, int umura,
			long pupulasia, int matia, double pakana, int bagusbutira,
			double baguskga, int retakbutira, double retakkga){
		ODocument o=new ODocument(getClassName());
		o.field(kandang, kandanga, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(umur, umura, OType.INTEGER);
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
	//o.field("x", 1, OType.INTEGER);
	public ODocument factoryModel(ODocument kandanga, Date tgla, int umura,
			long pupulasia, int matia, double pakana, int bagusbutira,
			double baguskga, int retakbutira, double retakkga, int x){
		ODocument o=new ODocument(getClassName());
		o.field(kandang, kandanga, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(umur, umura, OType.INTEGER);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
		o.field(pakan, pakana, OType.DOUBLE);
		o.field(bagusbutir, bagusbutira, OType.INTEGER);
		o.field(baguskg, baguskga, OType.DOUBLE);
		o.field(retakbutir, retakbutira, OType.INTEGER);
		o.field(retakkg, retakkga, OType.DOUBLE);
		o.field("x", x, OType.INTEGER);
		return o;
	}
	public ODocument factoryModelUpdate(ODocument o,ODocument kandanga, Date tgla, int umura,
			long pupulasia, int matia, double pakana, int bagusbutira,
			double baguskga, int retakbutira, double retakkga){
		o.field(kandang, kandanga, OType.LINK);
		o.field(tgl, tgla, OType.DATE);
		o.field(umur, umura, OType.INTEGER);
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
	
	public String getKet2(ODocument o){
		if (getA(o)<=1) {
			return "Baik";
		}
		return "Jelek";
	}
	
	public String getKet1(ODocument o){
		if (getFcr(o)>=2.3) {
			return "Baik";
		}
		return "Jelek";
	}
	
	public double getA(ODocument o){
		double r=o.field(retakkg);
		double b=o.field(baguskg);
		if (b==0) {
			b=1;
		}
		return r/b*100;
	}
	
	public double getFcr(ODocument o){
		if (o.field(pakan)!=null) {
			double p=o.field(pakan);
			double t=getTotalTelurKg(o);
			if (t==0) {
				t=1;
			}
			return p/t;
		}else{
			return 0;
		}
		
	}
	
	public double getHD(ODocument o){
		long p=o.field(pupulasi);
		if (p==0) {
			p=1;
		}
		double t=getTotalTelurKg(o);
		return t/p;
	}
	
	public double getP(ODocument o){
		double p=getTotalTelurButir(o);
		if (p==0) {
			p=1;
		}
		double t=getTotalTelurKg(o);
		return t/p*1000;
	}
	
	public int getTotalTelurButir(ODocument o){
		int a=o.field(bagusbutir);
		int b=o.field(retakbutir);
		return a+b;
	}
	public double getTotalTelurKg(ODocument o){
		double a=o.field(baguskg);
		double b=o.field(retakkg);
		return a+b;
	}
	public KandangdDao() {
		super("Kandangd");
	}

	public ODocument getOneOrderByTgl(ODatabaseDocumentTx db,  Object value) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kandang);
		sql.append(" = ? ");
		sql.append(" order by tgl asc ");
		sql.append(" limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(value);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
	public ODocument getOneOrderByTgl1Level(ODatabaseDocumentTx db,ODocument kandanga, Date tgla) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kandang);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" < ?  ");
		sql.append(" order by tgl desc ");
		sql.append(" limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(kandanga.getIdentity(), tgla);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
	public List<ODocument> getAllSesudahOrderByTgl(ODatabaseDocumentTx db,ODocument kandanga, Date tgla) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kandang);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" > ?  ");
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		return  db.command(query).execute(kandanga.getIdentity(), tgla);
	}
	
	public List<ODocument> getAllByColumn(ODatabaseDocumentTx db, String kolom, Object value, int start, int end){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
		sql.append(" = ? ");
		sql.append(" skip "+start+" limit "+end);
		sql.append(" order by tgl asc ");
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
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(value);
		return result;
	}
}

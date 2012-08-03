package com.bmb.app.dao;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Pelanggan;
import com.bmb.app.db.Penjualan;
import com.bmb.app.db.Piutangd;
import com.bmb.app.db.Productd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class PenjualanDao extends DaoAbstract {
	
	public static final  String  code="code";
	public static final  String  tgl="tgl";
	public static final  String  pelanggan="pelanggan";
	public static final  String  product="product";
	public static final  String  harga="harga";
	public static final  String  jml="jml";
	public static final  String  total1="total1";
	public static final  String  diskon="diskon";
	public static final  String  diskonp="diskonp";
	public static final  String  totaldiskon="totaldiskon";
	public static final  String  total="total";
	public static final  String  bayar="bayar";
	public static final  String  d="d";
	public static final  String  k="k";
	public static final  String  note="note";
	
	public static final  String  fcode="Kode";
	public static final  String  ftgl="Tanggal";
	public static final  String  fpelanggan="Pelanggan";
	public static final  String  fharga="Harga";
	public static final  String  fjml="Jumlah";
	public static final  String  ftotal1="";
	public static final  String  fdiskon="Diskon[Rp]";
	public static final  String  fdiskonp="Diskon[%]";
	public static final  String  ftotaldiskon="Total Diskon";
	public static final  String  ftotal="Total";
	public static final  String  fbayar="Bayar";
	public static final  String  fd="Debit";
	public static final  String  fk="Kredit";
	public static final  String  fhutang="Hutang";
	public static final  String  fkembali="Kembali";
	public static final  String  fnote="Keterangan";

	public PenjualanDao() {
		super("Penjualan");
	}

	public ODocument factoryModel(String codea, Date tgla, ODocument pelanggana, double hargaa, double jmla, double total1a, double diskona, 
			double diskonpa, double totaldiskona, double totala, double bayara, double da, double ka, String notea){
		ODocument o=new ODocument(className);
		o.field(code,codea);
		o.field(tgl,tgla, OType.DATE);
		o.field(pelanggan,pelanggana, OType.LINK);
		o.field(harga,hargaa, OType.DOUBLE);
		o.field(jml,jmla, OType.DOUBLE);
		o.field(total1,total1a, OType.DOUBLE);
		o.field(diskon,diskona, OType.DOUBLE);
		o.field(diskonp,diskonpa, OType.DOUBLE);
		o.field(totaldiskon,totaldiskona, OType.DOUBLE);
		o.field(total,totala, OType.DOUBLE);
		o.field(bayar,bayara, OType.DOUBLE);
		o.field(d,da, OType.DOUBLE);
		o.field(k,ka, OType.DOUBLE);
		o.field(note,notea);
		
		return o;
	}
	
	
	
	@Override
	public ODocument setNull(ODatabaseDocumentTx db, ODocument o) {
		o.field(pelanggan,null, OType.LINK);
		o.save();
		return super.setNull(db, o);
	}

	public ODocument factoryModelUpdate(ODocument o,String codea, Date tgla, ODocument pelanggana, double hargaa, double jmla, double total1a, double diskona, 
			double diskonpa, double totaldiskona, double totala, double bayara, double da, double ka, String notea){
		o.field(code,codea);
		o.field(tgl,tgla, OType.DATE);
		o.field(pelanggan,pelanggana, OType.LINK);
		o.field(harga,hargaa, OType.DOUBLE);
		o.field(jml,jmla, OType.DOUBLE);
		o.field(total1,total1a, OType.DOUBLE);
		o.field(diskon,diskona, OType.DOUBLE);
		o.field(diskonp,diskonpa, OType.DOUBLE);
		o.field(totaldiskon,totaldiskona, OType.DOUBLE);
		o.field(total,totala, OType.DOUBLE);
		o.field(bayar,bayara, OType.DOUBLE);
		o.field(d,da, OType.DOUBLE);
		o.field(k,ka, OType.DOUBLE);
		o.field(note,notea);
		
		return o;
	}
	
	public ODocument save1(ODatabaseDocumentTx db, ODocument model){
		String tmp=model.field("code");
		if (tmp==null || tmp.trim().equalsIgnoreCase("AUTO") || tmp.trim().equalsIgnoreCase("")) {
			ODocument num=App.getNumberIdDao().getLangByNamaTable(db, getClassName());
			if (num==null) {
				num=App.getNumberIdDao().createContentDefaultModel(db, getClassName());
			}
			
			long now=num.field("now");
			DecimalFormat x=new DecimalFormat("00000000");
			model.field("code", x.format(now)+"");
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

	@Override
	public ODocument save(ODatabaseDocumentTx db, ODocument model) {
		
		try{
			Date tgltmp=model.field(tgl);
			
			Calendar c=Calendar.getInstance();
			c.setTime(tgltmp);
			  int bulantmp=c.get(Calendar.MONTH);
			  int tahuntmp=c.get(Calendar.YEAR);
			  ODocument bulanantmp=App.getPenjualanbDao().getOne(db, PenjualanbDao.b, bulantmp, PenjualanbDao.t, tahuntmp);
			  if (bulanantmp==null) {
				bulanantmp=App.getPenjualanbDao().factoryModel(bulantmp, tahuntmp, 0);
			}
			
			  db.begin(TXTYPE.OPTIMISTIC);

			  save1(db, model);
			  
			  //yg terjadi pada penjulan
			  // - stok
			  // + history stok
			  // + bila hutang -> hutang
			  // + penjualan harian
			  // + penjualan bulanan
			  
			  // -stok
			  ODocument tmp=DataUser.getProduct();
			  double stok=tmp.field(ProductDao.saldo);
			  double jmlPenjulan=model.field(PenjualanDao.jml);
			  stok=stok-jmlPenjulan;
			  tmp.field(ProductDao.saldo, stok, OType.DOUBLE);
			  tmp.save();
			  
			  
			  double totaltmp=model.field(total);
			  // + history stok
			  ODocument htmp=App.getProductdDao().factoryModel(tmp, tgltmp, Productd.TYPE_PENJUALAN, 
					  0, (Double)model.field(jml), (Double) tmp.field(ProductDao.saldo), 
					  App.getT(db, "Penjualan")+" "+model.field(code));
			  htmp.save();
			  
			// + bila hutang -> hutang
			  double ktmp=model.field(k);
			  ODocument pelanggantmp=model.field(pelanggan);
			  if (ktmp>0) {
				ODocument piutangtmp=App.getPiutangDao().getOne(db, PiutangDao.pelanggan, pelanggantmp.getIdentity() );
				if (piutangtmp==null) {
					piutangtmp=App.getPiutangDao().factoryModel(pelanggantmp, 0);
					piutangtmp.save();
				}
				double ksebelumnya=piutangtmp.field(PiutangDao.total);
				ksebelumnya=ksebelumnya+ktmp;
				piutangtmp.field(PiutangDao.total, ksebelumnya, OType.DOUBLE);
				piutangtmp.save();
				
				ODocument piutangdtmp=App.getPiutangdDao().factoryModel(tgltmp, piutangtmp, Piutangd.TYPE_PENJUALAN, ktmp,0, ksebelumnya, App.getT(db, "Penjualan")+" "+model.field(code));
				piutangdtmp.save();
				
			}
			  
			  // penjualan harian total
			  ODocument hariantmp=App.getPenjualanhDao().getOne(db, PenjualanhDao.tgl, tgltmp);
			  if (hariantmp==null) {
				hariantmp=App.getPenjualanhDao().factoryModel(tgltmp, 0);
				hariantmp.save();
			}
			  double penjualanhariantmp=hariantmp.field(PenjualanhDao.total);
			  penjualanhariantmp=penjualanhariantmp+totaltmp;
			  hariantmp.field(PenjualanhDao.total, penjualanhariantmp);
			  hariantmp.save();
			  
			  // penjualan bulanan total
			  double penjualanbtmp=bulanantmp.field(PenjualanbDao.total);
			  penjualanbtmp=penjualanbtmp+totaltmp;
			  bulanantmp.field(PenjualanbDao.total, penjualanbtmp, OType.DOUBLE);
			  bulanantmp.save();
			  
			  db.commit();
			}catch( Exception e ){
				App.printErr(e);
			  db.rollback();
			} 

		
		
		return model;
	}
	
	
	
	public List<ODocument> getAllForPrintByBulan(ODatabaseDocumentTx db, int bln, int th){
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
		sql.append(tgl);
		sql.append(" between ? and ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute( tglA, tglB);
		return result;
	}
	
	public List<ODocument> getAllForPrintByTgl(ODatabaseDocumentTx db,Date tgla){
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(tgl);
		sql.append(" = ? ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(tgla);
		return result;
	}
	

}

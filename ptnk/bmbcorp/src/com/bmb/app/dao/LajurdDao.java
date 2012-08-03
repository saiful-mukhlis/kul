package com.bmb.app.dao;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Lajur;
import com.bmb.app.db.Productd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class LajurdDao extends DaoAbstract {
	
	public static final  String  lajur="lajur";
	public static final  String  tgl="tgl";
	public static final  String  pupulasi="pupulasi";
	public static final  String  mati="mati";
	public static final  String  butirpagibagus="butirpagibagus";
	public static final  String  butirpagiretak="butirpagiretak";
	public static final  String  butirsorebagus="butirsorebagus";
	public static final  String  butirsoreretak="butirsoreretak";
	
	public static final  String  kgpagibagus="kgpagibagus";
	public static final  String  kgpagiretak="kgpagiretak";
	public static final  String  kgsorebagus="kgsorebagus";
	public static final  String  kgsoreretak="kgsoreretak";
	
	public static final  String  flajur="Lajur";
	public static final  String  ftgl="Tanggal";
	public static final  String  fpupulasi="Populasi";
	public static final  String  fmati="Mati/Afkir";
	public static final  String  fproduksi="Produksi Telur /Butir";
	public static final  String  fbutirpagibagus="Pagi[Bgs]";
	public static final  String  fbutirpagiretak="Pagi[Rtk]";
	public static final  String  fbutirsorebagus="Sore[Bgs]";
	public static final  String  fbutirsoreretak="Sore[Rtk]";
	
	public static final  String  fproduksi2="Produksi Telur /Kg";
	public static final  String  fkgpagibagus="Pagi[Bgs]*";
	public static final  String  fkgpagiretak="Pagi[Rtk]*";
	public static final  String  fkgsorebagus="Sore[Bgs]*";
	public static final  String  fkgsoreretak="Sore[Rtk]*";
	
	public static final  String  ftotalbutir="Total Btr";
	public static final  String  fbagus="Bagus";
	public static final  String  fretak="Retak";
	
	public static final  String  fbaguskg="Bagus*";
	public static final  String  fretakkg="Retak*";
	
	public static final  String  fperforma="Performa Produksi";
	public static final  String  fhd="% HD";
	public static final  String  fp="Btr Tlr[gr/bt]";
	
	
	
	
	@Override
	public ODocument save(ODatabaseDocumentTx db, ODocument model) {
		
		try{
			  db.begin(TXTYPE.OPTIMISTIC);
			  // kandang
			  // stok
			  // kandang all
			  //type 2
			  
			  model.save();
			  db.commit();
			}catch( Exception e ){
			  db.rollback();
			} 

		
		return model;
	}
	
	public ODocument modelSave(ODocument lajura, Date tgla, long pupulasia, int matia,
			int butirpagibagusa, int butirpagiretaka, int butirsorebagusa,
			int butirsoreretaka, double kgpagibagusa, double kgpagiretaka,
			double kgsorebagusa, double kgsoreretaka, ODocument lama, boolean baru, boolean pertama, boolean pagi, ODatabaseDocumentTx db){
		
//		Date tanggalnull=new Date();
//		tanggalnull.setTime(0);
		
		ODocument kandangtmp=lajura.field(LajurDao.kandang);
		ODocument tmpx = null;
		try{
			  
			App.getLajurdDao().delNull(db);
			App.getProductdDao().delNull(db);
			
			ODocument o=null;
			if (lama!=null && !baru) {
				o=lama;
			}
			boolean obaru=false;
			if (o==null) {
				o=factoryModel(lajura, tgla, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
				o.save();
				obaru=true;
			}
			
			if (obaru!=baru) {
				App.showErrSementara("baru tidak cocok");
			}
			
			ODocument ktmp=App.getKandangdDao().getOne(db, KandangdDao.tgl, tgla, KandangdDao.kandang, kandangtmp.getIdentity());
			boolean kandangdIsBaru=false;
			  if (ktmp==null) {
				  //ktmp=App.getKandangdDao().createODNull();
				
				  ktmp=App.getKandangdDao().factoryModel(kandangtmp, tgla, 0, 0, 0, 0, 0, 0, 0, 0, 1);
				  ktmp.save();
				  kandangdIsBaru=true;
				  
				  
			  }
			  
			  ODocument prodtmp=DataUser.getProduct();
			  
			  ODocument htmp=App.getProductdDao().factoryModel(prodtmp, tgla, 0, 0, 0, 0, "", 1);
			  htmp.save();
			  
			  
			  ODocument kalltmp=App.getKandangallDao().getOne(db, KandangallDao.tgl, tgla);
			  boolean kandangallIsBaru=false;
			  if (kalltmp==null) {
				  kalltmp=App.getKandangallDao().factoryModelSementara(tgla, 0, 0, 0, 0, 0, 0, 1);
				  kalltmp.save();
				  kandangallIsBaru=true;
			}
			
			//db.begin(TXTYPE.OPTIMISTIC);
			  // kandang
			  // stok
			  // kandang all
			  //type 2
			
			  
			  
			  if (!pertama) {
				ODocument sebelumnya1Level=getOneOrderByTgl1Level(db, lajura, tgla);
				if (sebelumnya1Level!=null) {
					long populasiSebelumnya=sebelumnya1Level.field(LajurdDao.pupulasi);
					int matiSebelumnya=sebelumnya1Level.field(LajurdDao.mati);
					pupulasia=populasiSebelumnya-matiSebelumnya;
				}else{
					App.showErrSementara("Error pada penyimpanan Produksi lajur, kode : 153");
				}
			}
			
			
			  
			  //baru
			  if (obaru) {
				  App.info("baru");
				  o=factoryModelUpdate(o, lajura, tgla, pupulasia, matia, butirpagibagusa, butirpagiretaka, butirsorebagusa, butirsoreretaka, kgpagibagusa, kgpagiretaka, kgsorebagusa, kgsoreretaka);
				  //o.save();
				  App.info("save lajurd");
				  tmpx=o;
				  //kandang
				  
				  if (kandangdIsBaru) {
					//kandangd baru
					  
					  App.info("Kandangd Baru");
					  long jml=App.getKandangdDao().getCountByColumn(db, KandangdDao.kandang, kandangtmp.getIdentity());
					  if (jml!=0) {
							Calendar cini=Calendar.getInstance();
							cini.setTime(tgla);
							long angkatglini=cini.getTimeInMillis();
						//tidak pertama
						  ODocument oatas=App.getKandangdDao().getOneOrderByTgl1Level(db, kandangtmp, tgla);
						  if (oatas!=null) {
							  int umtmp=oatas.field(KandangdDao.umur);
								Date tglatas=oatas.field(KandangdDao.tgl);
								Calendar catas=Calendar.getInstance();
								catas.setTime(tglatas);
								long ankaatas=catas.getTimeInMillis();
								long selisih=angkatglini-ankaatas;
								long daySelisih=selisih/86400000;
								int um=(int) (daySelisih+umtmp);
								 ktmp=App.getKandangdDao().factoryModelUpdate(ktmp, kandangtmp, tgla, um, pupulasia, matia, 0, butirpagibagusa+butirsorebagusa,
										  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
						}else{
							ktmp=App.getKandangdDao().factoryModelUpdate(ktmp, kandangtmp, tgla, 0, pupulasia, matia, 0, butirpagibagusa+butirsorebagusa,
									  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
						}
						  
					}else{
					  ktmp=App.getKandangdDao().factoryModelUpdate(ktmp, kandangtmp, tgla, 0, pupulasia, matia, 0, butirpagibagusa+butirsorebagusa,
							  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
					 // ktmp.save();
					  App.info("Save Kandangd baru");
				  }
				}else{
					// kandang sudah ada tpi lajur baru, jdi tinggal tambah
					int kmatitmp=ktmp.field(KandangdDao.mati);
					kmatitmp=kmatitmp+matia;
					ktmp.field(KandangdDao.mati, kmatitmp, OType.INTEGER);
					int bagusbutirtmp=ktmp.field(KandangdDao.bagusbutir);
					bagusbutirtmp=bagusbutirtmp+butirpagibagusa+butirsorebagusa;
					ktmp.field(KandangdDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
					double bagyskgtmp=ktmp.field(KandangdDao.baguskg);
					bagyskgtmp=bagyskgtmp+kgpagibagusa+kgsorebagusa;
					ktmp.field(KandangdDao.baguskg, bagyskgtmp, OType.DOUBLE);
					
					int retakbutirtmp=ktmp.field(KandangdDao.retakbutir);
					retakbutirtmp=retakbutirtmp+butirpagiretaka+butirsoreretaka;
					ktmp.field(KandangdDao.retakbutir, retakbutirtmp, OType.INTEGER);
					double retakkgtmp=ktmp.field(KandangdDao.retakkg);
					retakkgtmp=retakkgtmp+ kgpagiretaka+kgsoreretaka;
					ktmp.field(KandangdDao.retakkg, retakkgtmp, OType.DOUBLE);
					//ktmp.save();
				}
				  
				  App.info("Menjalankan update stok stok");
				  // stok
				  double stoktmp=prodtmp.field(ProductDao.saldo);
				  //di sini yang di tambahkan yag bagus aja ato ama yg retak,
				  // utk sementara yg bagus aja
				  stoktmp=stoktmp+kgpagibagusa+kgsorebagusa;
				  prodtmp.field(ProductDao.saldo, stoktmp, OType.DOUBLE);
				  //prodtmp.save();
				  App.info("Menyimpan update stok stok");
				  
				// + history stok
				  htmp=App.getProductdDao().factoryModelUpdate(htmp, prodtmp, tgla, Productd.TYPE_PRODUKSI_PLUS, 
						  kgpagibagusa+kgsorebagusa, 0 , stoktmp, 
						  App.getT(db, "Tambah data produksi")+" "+lajura.field(LajurDao.name)+" "+App.dateFormat.format(tgla));
				  //htmp.save();
				  
				  
				  //kandang all
				  if (kandangallIsBaru) {
					//baru
					  kalltmp=App.getKandangallDao().factoryModelUpdate(kalltmp, tgla,  pupulasia, matia, butirpagibagusa+butirsorebagusa,
							  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
					  //kalltmp.save();
				}else{
					//tambahkan aj
					int kmatitmp=kalltmp.field(KandangallDao.mati);
					kmatitmp=kmatitmp+matia;
					kalltmp.field(KandangallDao.mati, kmatitmp, OType.INTEGER);
					int bagusbutirtmp=kalltmp.field(KandangallDao.bagusbutir);
					bagusbutirtmp=bagusbutirtmp+butirpagibagusa+butirsorebagusa;
					kalltmp.field(KandangallDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
					double bagyskgtmp=kalltmp.field(KandangallDao.baguskg);
					bagyskgtmp=bagyskgtmp+kgpagibagusa+kgsorebagusa;
					kalltmp.field(KandangallDao.baguskg, bagyskgtmp, OType.DOUBLE);
					
					int retakbutirtmp=kalltmp.field(KandangallDao.retakbutir);
					retakbutirtmp=retakbutirtmp+butirpagiretaka+butirsoreretaka;
					kalltmp.field(KandangallDao.retakbutir, retakbutirtmp, OType.INTEGER);
					double retakkgtmp=kalltmp.field(KandangallDao.retakkg);
					retakkgtmp=retakkgtmp+ kgpagiretaka+kgsoreretaka;
					kalltmp.field(KandangallDao.retakkg, retakkgtmp, OType.DOUBLE);
					//kalltmp.save();
					
				}
			}else{
				//lajur tidak baru
				
				// kandang
				  if (kandangdIsBaru) {
					//imposivle
					  App.showErrSementara("imposible");
					  ktmp=App.getKandangdDao().factoryModelUpdate(ktmp, kandangtmp, tgla, 0, pupulasia, matia, 0, butirpagibagusa+butirsorebagusa,
							  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
					 // ktmp.save();
				}else{
					// kandang sudah ada tpi lajur edit, jdi di kurangi terus di tambah (- lama + baru)
					int kmatitmp=ktmp.field(KandangdDao.mati);
					int matilama=lama.field(LajurdDao.mati);
					kmatitmp=kmatitmp+matia-matilama;
					ktmp.field(KandangdDao.mati, kmatitmp, OType.INTEGER);
					
					int bagusbutirtmp=ktmp.field(KandangdDao.bagusbutir);
					int butirbagibaguslama=lama.field(LajurdDao.butirpagibagus);
					int butirsorebaguslama=lama.field(LajurdDao.butirsorebagus);
					bagusbutirtmp=bagusbutirtmp+butirpagibagusa+butirsorebagusa-butirbagibaguslama-butirsorebaguslama;
					ktmp.field(KandangdDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
					
					double kgpagibaguslama=lama.field(LajurdDao.kgpagibagus);
					double kgsorebaguslama=lama.field(LajurdDao.kgsorebagus);
					double bagyskgtmp=ktmp.field(KandangdDao.baguskg);
					bagyskgtmp=bagyskgtmp+kgpagibagusa+kgsorebagusa-kgpagibaguslama-kgsorebaguslama;
					ktmp.field(KandangdDao.baguskg, bagyskgtmp, OType.DOUBLE);
					
					int butirpagiretaklama=lama.field(LajurdDao.butirpagiretak);
					int butirsoreretaklama=lama.field(LajurdDao.butirsoreretak);
					int retakbutirtmp=ktmp.field(KandangdDao.retakbutir);
					retakbutirtmp=retakbutirtmp+butirpagiretaka+butirsoreretaka-butirpagiretaklama-butirsoreretaklama;
					ktmp.field(KandangdDao.retakbutir, retakbutirtmp, OType.INTEGER);
					
					double kgpagiretaklama=lama.field(LajurdDao.kgpagiretak);
					double kgsoreretaklama=lama.field(LajurdDao.kgsoreretak);
					double retakkgtmp=ktmp.field(KandangdDao.retakkg);
					retakkgtmp=retakkgtmp+ kgpagiretaka+kgsoreretaka-kgpagiretaklama-kgsoreretaklama;
					ktmp.field(KandangdDao.retakkg, retakkgtmp, OType.DOUBLE);
					//ktmp.save();
				}
				  
				  //kandang all
				  if (kandangallIsBaru) {
					//imposible
					  App.showErrSementara("imposible");
					  kalltmp=App.getKandangallDao().factoryModelUpdate(kalltmp, tgla,  pupulasia, matia, butirpagibagusa+butirsorebagusa,
							  kgpagibagusa+kgsorebagusa, butirpagiretaka+butirsoreretaka, kgpagiretaka+kgsoreretaka);
					 // kalltmp.save();
				}else{
					//tambahkan dan min lama
					int matilama=lama.field(LajurdDao.mati);
					int kmatitmp=kalltmp.field(KandangallDao.mati);
					kmatitmp=kmatitmp+matia-matilama;
					kalltmp.field(KandangallDao.mati, kmatitmp, OType.INTEGER);
					
					int butirbagibaguslama=lama.field(LajurdDao.butirpagibagus);
					int butirsorebaguslama=lama.field(LajurdDao.butirsorebagus);
					int bagusbutirtmp=kalltmp.field(KandangallDao.bagusbutir);
					bagusbutirtmp=bagusbutirtmp+butirpagibagusa+butirsorebagusa-butirbagibaguslama-butirsorebaguslama;
					kalltmp.field(KandangallDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
					
					double kgpagibaguslama=lama.field(LajurdDao.kgpagibagus);
					double kgsorebaguslama=lama.field(LajurdDao.kgsorebagus);
					double bagyskgtmp=kalltmp.field(KandangallDao.baguskg);
					bagyskgtmp=bagyskgtmp+kgpagibagusa+kgsorebagusa-kgpagibaguslama-kgsorebaguslama;
					kalltmp.field(KandangallDao.baguskg, bagyskgtmp, OType.DOUBLE);
					
					int butirpagiretaklama=lama.field(LajurdDao.butirpagiretak);
					int butirsoreretaklama=lama.field(LajurdDao.butirsoreretak);
					int retakbutirtmp=kalltmp.field(KandangallDao.retakbutir);
					retakbutirtmp=retakbutirtmp+butirpagiretaka+butirsoreretaka-butirpagiretaklama-butirsoreretaklama;
					kalltmp.field(KandangallDao.retakbutir, retakbutirtmp, OType.INTEGER);
					
					double kgpagiretaklama=lama.field(LajurdDao.kgpagiretak);
					double kgsoreretaklama=lama.field(LajurdDao.kgsoreretak);
					double retakkgtmp=kalltmp.field(KandangallDao.retakkg);
					retakkgtmp=retakkgtmp+ kgpagiretaka+kgsoreretaka-kgpagiretaklama-kgsoreretaklama;
					kalltmp.field(KandangallDao.retakkg, retakkgtmp, OType.DOUBLE);
					//kalltmp.save();
				}
				  
				  
				  //stok
				  // stok
				  double stoktmp=prodtmp.field(ProductDao.saldo);
				  //di sini yang di tambahkan yag bagus aja ato ama yg retak,
				  // utk sementara yg bagus aja
				  double kgpagibaguslama=lama.field(LajurdDao.kgpagibagus);
				  double kgsorebaguslama=lama.field(LajurdDao.kgsorebagus);
					
				  stoktmp=stoktmp+kgpagibagusa+kgsorebagusa-kgpagibaguslama-kgsorebaguslama;
				  prodtmp.field(ProductDao.saldo, stoktmp, OType.DOUBLE);
				 // prodtmp.save();
				  
				// + history stok
				  htmp=App.getProductdDao().factoryModelUpdate(htmp, prodtmp, tgla, Productd.TYPE_PRODUKSI_EDIT, 
						  kgpagibagusa+kgsorebagusa, kgpagibaguslama-kgsorebaguslama , stoktmp, 
						  App.getT(db, "Update data produksi")+" "+lajura.field(LajurDao.name)+" "+App.dateFormat.format(tgla));
				 // htmp.save();
				  
				  o=lama;
//					o.field(lajur, lajura, OType.LINK);
//					o.field(tgl , tgla, OType.DATE);
					if (pertama) {
						o.field(pupulasi, pupulasia, OType.LONG);
					}
					o.field(mati, matia, OType.INTEGER);
					if (pagi) {
						o.field(butirpagibagus, butirpagibagusa, OType.INTEGER);
						o.field(butirpagiretak, butirpagiretaka, OType.INTEGER);
						o.field(kgpagibagus, kgpagibagusa, OType.DOUBLE);
						o.field(kgpagiretak, kgpagiretaka, OType.DOUBLE);
					}else{
						o.field(butirsorebagus, butirsorebagusa, OType.INTEGER);
						o.field(butirsoreretak, butirsoreretaka, OType.INTEGER);
						
						o.field(kgsorebagus, kgsorebagusa, OType.DOUBLE);
						o.field(kgsoreretak, kgsoreretaka, OType.DOUBLE);
					}
					
					//xo.save();
					tmpx=o;
			}
			  
			  
			  
			 
//			  o.save();
			 
//			  ktmp.save();
//			  printAll(db);
//			  App.info("sebelum bigin");
			  db.begin(TXTYPE.OPTIMISTIC);
			  o.save();
			  ktmp.save();
			  
			  prodtmp.save();
			  htmp.save();
			  kalltmp.save();
			  
			  List<ODocument> sesudahs=getAllSesudahOrderByTgl(db, lajura, tgla);
			  int itmp=0;
			  for (ODocument oDocument : sesudahs) {
				  if (itmp==0) {
					long ptmp=pupulasia-matia;
					oDocument.field(LajurdDao.pupulasi, ptmp, OType.LONG);
					oDocument.save();
				}else{
					ODocument sebelumnya1Level=sesudahs.get(itmp-1);
						long populasiSebelumnya=sebelumnya1Level.field(LajurdDao.pupulasi);
						int matiSebelumnya=sebelumnya1Level.field(LajurdDao.mati);
						long ptmp=populasiSebelumnya-matiSebelumnya;
						oDocument.field(LajurdDao.pupulasi, ptmp, OType.LONG);
						oDocument.save();
				}
				
				itmp++;
			}
			  
			  // kandangd
			  List<ODocument> ksesudahs=App.getKandangdDao().getAllSesudahOrderByTgl(db, kandangtmp, tgla);
			  itmp=0;
			  for (ODocument oDocument : ksesudahs) {
				  if (itmp==0) {
					 long pkd= ktmp.field(KandangdDao.pupulasi);
					 int mkd=ktmp.field(KandangdDao.mati);
					long ptmp=pkd-mkd;
					oDocument.field(KandangdDao.pupulasi, ptmp, OType.LONG);
					oDocument.save();
				}else{
					ODocument sebelumnya1Level=ksesudahs.get(itmp-1);
						long populasiSebelumnya=sebelumnya1Level.field(KandangdDao.pupulasi);
						int matiSebelumnya=sebelumnya1Level.field(KandangdDao.mati);
						long ptmp=populasiSebelumnya-matiSebelumnya;
						oDocument.field(KandangdDao.pupulasi, ptmp, OType.LONG);
						oDocument.save();
				}
				
				itmp++;
			}
			  
			  // kandangall
			   ksesudahs=App.getKandangallDao().getAllSesudahOrderByTgl(db, tgla);
			  itmp=0;
			  for (ODocument oDocument : ksesudahs) {
				  if (itmp==0) {
					 long pkd= kalltmp.field(KandangallDao.pupulasi);
					 int mkd=kalltmp.field(KandangallDao.mati);
					long ptmp=pkd-mkd;
					oDocument.field(KandangallDao.pupulasi, ptmp, OType.LONG);
					oDocument.save();
				}else{
					ODocument sebelumnya1Level=ksesudahs.get(itmp-1);
						long populasiSebelumnya=sebelumnya1Level.field(KandangallDao.pupulasi);
						int matiSebelumnya=sebelumnya1Level.field(KandangallDao.mati);
						long ptmp=populasiSebelumnya-matiSebelumnya;
						oDocument.field(KandangallDao.pupulasi, ptmp, OType.LONG);
						oDocument.save();
				}
				
				itmp++;
			}
			  
			  
//			  App.info("Akan menjalankan komit");
			  db.commit();
//			  App.info("Sudah Comit");
			}catch( Exception e ){
				App.printErr(e);
			  db.rollback();
			} finally{
				App.getLajurdDao().delNull(db);
				App.getProductdDao().delNull(db);
				
			}
		return tmpx;
	}



	public ODocument getOneOrderByTgl(ODatabaseDocumentTx db, String kolom, Object value) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(kolom);
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
	
	public ODocument getOneOrderByTgl1Level(ODatabaseDocumentTx db,ODocument lajura, Date tgla) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(lajur);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" < ?  ");
		sql.append(" order by tgl desc ");
		sql.append(" limit 1");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query).execute(lajura.getIdentity(), tgla);
		if (result.size() == 0) {
			return null;
		} else {
			return (ODocument) result.get(0);
		}
	}
	
	public List<ODocument> getAllSesudahOrderByTgl(ODatabaseDocumentTx db,ODocument lajura, Date tgla) {
		StringBuilder sql=new StringBuilder("select * from ");
		sql.append(getClassName());
		sql.append(" where ");
		sql.append(lajur);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" > ?  ");
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		return  db.command(query).execute(lajura.getIdentity(), tgla);
	}



	public ODocument factoryModel(ODocument lajura, Date tgla, long pupulasia, int matia,
			int butirpagibagusa, int butirpagiretaka, int butirsorebagusa,
			int butirsoreretaka, double kgpagibagusa, double kgpagiretaka,
			double kgsorebagusa, double kgsoreretaka){
		ODocument o=new ODocument(getClassName());
		o.field(lajur, lajura, OType.LINK);
		o.field(tgl , tgla, OType.DATE);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
		o.field(butirpagibagus, butirpagibagusa, OType.INTEGER);
		o.field(butirpagiretak, butirpagiretaka, OType.INTEGER);
		o.field(butirsorebagus, butirsorebagusa, OType.INTEGER);
		o.field(butirsoreretak, butirsoreretaka, OType.INTEGER);
		o.field(kgpagibagus, kgpagibagusa, OType.DOUBLE);
		o.field(kgpagiretak, kgpagiretaka, OType.DOUBLE);
		o.field(kgsorebagus, kgsorebagusa, OType.DOUBLE);
		o.field(kgsoreretak, kgsoreretaka, OType.DOUBLE);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	public ODocument factoryModel(ODocument lajura, Date tgla, long pupulasia, int matia,
			int butirpagibagusa, int butirpagiretaka, int butirsorebagusa,
			int butirsoreretaka, double kgpagibagusa, double kgpagiretaka,
			double kgsorebagusa, double kgsoreretaka, int x){
		ODocument o=new ODocument(getClassName());
		o.field(lajur, lajura, OType.LINK);
		o.field(tgl , tgla, OType.DATE);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
		o.field(butirpagibagus, butirpagibagusa, OType.INTEGER);
		o.field(butirpagiretak, butirpagiretaka, OType.INTEGER);
		o.field(butirsorebagus, butirsorebagusa, OType.INTEGER);
		o.field(butirsoreretak, butirsoreretaka, OType.INTEGER);
		o.field(kgpagibagus, kgpagibagusa, OType.DOUBLE);
		o.field(kgpagiretak, kgpagiretaka, OType.DOUBLE);
		o.field(kgsorebagus, kgsorebagusa, OType.DOUBLE);
		o.field(kgsoreretak, kgsoreretaka, OType.DOUBLE);
		o.field("x", x, OType.INTEGER);
		
		return o;
	}
	
	public ODocument factoryModelUpdate(ODocument o,ODocument lajura, Date tgla, long pupulasia, int matia,
			int butirpagibagusa, int butirpagiretaka, int butirsorebagusa,
			int butirsoreretaka, double kgpagibagusa, double kgpagiretaka,
			double kgsorebagusa, double kgsoreretaka){
		o.field(lajur, lajura, OType.LINK);
		o.field(tgl , tgla, OType.DATE);
		o.field(pupulasi, pupulasia, OType.LONG);
		o.field(mati, matia, OType.INTEGER);
		o.field(butirpagibagus, butirpagibagusa, OType.INTEGER);
		o.field(butirpagiretak, butirpagiretaka, OType.INTEGER);
		o.field(butirsorebagus, butirsorebagusa, OType.INTEGER);
		o.field(butirsoreretak, butirsoreretaka, OType.INTEGER);
		o.field(kgpagibagus, kgpagibagusa, OType.DOUBLE);
		o.field(kgpagiretak, kgpagiretaka, OType.DOUBLE);
		o.field(kgsorebagus, kgsorebagusa, OType.DOUBLE);
		o.field(kgsoreretak, kgsoreretaka, OType.DOUBLE);
		o.field("x", 0, OType.INTEGER);
		return o;
	}
	
	
	public double getHD(ODocument o){
		long p=o.field(pupulasi);
		double hd=getTotalKgBagus(o)+getTotalKgRetak(o)*p;
		return hd;
	}
	public double getP(ODocument o){
		double t=getTotalKgBagus(o)+getTotalKgRetak(o);
		double t2=getTotalButirBagus(o)+getTotalButirRetak(o);
		if (t2==0) {
			t2=1;
		}
		t=t*1000;
		return 1000/t2;
	}
	public int getTotalButirBagus(ODocument o){
		int bpb=o.field(butirpagibagus);
		int bsb=o.field(butirsorebagus);
		return bpb+bsb;
	}
	public int getTotalButirRetak(ODocument o){
		int bpr=o.field(butirpagiretak);
		int bsr=o.field(butirsoreretak);
		return bpr+bsr;
	}
	public double getTotalKgBagus(ODocument o){
		double bpb=o.field(kgpagibagus);
		double bsb=o.field(kgsorebagus);
		return bpb+bsb;
	}
	public double getTotalKgRetak(ODocument o){
		double bpr=o.field(kgpagiretak);
		double bsr=o.field(kgsoreretak);
		return bpr+bsr;
	}
	
	public LajurdDao() {
		super("Lajurd");
	}
	
	
	
	@Override
	public int delNull(ODatabaseDocumentTx db) {
		int a=App.getKandangallDao().delNull(db);
		int b=App.getKandangdDao().delNull(db);
		int tmp=super.delNull(db);
		return tmp+a+b;
	}

	public ODocument setNull(ODatabaseDocumentTx db,ODocument o){
		ODocument lajura=o.field(lajur);
		ODocument kandangtmp=lajura.field(LajurDao.kandang);
		 Date tgla=o.field(tgl);
		 long pupulasia=o.field(pupulasi);
		 int matia=o.field(mati);
		 int butirpagibagusa=o.field(butirpagibagus);
		 int butirpagiretaka=o.field(butirpagiretak);
		 int butirsorebagusa=o.field(butirsorebagus);
		 int butirsoreretaka=o.field(butirsoreretak);
		
		 double kgpagibagusa=o.field(kgpagibagus);
		 double kgpagiretaka=o.field(kgpagiretak);
		 double kgsorebagusa=o.field(kgsorebagus);
		 double kgsoreretaka=o.field(kgsoreretak);
		
		 //stok
		 //stokd
		 //kandangd
		 //kandangall
		//type 3
		 
		 //stock
		 ODocument stock=DataUser.getProduct();
		 if (stock!=null) {
			  double stoktmp=stock.field(ProductDao.saldo);
			  stoktmp=stoktmp-kgpagibagusa-kgsorebagusa;
			  stock.field(ProductDao.saldo, stoktmp, OType.DOUBLE);
			  stock.save();
			  
			//stokd
			ODocument htmp=App.getProductdDao().factoryModel(stock, new Date(), Productd.TYPE_PRODUKSI_HAPUS, 0, kgpagibagusa+kgsorebagusa, stoktmp, 
					App.getT(db, "Hapus data produksi")+" "+lajura.field(LajurDao.name)+" "+App.dateFormat.format(tgla));
			htmp.save();
			
		}
		 
		 // kandangd
		 ODocument ktmp=App.getKandangdDao().getOne(db, KandangdDao.tgl, tgla, KandangdDao.kandang, kandangtmp.getIdentity());
		if (ktmp!=null) {
			int kmatitmp=ktmp.field(KandangdDao.mati);
			kmatitmp=kmatitmp-matia;
			ktmp.field(KandangdDao.mati, kmatitmp, OType.INTEGER);
			int bagusbutirtmp=ktmp.field(KandangdDao.bagusbutir);
			bagusbutirtmp=bagusbutirtmp-butirpagibagusa-butirsorebagusa;
			ktmp.field(KandangdDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
			double bagyskgtmp=ktmp.field(KandangdDao.baguskg);
			bagyskgtmp=bagyskgtmp-kgpagibagusa-kgsorebagusa;
			ktmp.field(KandangdDao.baguskg, bagyskgtmp, OType.DOUBLE);
			
			int retakbutirtmp=ktmp.field(KandangdDao.retakbutir);
			retakbutirtmp=retakbutirtmp-butirpagiretaka-butirsoreretaka;
			ktmp.field(KandangdDao.retakbutir, retakbutirtmp, OType.INTEGER);
			double retakkgtmp=ktmp.field(KandangdDao.retakkg);
			retakkgtmp=retakkgtmp- kgpagiretaka-kgsoreretaka;
			ktmp.field(KandangdDao.retakkg, retakkgtmp, OType.DOUBLE);
			ktmp.save();
			
			if (kmatitmp==0 && bagusbutirtmp==0 && bagyskgtmp==0 && retakbutirtmp==0 && retakkgtmp==0 ) {
				App.getKandangdDao().setNull(db, ktmp);
			}
		}
		
		//kandangall
		ODocument kalltmp=App.getKandangallDao().getOne(db, KandangallDao.tgl, tgla);
		if (kalltmp!=null) {
			int kmatitmp=kalltmp.field(KandangallDao.mati);
			kmatitmp=kmatitmp-matia;
			kalltmp.field(KandangallDao.mati, kmatitmp, OType.INTEGER);
			int bagusbutirtmp=kalltmp.field(KandangallDao.bagusbutir);
			bagusbutirtmp=bagusbutirtmp-butirpagibagusa-butirsorebagusa;
			kalltmp.field(KandangallDao.bagusbutir, bagusbutirtmp, OType.INTEGER);
			double bagyskgtmp=kalltmp.field(KandangallDao.baguskg);
			bagyskgtmp=bagyskgtmp-kgpagibagusa-kgsorebagusa;
			kalltmp.field(KandangallDao.baguskg, bagyskgtmp, OType.DOUBLE);
			
			int retakbutirtmp=kalltmp.field(KandangallDao.retakbutir);
			retakbutirtmp=retakbutirtmp-butirpagiretaka-butirsoreretaka;
			kalltmp.field(KandangallDao.retakbutir, retakbutirtmp, OType.INTEGER);
			double retakkgtmp=kalltmp.field(KandangallDao.retakkg);
			retakkgtmp=retakkgtmp-kgpagiretaka-kgsoreretaka;
			kalltmp.field(KandangallDao.retakkg, retakkgtmp, OType.DOUBLE);
			kalltmp.save();
			if (kmatitmp==0 && bagusbutirtmp==0 && bagyskgtmp==0 && retakbutirtmp==0 && retakkgtmp==0 ) {
				App.getKandangallDao().setNull(db, kalltmp);
			}
			
			
		}
		
		
		
		
//		 Date tanggalnull=new Date();
//			tanggalnull.setTime(0);
		 o=factoryModelUpdate(o, lajura, tgla, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		o.save();
		super.setNull(db, o);
		return o;
	}
	
	public List<ODocument> getAllForPrint(ODatabaseDocumentTx db, ODocument lajura, int bln, int th){
		if (lajura==null) {
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
		sql.append(lajur);
		sql.append(" = ? and ");
		sql.append(tgl);
		sql.append(" between ? and ? ");
		sql.append(" order by tgl asc ");
		OSQLSynchQuery<ODocument> query = new OSQLSynchQuery<ODocument>(sql.toString());
		List<ODocument> result = db.command(query.setFetchPlan("*:-1")).execute(lajura.getIdentity(), tglA, tglB);
		return result;
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

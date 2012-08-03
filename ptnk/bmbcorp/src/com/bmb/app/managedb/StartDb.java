package com.bmb.app.managedb;


import com.bmb.app.config.DataUser;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.KandangallDao;
import com.bmb.app.dao.KandangdDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.db.Lajur;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class StartDb {

	/**
	 * membuat schema,index dan relasi pada database di jalankan setelah
	 * database di buat, jadi hanya sekali saja
	 */
	public void createSchemaDb() {
		OObjectDatabaseTx db = App.getDb();

		OClass logdb = db.getMetadata().getSchema().getClass("Logdb");
		OClass grp = db.getMetadata().getSchema().getClass("Grp");
		OClass usr = db.getMetadata().getSchema().getClass("Usr");
		OClass lang = db.getMetadata().getSchema().getClass("Lang");
		OClass i18n = db.getMetadata().getSchema().getClass("I18n");
		OClass numberId = db.getMetadata().getSchema().getClass("NumberId");
		OClass product = db.getMetadata().getSchema().getClass("Product");
		OClass productd = db.getMetadata().getSchema().getClass("Productd");
		OClass pelanggan = db.getMetadata().getSchema().getClass("Pelanggan");
		OClass penjualan = db.getMetadata().getSchema().getClass("Penjualan");
		OClass penjualanh = db.getMetadata().getSchema().getClass("Penjualanh");
		OClass penjualanb = db.getMetadata().getSchema().getClass("Penjualanb");
		OClass piutang = db.getMetadata().getSchema().getClass("Piutang");
		OClass piutangd = db.getMetadata().getSchema().getClass("Piutangd");
		OClass kandang = db.getMetadata().getSchema().getClass("Kandang");
		OClass lajur = db.getMetadata().getSchema().getClass("Lajur");
		OClass kandangd = db.getMetadata().getSchema().getClass("Kandangd");
		OClass lajurd = db.getMetadata().getSchema().getClass("Lajurd");
		OClass kandangall = db.getMetadata().getSchema().getClass("Kandangall");
		OClass format = db.getMetadata().getSchema().getClass("Format");
		OClass bos = db.getMetadata().getSchema().getClass("Bos");

		// tabel NumberId
		numberId.createProperty("namaTable", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);

		// OClass usr = db.getMetadata().getSchema().createClass("Grp");
		// usr.createIndex("GrpId", OClass.INDEX_TYPE.UNIQUE, "name");
		grp.createProperty("name", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty("code", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		grp.createProperty("logdb", OType.EMBEDDED, logdb).setNotNull(true);

		// tabel Usr
		usr.createProperty("username", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		usr.createProperty("logdb", OType.EMBEDDED, logdb).setNotNull(true);
		usr.createProperty("grp", OType.LINK, grp).setNotNull(true);

		// table lang
		lang.createProperty("code", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		lang.createProperty("name", OType.STRING).createIndex(
				OClass.INDEX_TYPE.UNIQUE);
		lang.createProperty("i18ns", OType.LINKSET);

		// table I18n
		i18n.createProperty("lang", OType.LINK, lang);
		i18n.createProperty("name", OType.STRING);
		i18n.createIndex("I18nId", OClass.INDEX_TYPE.UNIQUE, "lang", "name");
		
		//table Product
		product.createProperty("code", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		product.createProperty("name", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);

		//table Productd
		productd.createProperty("product", OType.LINK, product);
		
		//table Pelanggan
		pelanggan.createProperty("code", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		pelanggan.createProperty("name", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		//table Penjualan
		penjualan.createProperty("code", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		penjualan.createProperty("pelanggan", OType.LINK, pelanggan);
		penjualan.createProperty("product", OType.LINK, product);

		//table Penjualanh
		penjualanh.createProperty("tgl", OType.DATE).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		
		//table Penjumlahanb
		penjualanb.createProperty("b", OType.INTEGER);
		penjualanb.createProperty("t", OType.INTEGER);
		penjualanb.createIndex("bt", OClass.INDEX_TYPE.UNIQUE, "b", "t");
				
		//table Piutang
		piutang.createProperty("pelanggan", OType.LINK, pelanggan).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		//table Piutangd
		piutangd.createProperty("piutang", OType.LINK, piutang).getClass();
		
		//table Kandang
		kandang.createProperty(KandangDao.code, OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		kandang.createProperty(KandangDao.name, OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		//table Lajur
		lajur.createProperty(LajurDao.code, OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		lajur.createProperty(LajurDao.name, OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		lajur.createProperty(LajurDao.kandang, OType.LINK, kandang);
		
		//table Lajurd
		lajurd.createProperty(LajurdDao.tgl, OType.DATE);
		lajurd.createProperty(LajurdDao.lajur, OType.LINK, lajur);
		lajurd.createIndex("ltl", OClass.INDEX_TYPE.UNIQUE, LajurdDao.tgl, LajurdDao.lajur);
		
		//table Kandangd
		kandangd.createProperty(KandangdDao.tgl, OType.DATE);
		kandangd.createProperty(KandangdDao.kandang, OType.LINK, kandang);
		kandangd.createIndex("ktk", OClass.INDEX_TYPE.UNIQUE, KandangdDao.tgl, KandangdDao.kandang);
		
		//table Kandangall
		kandangall.createProperty(KandangallDao.tgl, OType.DATE).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		//table Penjualan
		format.createProperty("code", OType.STRING).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		//table Bos
		format.createProperty("is", OType.INTEGER).createIndex(OClass.INDEX_TYPE.UNIQUE);
		
		db.getMetadata().getSchema().save();
		
		db.close();
		createTranslateFirst();
	}
	
	public void createTranslateFirst(){
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
		createTranslateFirst("Title Application", "Applikasi Peternakan");
		
		createTranslateIcon("icon app", "/image/jadwal");
		createTranslateIcon("icon kandang", "/image/kandang");
		createTranslateIcon("icon produksi", "/image/produksi");
		createTranslateIcon("icon laporan", "/image/laporan");
		createTranslateIcon("icon customer", "/image/customer");
		createTranslateIcon("icon pegawai", "/image/pegawai");
		createTranslateIcon("icon penjualan", "/image/penjualan");
		createTranslateIcon("icon piutang", "/image/piutang");
		createTranslateIcon("icon pakan", "/image/pakan");
		createTranslateIcon("icon lajur", "/image/table");
		createTranslateIcon("icon total", "/image/total");
		
		createTranslateIcon("icon master", "/image/master");
		createTranslateIcon("icon lap", "/image/lap");
		createTranslateIcon("icon inputan", "/image/inputan");
		createTranslateIcon("icon info", "/image/info");
		createTranslateIcon("icon help", "/image/help");
		createTranslateIcon("icon reg", "/image/reg");
		createTranslateIcon("icon stock", "/image/telor");
		createTranslateIcon("icon format", "/image/format");
		
		createTranslateIcon("icon exit", "/image/exit");
		
		createTranslateIcon("icon hak akses", "/image/hakakses");
		
		createTranslateIcon("icon print", "/image/print");
		createTranslateIcon("icon tambah", "/image/add");
		createTranslateIcon("icon hapus", "/image/hapus");
		createTranslateIcon("icon reload", "/image/refresh");
		createTranslateIcon("icon reset", "/image/reset");
		createTranslateIcon("icon login", "/image/login");
		createTranslateIcon("icon logout", "/image/logout");
		createTranslateIcon("icon save", "/image/save");
		createTranslateIcon("icon edit", "/image/edit");
		createTranslateIcon("icon 2l", "/image/2l");
		createTranslateIcon("icon 1w", "/image/1w");
		createTranslateIcon("icon 3w", "/image/3w");
		createTranslateIcon("icon lihat", "/image/view");
		createTranslateIcon("icon table", "/image/table");
		
//		createTranslateIcon("icon exit", "/image/save");
		
		
		createTranslateIconJpg("icon about", "/image/about");
		
		// create format
		App.getFormatDao().factoryFirst(db);
		App.getGrpDao().factoryModelFirst(db);
		App.getUsrDao().factoryModelFirst(db);
		App.getBosDao().factoryFirst(db);
		
		db.close();
	}
	public void createTranslateFirst(String n, String t){
		ODocument tr=App.getI18nDao().factoryModel(DataUser.getLang().getIdentity(), n, t);
		tr.save();
	}
	public void createTranslateIcon(String n, String t){
		createTranslateFirst(n+" 16", t+"-16.png");
		createTranslateFirst(n+" 24", t+"-24.png");
		createTranslateFirst(n+" 32", t+"-32.png");
		createTranslateFirst(n+" 48", t+"-48.png");
	}
	public void createTranslateIconJpg(String n, String t){
		createTranslateFirst(n+" 16", t+"-16.jpg");
		createTranslateFirst(n+" 24", t+"-24.jpg");
		createTranslateFirst(n+" 32", t+"-32.jpg");
		createTranslateFirst(n+" 48", t+"-48.jpg");
	}
	

//	public void createContentDefaultDb() {
//		ODatabaseDocumentTx db = App.getDbd();
//
//		factoryGrp("Administrator", "ADM", "Hak Akses Tertinggi", 0, new Date());
//		factoryGrp("Pegawai", "PEG", "Hak Akses untuk Pegawai", 0, new Date());
//		factoryGrp("Umum", "UMU", "Hak Akses Umum", 0, new Date());
//
//		db.close();
//	}

//	public void createContentDefaultLang() {
//		ODatabaseDocumentTx db = App.getDbd();
//		factoryLang(db, "en", "English");
//		factoryLang(db, "id", "Indonesia");
//		db.close();
//	}

//	public void factoryLang(ODatabaseDocumentTx db, final String kode,
//			String name) {
//		List<ODocument> result = db.query(new ONativeSynchQuery(db, "Lang",
//				new OQueryContextNativeSchema<ODocument>()) {
//
//			@Override
//			public boolean filter(OQueryContextNative iRecord) {
//				return iRecord.field("kode").eq(kode).go();
//			}
//		});
//		if (result.size() == 0) {
//			System.out.println("buat baru");
//			ODocument doc = new ODocument("Lang");
//			doc.field("kode", kode);
//			doc.field("name", name);
//			doc.save();
//		}
//
//		// doc.geti
//	}

//	public void factoryGrp(String name, String kode, String note, int createby,
//			Date createAt) {
//		ODocument doc = new ODocument("Grp");
//		doc.field("name", name)
//				.field("kode", kode)
//				.field("note", note)
//				.field("logdb",
//						new ODocument("Logdb").field("createdBy", createby)
//								.field("createdAt", createAt, OType.DATE),
//						OType.EMBEDDED);
//
//		doc.save();
//	}
}

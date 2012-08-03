package com.bmb.app.config;


import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.ProductDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Class ini untuk menampung session dari semua setting dari user
 * 
 * @author toyib
 */
public class DataUser {
	
	public static boolean ROOT=true;
	public static boolean HAK_AKSES_VIEW=false;
	public static boolean HAK_AKSES_ADD=false;
	public static boolean HAK_AKSES_EDIT=false;
	public static boolean HAK_AKSES_HAPUS=false;
	
	public static boolean USR_VIEW=false;
	public static boolean USR_EDIT=false;
	public static boolean USR_ADD=false;
	public static boolean USR_DEL=false;
	
	public static boolean PELANGGAN_VIEW=false;
	public static boolean PELANGGAN_EDIT=false;
	public static boolean PELANGGAN_ADD=false;
	public static boolean PELANGGAN_DEL=false;

	public static boolean KANDANG_VIEW=false;
	public static boolean KANDANG_EDIT=false;
	public static boolean KANDANG_ADD=false;
	public static boolean KANDANG_DEL=false;
	
	public static boolean PRODUKSI_VIEW=false;
	public static boolean PENJUALAN_VIEW=false;
	public static boolean PAKAN_VIEW=false;
	
	public static boolean LAJURD_VIEW=false;
	public static boolean LAJURD_PRINT=false;
	
	public static boolean KANDANGD_VIEW=false;
	public static boolean KANDANGD_PRINT=false;
	
	public static boolean KANDANGALL_VIEW=false;
	public static boolean KANDANGALL_PRINT=false;
	
	public static boolean PRODUCTD_VIEW=false;
	public static boolean PRODUCTD_EDIT=false;
	
	public static boolean PIUTANG_VIEW=false;
	public static boolean PEMBAYARAN_EDIT=false;
	
	public static boolean FORMAT_VIEW=false;
	public static boolean FORMAT_EDIT=false;
	
	public static int XROOT=0;
	public static int XHAK_AKSES_VIEW=1;
	public static int XHAK_AKSES_ADD=2;
	public static int XHAK_AKSES_EDIT=3;
	public static int XHAK_AKSES_HAPUS=4;
	
	
	public static int XUSR_VIEW=5;
	public static int XUSR_EDIT=6;
	public static int XUSR_ADD=7;
	public static int XUSR_DEL=8;
	
	public static int XPELANGGAN_VIEW=9;
	public static int XPELANGGAN_EDIT=10;
	public static int XPELANGGAN_ADD=11;
	public static int XPELANGGAN_DEL=12;

	public static int XKANDANG_VIEW=13;
	public static int XKANDANG_EDIT=14;
	public static int XKANDANG_ADD=15;
	public static int XKANDANG_DEL=16;
	
	public static int XPRODUKSI_VIEW=17;
	public static int XPENJUALAN_VIEW=18;
	public static int XPAKAN_VIEW=19;
	
	public static int XLAJURD_VIEW=20;
	public static int XLAJURD_PRINT=21;
	
	public static int XKANDANGD_VIEW=22;
	public static int XKANDANGD_PRINT=23;
	
	public static int XKANDANGALL_VIEW=24;
	public static int XKANDANGALL_PRINT=25;
	
	public static int XPRODUCTD_VIEW=26;
	public static int XPRODUCTD_EDIT=27;
	
	public static int XPIUTANG_VIEW=28;
	public static int XPEMBAYARAN_EDIT=29;
	
	public static int XFORMAT_VIEW=30;
	public static int XFORMAT_EDIT=31;
	
	public static boolean getAkses(int id) {
		if (getGrp() != null && getGrp().field("@class").equals(App.getGrpDao().getClassName())) {
			String kunci = getGrp().field(GrpDao.key);
			if (kunci!=null&&kunci.indexOf("x" + id + "x") != -1) {
				return true;
			}
		}
		return false;

	}
	
	public static void setAkses(){
		
		ROOT=getAkses(XROOT);
		HAK_AKSES_VIEW=getAkses(XHAK_AKSES_VIEW);
		HAK_AKSES_ADD=getAkses(XHAK_AKSES_ADD);
		HAK_AKSES_EDIT=getAkses(XHAK_AKSES_EDIT);
		HAK_AKSES_HAPUS=getAkses(XHAK_AKSES_HAPUS);
		
		USR_VIEW=getAkses(XUSR_VIEW);
		USR_EDIT=getAkses(XUSR_EDIT);
		USR_ADD=getAkses(XUSR_ADD);
		USR_DEL=getAkses(XUSR_DEL);
		
		PELANGGAN_VIEW=getAkses(XPELANGGAN_VIEW);
		PELANGGAN_EDIT=getAkses(XPELANGGAN_EDIT);
		PELANGGAN_ADD=getAkses(XPELANGGAN_ADD);
		PELANGGAN_DEL=getAkses(XPELANGGAN_DEL);

		KANDANG_VIEW=getAkses(XKANDANG_VIEW);
		KANDANG_EDIT=getAkses(XKANDANG_EDIT);
		KANDANG_ADD=getAkses(XKANDANG_ADD);
		KANDANG_DEL=getAkses(XKANDANG_DEL);
		
		PRODUKSI_VIEW=getAkses(XPRODUKSI_VIEW);
		PENJUALAN_VIEW=getAkses(XPENJUALAN_VIEW);
		PAKAN_VIEW=getAkses(XPAKAN_VIEW);
		
		LAJURD_VIEW=getAkses(XLAJURD_VIEW);
		LAJURD_PRINT=getAkses(XLAJURD_PRINT);
		
		KANDANGD_VIEW=getAkses(XKANDANGD_VIEW);
		KANDANGD_PRINT=getAkses(XKANDANGD_PRINT);
		
		KANDANGALL_VIEW=getAkses(XKANDANGALL_VIEW);
		KANDANGALL_PRINT=getAkses(XKANDANGALL_PRINT);
		
		PRODUCTD_VIEW=getAkses(XPRODUCTD_VIEW);
		PRODUCTD_EDIT=getAkses(XPRODUCTD_EDIT);
		
		PIUTANG_VIEW=getAkses(XPIUTANG_VIEW);
		PEMBAYARAN_EDIT=getAkses(XPEMBAYARAN_EDIT);
		
		FORMAT_VIEW=getAkses(XFORMAT_VIEW);
		FORMAT_EDIT=getAkses(XFORMAT_EDIT);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static ODocument grp;

	public static ODocument getGrp() {
//		if (getUsr()!=null && grp == null) {
//			ODatabaseDocumentTx db = App.getDbd();
//		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
//			grp = App.getGrpDao().getOne(db, "@rid", getUsr().field(UsrDao.grp));
//			db.close();
//		}
		return grp;
	}
	
	
	/**
	 * User yang login
	 */
	private static ODocument usr;
	/**
	 * Bahasa yang diguanakan User
	 */
	private static ODocument lang;

	/**
	 * Menggambil data user yang login
	 * 
	 * @return User
	 */
	public static synchronized ODocument getUsr() {
		if (usr == null) {
//			// ini hanya di gunakan saat develop
//			ODatabaseDocumentTx db = App.getDbd();
//			ODatabaseRecordThreadLocal. INSTANCE .set(db);
//			usr=App.getUsrDao().getOne(db, "username", "admin");
//			if (usr==null) {
//				usr=App.getUsrDao().factoryModelFirst();
//				usr.save();
//			}
//			db.close();
		}
		return usr;
	}
	
	public static synchronized ODocument setUsr(ODocument usra) {
		usr=usra;
		return usr;
	}
	public static synchronized ODocument setGrp(ODocument grpa) {
		grp=grpa;
		return grp;
	}

	/**
	 * Mendapatkan bahasa yang sedang di aktifkan
	 * 
	 * @return Lang
	 */
	public static synchronized ODocument getLang() {
		if (lang == null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set( db );
			lang = App.getLangDao().getLangByCode(db, "id");
			if (lang == null) {
				App.getLangDao().createContentDefaultLang();
				lang = App.getLangDao().getLangByCode(db, "id");
			}
			db.close();
		}
		return lang;
	}
	
	private static ODocument product;
	public static synchronized ODocument getProduct() {
		if (product==null) {
			ODatabaseDocumentTx db = App.getDbd();
			ODatabaseRecordThreadLocal.INSTANCE.set( db );
			try {
				ODocument tmp=App.getProductDao().getOne(db, ProductDao.code, "1");
				if (tmp==null) {
					tmp=App.getProductDao().factoryModel("1", "Telur [KG]",	 0, 10000);
					tmp.save();
				}
				product=tmp;
			} catch (Exception e) {
				App.printErr(e);
			}
			db.close();
		}
		return product;
	}
}

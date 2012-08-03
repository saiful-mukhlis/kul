package com.bmb.app.global;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import org.bmb.app.util.NumberRenderer;

import com.bmb.app.config.SchemaDb;
import com.bmb.app.dao.BosDao;
import com.bmb.app.dao.FormatDao;
import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.I18nDao;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.KandangallDao;
import com.bmb.app.dao.KandangdDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.dao.LangDao;
import com.bmb.app.dao.NumberIdDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PenjualanbDao;
import com.bmb.app.dao.PenjualanhDao;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.dao.PiutangdDao;
import com.bmb.app.dao.ProductDao;
import com.bmb.app.dao.ProductdDao;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.db.Grp;
import com.bmb.app.db.Penjualan;
import com.bmb.app.managedb.StartDb;
import com.bmb.app.other.NamaBulan;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * @author toyib Class ini di gunalan untuk variable yang di access secara
 *         global
 */
public class App {
	private App() {
	}
	
	private static Double w;
	private static Double h;

	public static Double getW() {
		if (w == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			w = screenSize.getWidth();
		}
		return w;
	}
	public static Double getH() {
		if (h == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			h = screenSize.getHeight();
		}
		return h;
	}
	
	
	public static DateFormat timeFormat=new SimpleDateFormat("HH:mm");
	public static DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
	public static DecimalFormat paymentFormat=new DecimalFormat("###,###,##0.##");
	public static DecimalFormat paymentFormat2=new DecimalFormat("###,###,##0.00");
	public static NumberRenderer tablePayment=new NumberRenderer(App.paymentFormat2);
	public static Color whiteSmoke = new Color(245, 245, 245);
	public static Color selected = new Color(135, 206, 250);
	public static Color aqua = new Color(0, 255, 255);
	public static Color gainsboro = new Color(220, 220, 220);
	
	public static Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(App.gainsboro, 1),
            BorderFactory.createEmptyBorder(3, 3, 3,3 ));
	public static Border borderSelected = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(App.selected, 1),
            BorderFactory.createEmptyBorder(3, 3, 3,3 ));
	
	public static String [] bln={"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", 
			"September", "Oktober", "November", "Desember" };
	
	public static NamaBulan[] getBulan(){
//		String [] bln={"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", 
//				"September", "Oktober", "November", "Desember" };
		NamaBulan  nb[]= new NamaBulan[12];
		for (int i = 0; i < 12; i++) {
			nb[i]=new NamaBulan(i, bln[i]);
		}
		return nb;
	}

	
	private static FormatDao formatDao;

	public static FormatDao getFormatDao() {
		if (formatDao == null) {
			formatDao = new FormatDao();
		}
		return formatDao;
	}
	
	
	private static KandangallDao kandangallDao;

	public static KandangallDao getKandangallDao() {
		if (kandangallDao == null) {
			kandangallDao = new KandangallDao();
		}
		return kandangallDao;
	}
	
	
	private static KandangdDao kandangdDao;

	public static KandangdDao getKandangdDao() {
		if (kandangdDao == null) {
			kandangdDao = new KandangdDao();
		}
		return kandangdDao;
	}
	
	
	private static PenjualanbDao penjualanbDao;

	public static PenjualanbDao getPenjualanbDao() {
		if (penjualanbDao == null) {
			penjualanbDao = new PenjualanbDao();
		}
		return penjualanbDao;
	}
	
	
	private static LajurDao lajurDao;

	public static LajurDao getLajurDao() {
		if (lajurDao == null) {
			lajurDao = new LajurDao();
		}
		return lajurDao;
	}
	
	private static LajurdDao lajurdDao;

	public static LajurdDao getLajurdDao() {
		if (lajurdDao == null) {
			lajurdDao = new LajurdDao();
		}
		return lajurdDao;
	}
	
	private static KandangDao kandangDao;

	public static KandangDao getKandangDao() {
		if (kandangDao == null) {
			kandangDao = new KandangDao();
		}
		return kandangDao;
	}
	
	private static BosDao bosDao;

	public static BosDao getBosDao() {
		if (bosDao == null) {
			bosDao = new BosDao();
		}
		return bosDao;
	}
	
	private static UsrDao usrDao;

	public static UsrDao getUsrDao() {
		if (usrDao == null) {
			usrDao = new UsrDao();
		}
		return usrDao;
	}
	private static ProductDao productDao;

	public static ProductDao getProductDao() {
		if (productDao == null) {
			productDao = new ProductDao();
		}
		return productDao;
	}
	
	private static ProductdDao productdDao;

	public static ProductdDao getProductdDao() {
		if (productdDao == null) {
			productdDao = new ProductdDao();
		}
		return productdDao;
	}
	
	
	private static PiutangdDao piutangdDao;

	public static PiutangdDao getPiutangdDao() {
		if (piutangdDao == null) {
			piutangdDao = new PiutangdDao();
		}
		return piutangdDao;
	}
	
	
	private static PenjualanDao penjualanDao;

	public static PenjualanDao getPenjualanDao() {
		if (penjualanDao == null) {
			penjualanDao = new PenjualanDao();
		}
		return penjualanDao;
	}
	
	private static PenjualanhDao penjualanhDao;

	public static PenjualanhDao getPenjualanhDao() {
		if (penjualanhDao == null) {
			penjualanhDao = new PenjualanhDao();
		}
		return penjualanhDao;
	}
	
	private static PiutangDao piutangDao;

	public static PiutangDao getPiutangDao() {
		if (piutangDao == null) {
			piutangDao = new PiutangDao();
		}
		return piutangDao;
	}
	
	
	private static PelangganDao pelangganDao;

	public static PelangganDao getPelangganDao() {
		if (pelangganDao == null) {
			pelangganDao = new PelangganDao();
		}
		return pelangganDao;
	}
	
	
	private static GrpDao grpDao;

	public static GrpDao getGrpDao() {
		if (grpDao == null) {
			grpDao = new GrpDao();
		}
		return grpDao;
	}
	private static I18nDao i18nDao;

	public static I18nDao getI18nDao() {
		if (i18nDao == null) {
			i18nDao = new I18nDao();
		}
		return i18nDao;
	}
	private static LangDao langDao;

	public static LangDao getLangDao() {
		if (langDao == null) {
			langDao = new LangDao();
		}
		return langDao;
	}
	
	private static NumberIdDao numberIdDao;

	public static NumberIdDao getNumberIdDao() {
		if (numberIdDao == null) {
			numberIdDao = new NumberIdDao();
		}
		return numberIdDao;
	}
	
	
	public static String getT(ODatabaseDocumentTx db, String name){
		return getI18nDao().getTranslate(db, name);
	}
	
	

					/**
					 * di gunakan untuk one registri pada class db
					 */
	private static boolean firstRegisterClasses = true;

	/**
	 * mendapatkan database dari pool, bila databse belum tersedia maka akan
	 * dibuatkan database baru
	 * 
	 * @return OObjectDatabaseTx
	 */
	public static OObjectDatabaseTx getDb() {
		String url = "local:c://test/db";
		//String url = "local:db/db";
		String user = "admin";
		String pwd = "admin";

		String packDb = "com.bmb.app.db";
		try {
			OObjectDatabaseTx db = OObjectDatabasePool.global().acquire(url,
					user, pwd);

			if (firstRegisterClasses) {
				// try {
				// new SchemaDb().createDbIfNotExist(url);
				// } catch (IOException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				db.getEntityManager().registerEntityClasses(packDb);
				firstRegisterClasses = false;
			}
			return db;
		} catch (OStorageException e) {
			App.printErr(e);
			OObjectDatabaseTx db = new OObjectDatabaseTx(url).create();
			db.getEntityManager().registerEntityClasses(packDb);
			new StartDb().createSchemaDb();
			return db;
		}
	}

	/**
	 * @return ODatabaseDocumentTx
	 */
	public static ODatabaseDocumentTx getDbd() {
		String url = "local:c://test/db";
		//String url = "local:db/db";
		String user = "admin";
		String pwd = "admin";

		String packDb = "com.bmb.app.db";

		try {
			ODatabaseDocumentTx dbd = ODatabaseDocumentPool.global().acquire(
					url, user, pwd);
			return dbd;
		} catch (OStorageException e) {
			e.printStackTrace();
			OObjectDatabaseTx db = new OObjectDatabaseTx(url).create();
			db.getEntityManager().registerEntityClasses(packDb);
			new StartDb().createSchemaDb();
			ODatabaseDocumentTx dbd = ODatabaseDocumentPool.global().acquire(
					url, user, pwd);
			return dbd;
		}

	}
	
	public static Icon getIcon(ODatabaseDocumentTx db, String nameIcon){
			return new ImageIcon(App.class.getResource(App.getT(db, nameIcon)));
	}
	public static ImageIcon getImage(ODatabaseDocumentTx db, String nameIcon){
		return new ImageIcon(App.class.getResource(App.getT(db, nameIcon)));
}


	
	public static void showErrorFieldEmpty(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, getT(db, namaField)+getT(db, " belum di isi"));
	}
	public static void showErrorNotValid(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, getT(db, namaField)+getT(db, " tidak valid"));
	}
	public static void showSaveOk(){
		JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
	}
	public static void showErrorJumlahTidakFalid(){
		JOptionPane.showMessageDialog(null, "Jumlah tidak valid. Jumlah tidak boleh kurang dari 1.");
	}
	public static void showPelangganTidakDapatDiHapus(){
		JOptionPane.showMessageDialog(null, "Pelanggan masih punya Piutang.\nData tidak dapat di Hapus.");
	}
	public static void showErrorPasswordTidakSamadenganKonfirmasi(){
		JOptionPane.showMessageDialog(null, "Password dan Ketik Ulang tidak sesuai");
	}
	public static void showErrorUsernameTidakTerdaftar(){
		JOptionPane.showMessageDialog(null, "Username tidak terdaftar");
	}
	public static void showErrorPasswordSalah(){
		JOptionPane.showMessageDialog(null, "Silahkan ulangi, password yang Anda inputkan tidak sesuai.");
	}
	public static void showErrorSN(){
		JOptionPane.showMessageDialog(null, "Kode Aktifasi yang Anda masukkan tidak sesuai.");
	}
	public static void showErrorDataSudahAda(ODatabaseDocumentTx db, String namaField){
		JOptionPane.showMessageDialog(null, getT(db, namaField)+getT(db, " sudah terdaftar"));
	}
	
	public static void showErrSementara(String x){
		JOptionPane.showMessageDialog(null, x);
	}
	public static void printErr(Exception e) {
		e.printStackTrace();
	}

	public static void info(String note) {
		System.out.println(note);
	}
	
}

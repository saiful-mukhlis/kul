package com.bmb.app.dao;


import com.bmb.app.dao.abst.DaoAbstract;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatDao extends DaoAbstract {

	public static final String code = "code";
	public static final String name = "name";
	public static final String kop1 = "kop1";
	public static final String kop2 = "kop2";
	public static final String kop3 = "kop3";



	public FormatDao() {
		super("Format");
	}

	public ODocument factoryModel(String codea,String namea, String kop1a, String kop2a,
			String kop3a) {
		ODocument o = new ODocument(getClassName());
		o.field(code, codea);
		o.field(name, namea);
		o.field(kop1, kop1a);
		o.field(kop2, kop2a);
		o.field(kop3, kop3a);
		return o;
	}

	public ODocument factoryModelUpdate(ODocument o, String codea,String namea, String kop1a, String kop2a,
			String kop3a) {
		o.field(code, codea);
		o.field(name, namea);
		o.field(kop1, kop1a);
		o.field(kop2, kop2a);
		o.field(kop3, kop3a);
		return o;
	}

	public void factoryFirst(ODatabaseDocumentTx db){

			ODocument o=factoryModel("kop","Kop Laporan", "Petertankan", "UD 'ARFI LESTARI", "Jl. Gajah Mada No 48 RT 05 RW 02 KEL Milir Kec. Delopo Kab. Madiun");
			o.save();
			o=factoryModel("lajur", "Title Laporan Lajur", "BUKU CATATAN HARIAN", "Lajur :", "Bulan :" );
			o.save();
			o=factoryModel("kandang", "Title Laporan Kandang", "BUKU CATATAN HARIAN" , "Kandang :", "Bulan :");
			o.save();
			o=factoryModel("kandangall", "Title Laporan Total Produksi", "BUKU CATATAN HARIAN", "Bulan :" , "");
			o.save();
			o=factoryModel("penjualanh", "Title Laporan Penjualan per Hari", "LAPORAN PENJUALAN", "Tanggal :" , "Total Penjualan :");
			o.save();
			o=factoryModel("penjualanb", "Title Laporan Penjualan per Bulan", "LAPORAN PENJUALAN", "Bulan :" , "Total Penjualan :");
			o.save();
	}

	

}

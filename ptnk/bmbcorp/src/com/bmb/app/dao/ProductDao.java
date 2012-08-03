package com.bmb.app.dao;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductDao extends DaoAbstract {
	public static final String code = "code";
	public static final String name = "name";
	public static final String saldo = "saldo";
	public static final String harga = "harga";

	public static final String fcode = "Kode";
	public static final String fname = "Nama";
	public static final String fsaldo = "Saldo";
	public static final String fharga = "Harga";

	public ProductDao() {
		super("Product");
	}

	public ODocument factoryModel(String codea, String namea, double saldoa,
			double hargaa) {
		ODocument doc = new ODocument(getClassName());
		doc.field(code, codea);
		doc.field(name, namea);
		doc.field(saldo, saldoa, OType.DOUBLE);
		doc.field(harga, hargaa, OType.DOUBLE);
		return doc;
	}

}

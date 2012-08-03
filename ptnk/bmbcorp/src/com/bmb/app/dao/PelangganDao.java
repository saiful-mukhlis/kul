package com.bmb.app.dao;

import java.util.List;

import com.bmb.app.dao.abst.DaoAbstract;
import com.bmb.app.db.Penjualan;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PelangganDao extends DaoAbstract {

	public static final String code = "code";
	public static final String name = "name";
	public static final String pemilik = "pemilik";
	public static final String notelp = "notelp";
	public static final String alamat = "alamat";

	public static final String fcode = "Kode";
	public static final String fname = "Nama Toko";
	public static final String fpemilik = "Nama Pemilik";
	public static final String fnotelp = "No Telp";
	public static final String falamat = "Alamat";

	public PelangganDao() {
		super("Pelanggan");
	}

	public ODocument factoryModel(String code, String name, String pemilik,
			String notelp, String alamat) {
		ODocument o = new ODocument(getClassName());
		o.field(this.code, code);
		o.field(this.name, name);
		o.field(this.pemilik, pemilik);
		o.field(this.notelp, notelp);
		o.field(this.alamat, alamat);
		return o;
	}

	public ODocument factoryModelUpdate(ODocument o, String code, String name,
			String pemilik, String notelp, String alamat) {
		o.field(this.code, code);
		o.field(this.name, name);
		o.field(this.pemilik, pemilik);
		o.field(this.notelp, notelp);
		o.field(this.alamat, alamat);
		return o;
	}


	@Override
	public void afterDelete(ODatabaseDocumentTx db, ODocument o) {
		App.getPiutangDao().delNull(db);
		super.afterDelete(db, o);
	}

	@Override
	public boolean beforeDelete(ODatabaseDocumentTx db, ODocument o) {
		// piutang & penjualan
		// piutang harus nol
		boolean boleh=true;
		List<ODocument> piutangs = App.getPiutangDao().getAllByColumn(db,
				PiutangDao.pelanggan, o.getIdentity());
		for (ODocument oDocument : piutangs) {
			double sisa=oDocument.field(PiutangDao.total);
			if (sisa>0) {
				boleh=false;
				App.showPelangganTidakDapatDiHapus();
			}
			
		}
		if(boleh){
			for (ODocument oDocument : piutangs) {
				App.getPiutangDao().setNull(db, oDocument);
			}
			List<ODocument> penjualans = App.getPenjualanDao().getAllByColumn(db,
					PenjualanDao.pelanggan, o.getIdentity());
			for (ODocument oDocument : penjualans) {
				App.getPenjualanDao().setNull(db, oDocument);
			}
			return super.beforeDelete(db, o);
		}else{
			return false;
		}

		
		
	}

}

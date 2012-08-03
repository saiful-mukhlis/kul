package org.bmb.app.view.form;


import java.util.Date;

import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.ComponetNewAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;
import org.bmb.app.view.listener.HakAksesListener;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.db.Piutangd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class PiutangComponetEdit extends PiutangComponetFormDefault implements
		ComponetEditAdapter, HakAksesListener {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Pembayaran";
		icon = "icon edit 16";
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		setContentComponent(model);
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 13);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(name, pemilik);
		setFocusEnter(pemilik, bayar);
		setFocusEnter(bayar, save);
		setFocusEnter(save, bayar);
	}
	
	protected double bayara=0;
	protected Date tgla;
	
	
	public boolean validate(ODatabaseDocumentTx db){
		try {
			bayara=Double.parseDouble(bayar.getText());
		} catch (Exception e) {
			bayara=0;
			bayar.setText("");
			App.showErrorFieldEmpty(db, "Jumlah");
		}
		tgla=tgl.getDate();
		if (tgla==null) {
			tgl.setDate(new Date());
			App.showErrorFieldEmpty(db, "Tanggal");
			return false;
		}
		return true;
	}
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument piutangtmp=getPiutang();
			// bayar
			// piutang -
			// piutang d
			
			try{
				  db.begin(TXTYPE.OPTIMISTIC);
				  
				  double ksebelumnya=piutangtmp.field(PiutangDao.total);
					ksebelumnya=ksebelumnya-bayara;
					piutangtmp.field(PiutangDao.total, ksebelumnya, OType.DOUBLE);
					piutangtmp.save();
					
				  ODocument piutangdtmp=App.getPiutangdDao().factoryModel(tgla, piutangtmp, Piutangd.TYPE_PEMBAYARAN, 0 , bayara, ksebelumnya, App.getT(db, "Pembayaran"));
					piutangdtmp.save();
				  
					piutangtmp.save();
				  db.commit();
				  for (WidgetAdapter w: widgeds) {
						w.modelWidgetChange(piutangtmp);
						this.model=piutangtmp;
					}
				  App.showSaveOk();
				  resetContentComponent();
				}catch( Exception e ){
				  db.rollback();
				} finally{
					db.close();
				}
			
			
			
			
		}
	
		
	}

	@Override
	public void changeHakAkses() {
		bayar.setEnabled(DataUser.PEMBAYARAN_EDIT);
		tgl.setEnabled(DataUser.PEMBAYARAN_EDIT);
		save.setEnabled(DataUser.PEMBAYARAN_EDIT);
	}


	
}

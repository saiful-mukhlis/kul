package org.bmb.app.view.form;




import javax.swing.SwingConstants;

import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanComponetFormDefault extends ComponetFormAbstract{
	
	protected TextField  code;
	protected TextField  tgl;
	protected TextField  pelanggan;
	protected TextField  pemilik;
	protected TextField  harga;
	protected TextField  jml;
	protected TextField  total1;
	protected TextField  diskon;
	protected TextField  diskonp;
	protected TextField  totaldiskon;
	protected TextField  total;
	protected TextField  bayar;
	protected TextField  d;
	protected TextField  k;
	protected TextField  note;
	
	public void setContentComponent(ODocument model) {
		if (modelIsTrue(model)) {
			code.setText(model.field(PenjualanDao.code)+"");
			tgl.setText(App.dateFormat.format(model.field(PenjualanDao.tgl))+"");
			harga.setText(App.paymentFormat2.format(model.field(PenjualanDao.harga))+"");
			jml.setText(App.paymentFormat2.format(model.field(PenjualanDao.jml))+"");
			total1.setText(App.paymentFormat2.format(model.field(PenjualanDao.total1))+"");
			diskon.setText(App.paymentFormat2.format(model.field(PenjualanDao.diskon))+"");
			diskonp.setText(App.paymentFormat2.format(model.field(PenjualanDao.diskonp))+"");
//			totaldiskon.setText(model.field(PenjualanDao.totaldiskon)+"");
			total.setText(App.paymentFormat2.format(model.field(PenjualanDao.total))+"");
			bayar.setText(App.paymentFormat2.format(model.field(PenjualanDao.bayar))+"");
			d.setText(App.paymentFormat2.format(model.field(PenjualanDao.d))+"");
			k.setText(App.paymentFormat2.format(model.field(PenjualanDao.k))+"");
			note.setText(model.field(PenjualanDao.note)+"");
		}else if (model.field("@class").equals(App.getPelangganDao().getClassName())) {
			pelanggan.setText(model.field(PelangganDao.name)+"");
			pemilik.setText(model.field(PelangganDao.pemilik)+"");
		}
	}
	
	public void resetContentComponent() {
		code.setText("");
		tgl.setText("");
		pelanggan.setText("");
		pemilik.setText("");
		harga.setText("");
		jml.setText("");
		total1.setText("");
		diskon.setText("");
		diskonp.setText("");
		totaldiskon.setText("");
		total.setText("");
		bayar.setText("");
		d.setText("");
		k.setText("");
		note.setText("");
	}

	public void setColorView() {
		code.setBackground(App.whiteSmoke);
		tgl.setBackground(App.whiteSmoke);
		pelanggan.setBackground(App.whiteSmoke);
		pemilik.setBackground(App.whiteSmoke);
		harga.setBackground(App.whiteSmoke);
		jml.setBackground(App.whiteSmoke);
		total1.setBackground(App.whiteSmoke);
		diskon.setBackground(App.whiteSmoke);
		diskonp.setBackground(App.whiteSmoke);
		totaldiskon.setBackground(App.whiteSmoke);
		total.setBackground(App.whiteSmoke);
		bayar.setBackground(App.whiteSmoke);
		d.setBackground(App.whiteSmoke);
		k.setBackground(App.whiteSmoke);
		note.setBackground(App.whiteSmoke);
	}

	public void setEditable(boolean isEdit) {
		code.setEditable(isEdit);
		tgl.setEditable(isEdit);
		pelanggan.setEditable(isEdit);
		pemilik.setEditable(isEdit);
		harga.setEditable(isEdit);
		jml.setEditable(isEdit);
		total1.setEditable(isEdit);
		diskon.setEditable(isEdit);
		diskonp.setEditable(isEdit);
		totaldiskon.setEditable(isEdit);
		total.setEditable(isEdit);
		bayar.setEditable(isEdit);
		d.setEditable(isEdit);
		k.setEditable(isEdit);
		note.setEditable(isEdit);
	}
	
	public void initComponent(ODatabaseDocumentTx db) {
		code= new TextField();
		tgl= new TextField();
		pelanggan= new TextField();
		pemilik= new TextField();
		harga= new TextField();
		jml= new TextField();
		total1= new TextField();
		diskon= new TextField();
		diskonp= new TextField();
		totaldiskon= new TextField();
		total= new TextField();
		bayar= new TextField();
		d= new TextField();
		k= new TextField();
		note= new TextField();
		
		
		harga.setHorizontalAlignment(SwingConstants.RIGHT);
		jml.setHorizontalAlignment(SwingConstants.RIGHT);
		total1.setHorizontalAlignment(SwingConstants.RIGHT);
		diskon.setHorizontalAlignment(SwingConstants.RIGHT);
		diskonp.setHorizontalAlignment(SwingConstants.RIGHT);
		totaldiskon.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		bayar.setHorizontalAlignment(SwingConstants.RIGHT);
		d.setHorizontalAlignment(SwingConstants.RIGHT);
		k.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.03;
		dao=App.getPenjualanDao();
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		Double tmp = App.getW() * lebar;// 0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("
						+ tmp.intValue()
						+ "px;p):g(.4),  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,    p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,   p,3dlu,    p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		int i = -1;


		
		builder.append(db, PenjualanDao.fcode, code, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.ftgl, tgl, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fpelanggan, pelanggan, 1, (i = i + 2), 5);
		builder.append(db, PelangganDao.fpemilik, pemilik, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fharga, harga, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fjml, jml, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.ftotal1, total1, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fdiskon, diskon, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fdiskonp, diskonp, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.ftotal, total, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fbayar, bayar, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fd, d, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fk, k, 1, (i = i + 2), 5);
		builder.append(db, PenjualanDao.fnote, note, 1, (i = i + 2), 5);

	}


	
	
	
	
	//untuk new
}

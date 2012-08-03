package org.bmb.app.view.form;



import java.util.Date;

import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.DatePicker;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PiutangDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PiutangComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField bayar;
	protected TextField name;
	protected TextField pemilik;
	protected DatePicker tgl;
	protected TextField total;
	
	protected ODocument piutang;
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.36;
		dao=App.getPelangganDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		bayar.setText("");
		name.setText("");
		pemilik.setText("");
		total.setText("");
		tgl.setDate(new Date());
	}
	
	
	
	public void setColorView(){
		bayar.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		pemilik.setBackground(App.whiteSmoke);
		total.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		bayar.setEditable(false);
		name.setEditable(false);
		pemilik.setEditable(false);
		total.setEditable(false);
	}
	
	public void setContentComponent(ODocument model){
		 if (model==null){
			 resetContentComponent();
		 }else if(modelIsTrue(model)){
				name.setText(model.field(PelangganDao.name)+"");
				pemilik.setText(model.field(PelangganDao.pemilik)+"");
				tgl.setDate(new Date());
		 }else if(model.field("@class").equals(App.getPiutangDao().getClassName())){
			 piutang=model;
			 total.setText(App.paymentFormat2.format(model.field(PiutangDao.total))+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		bayar=new TextField();
		name=new TextField();
		pemilik=new TextField();
		total=new TextField();
		tgl=new DatePicker();
		
		name.setEditable(false);
		pemilik.setEditable(false);
		total.setEditable(false);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  8px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu, p,3dlu,  p,3dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(db, PelangganDao.fname, name, 1, 1, 5);
		builder.append(db, PelangganDao.fpemilik, pemilik, 1, 3, 5);
		builder.append(db, "Total", total, 1, 5, 5);
		
		builder.addSeparator(db, "Pembayaran", 1, 7, 7);
		
		builder.append(db, "Tanggal", tgl, 1, 9, 5);
		builder.append(db, "Jumlah", bayar, 1, 11, 5);
		
	}

	public ODocument getPiutang() {
		return piutang;
	}

	public void setPiutang(ODocument piutang) {
		this.piutang = piutang;
	}


	
	
	
	
	//untuk new
}

package com.bmb.app.print.model;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.global.App;
import com.bmb.app.print.adapter.ModelAdapter;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanPrintModel implements ModelAdapter {
private String no;
private String tgl;
private String nota;
private String pembeli;
private String jumlah;
private String harga;
private String total;
private String bayar;
private String hutang;
private String ket;


private ODocument	 o;

public String getNo() {
	return no;
}

public void setNo(String no) {
	this.no = no;
}

public String getTgl() {
	return tgl;
}

public void setTgl(String tgl) {
	this.tgl = tgl;
}


public ODocument getO() {
	return o;
}

public void setO(ODocument o) {
	if (o!=null) {
		if (o.field("@class").equals(App.getPenjualanDao().getClassName())) {
			this.o = o;

			tgl=App.dateFormat.format(o.field(PenjualanDao.tgl));
			
			nota=o.field(PenjualanDao.code);
			jumlah=App.paymentFormat2.format(o.field(PenjualanDao.jml));
			harga=App.paymentFormat2.format(o.field(PenjualanDao.harga));
			total=App.paymentFormat2.format(o.field(PenjualanDao.total));
			bayar=App.paymentFormat2.format(o.field(PenjualanDao.d));
			hutang=App.paymentFormat2.format(o.field(PenjualanDao.k));
			ket=o.field(PenjualanDao.note);
		}else if (o.field("@class").equals(App.getPelangganDao().getClassName())) {

			pembeli=o.field(PelangganDao.name);
		}
	}
	
}

public String getNota() {
	return nota;
}

public void setNota(String nota) {
	this.nota = nota;
}

public String getPembeli() {
	return pembeli;
}

public void setPembeli(String pembeli) {
	this.pembeli = pembeli;
}

public String getJumlah() {
	return jumlah;
}

public void setJumlah(String jumlah) {
	this.jumlah = jumlah;
}

public String getHarga() {
	return harga;
}

public void setHarga(String harga) {
	this.harga = harga;
}

public String getTotal() {
	return total;
}

public void setTotal(String total) {
	this.total = total;
}

public String getBayar() {
	return bayar;
}

public void setBayar(String bayar) {
	this.bayar = bayar;
}

public String getHutang() {
	return hutang;
}

public void setHutang(String hutang) {
	this.hutang = hutang;
}

public String getKet() {
	return ket;
}

public void setKet(String ket) {
	this.ket = ket;
}







}

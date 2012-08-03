package com.bmb.app.print.model;

import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.bmb.app.print.adapter.ModelAdapter;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangdPrintModel implements ModelAdapter {
private String no;
private String tgl;
private String umur;
private String populasi;
private String mati;
private String pakan;
private String bagusButir;
private String bagusKg;
private String retakButir;
private String retakKg;
private String totalButir;
private String totalKg;
private String hd;
private String btr;
private String fcr;
private String ket1;
private String abn;
private String ket2;

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

public String getMati() {
	return mati;
}

public void setMati(String mati) {
	this.mati = mati;
}

public ODocument getO() {
	return o;
}

public void setO(ODocument o) {
	this.o = o;

	tgl=App.dateFormat.format(o.field(KandangdDao.tgl));
	umur=o.field(KandangdDao.umur)+"";
	populasi=o.field(KandangdDao.pupulasi)+"";
	mati=o.field(KandangdDao.mati)+"";
	Double tmpd=o.field(KandangdDao.pakan);
	if (tmpd==null) {
		pakan="0";
	}else{
		pakan=App.paymentFormat2.format(tmpd)+"";
	}
	
	bagusButir=o.field(KandangdDao.bagusbutir)+"";
	bagusKg=App.paymentFormat2.format(o.field(KandangdDao.baguskg));
	retakButir=o.field(KandangdDao.umur)+"";
	retakKg=App.paymentFormat2.format(o.field(KandangdDao.retakkg));
	totalButir=App.getKandangdDao().getTotalTelurButir(o)+"";
	totalKg=App.paymentFormat2.format(App.getKandangdDao().getTotalTelurKg(o));
	hd=App.paymentFormat2.format(App.getKandangdDao().getHD(o));
	btr=App.paymentFormat2.format(App.getKandangdDao().getA(o));
	fcr=App.paymentFormat2.format(App.getKandangdDao().getFcr(o));
	ket1=App.getKandangdDao().getKet1(o);
	abn=App.paymentFormat2.format(App.getKandangdDao().getTotalTelurButir(o));
	ket2=App.getKandangdDao().getKet2(o);
	
}

public String getPopulasi() {
	return populasi;
}

public void setPopulasi(String populasi) {
	this.populasi = populasi;
}

public String getUmur() {
	return umur;
}

public void setUmur(String umur) {
	this.umur = umur;
}

public String getPakan() {
	return pakan;
}

public void setPakan(String pakan) {
	this.pakan = pakan;
}

public String getBagusButir() {
	return bagusButir;
}

public void setBagusButir(String bagusButir) {
	this.bagusButir = bagusButir;
}

public String getBagusKg() {
	return bagusKg;
}

public void setBagusKg(String bagusKg) {
	this.bagusKg = bagusKg;
}

public String getRetakButir() {
	return retakButir;
}

public void setRetakButir(String retakButir) {
	this.retakButir = retakButir;
}

public String getRetakKg() {
	return retakKg;
}

public void setRetakKg(String retakKg) {
	this.retakKg = retakKg;
}

public String getTotalButir() {
	return totalButir;
}

public void setTotalButir(String totalButir) {
	this.totalButir = totalButir;
}

public String getTotalKg() {
	return totalKg;
}

public void setTotalKg(String totalKg) {
	this.totalKg = totalKg;
}

public String getHd() {
	return hd;
}

public void setHd(String hd) {
	this.hd = hd;
}

public String getBtr() {
	return btr;
}

public void setBtr(String btr) {
	this.btr = btr;
}

public String getFcr() {
	return fcr;
}

public void setFcr(String fcr) {
	this.fcr = fcr;
}

public String getKet1() {
	return ket1;
}

public void setKet1(String ket1) {
	this.ket1 = ket1;
}

public String getAbn() {
	return abn;
}

public void setAbn(String abn) {
	this.abn = abn;
}

public String getKet2() {
	return ket2;
}

public void setKet2(String ket2) {
	this.ket2 = ket2;
}



}

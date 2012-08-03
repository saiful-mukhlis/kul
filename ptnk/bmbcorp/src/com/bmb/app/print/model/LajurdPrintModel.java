package com.bmb.app.print.model;

import com.bmb.app.dao.LajurdDao;
import com.bmb.app.global.App;
import com.bmb.app.print.adapter.ModelAdapter;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdPrintModel implements ModelAdapter {
private String no;
private String tgl;
private String populasi;
private String mati;
private String butirPagiBagus;
private String butirPagiRetak;
private String butirSoreBagus;
private String butirSoreRetak;
private String totalButirBagus;
private String totalButirReteak;
private String kgPagiBagus;
private String kgPagiRetak;
private String kgSoreBagus;
private String kgSoreRetak;
private String totalKgBagus;
private String totalKgReteak;
private String hd;
private String btr;

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

public String getButirPagiBagus() {
	return butirPagiBagus;
}

public void setButirPagiBagus(String butirPagiBagus) {
	this.butirPagiBagus = butirPagiBagus;
}

public String getButirPagiRetak() {
	return butirPagiRetak;
}

public void setButirPagiRetak(String butirPagiRetak) {
	this.butirPagiRetak = butirPagiRetak;
}

public String getButirSoreBagus() {
	return butirSoreBagus;
}

public void setButirSoreBagus(String butirSoreBagus) {
	this.butirSoreBagus = butirSoreBagus;
}

public String getButirSoreRetak() {
	return butirSoreRetak;
}

public void setButirSoreRetak(String butirSoreRetak) {
	this.butirSoreRetak = butirSoreRetak;
}

public String getTotalButirBagus() {
	return totalButirBagus;
}

public void setTotalButirBagus(String totalButirBagus) {
	this.totalButirBagus = totalButirBagus;
}

public String getTotalButirReteak() {
	return totalButirReteak;
}

public void setTotalButirReteak(String totalButirReteak) {
	this.totalButirReteak = totalButirReteak;
}

public String getKgPagiBagus() {
	return kgPagiBagus;
}

public void setKgPagiBagus(String kgPagiBagus) {
	this.kgPagiBagus = kgPagiBagus;
}

public String getKgPagiRetak() {
	return kgPagiRetak;
}

public void setKgPagiRetak(String kgPagiRetak) {
	this.kgPagiRetak = kgPagiRetak;
}

public String getKgSoreBagus() {
	return kgSoreBagus;
}

public void setKgSoreBagus(String kgSoreBagus) {
	this.kgSoreBagus = kgSoreBagus;
}

public String getKgSoreRetak() {
	return kgSoreRetak;
}

public void setKgSoreRetak(String kgSoreRetak) {
	this.kgSoreRetak = kgSoreRetak;
}

public String getTotalKgBagus() {
	return totalKgBagus;
}

public void setTotalKgBagus(String totalKgBagus) {
	this.totalKgBagus = totalKgBagus;
}

public String getTotalKgReteak() {
	return totalKgReteak;
}

public void setTotalKgReteak(String totalKgReteak) {
	this.totalKgReteak = totalKgReteak;
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

public ODocument getO() {
	return o;
}

public void setO(ODocument o) {
	this.o = o;

	tgl=App.dateFormat.format(o.field(LajurdDao.tgl));
	populasi=o.field(LajurdDao.pupulasi)+"";
	mati=o.field(LajurdDao.mati)+"";
	butirPagiBagus=o.field(LajurdDao.butirpagibagus)+"";
	butirPagiRetak=o.field(LajurdDao.butirpagiretak)+"";
	butirSoreBagus=o.field(LajurdDao.butirsorebagus)+"";
	butirSoreRetak=o.field(LajurdDao.butirsoreretak)+"";
	totalButirBagus=App.getLajurdDao().getTotalButirBagus(o)+"";
	totalButirReteak=App.getLajurdDao().getTotalButirRetak(o)+"";
	
	kgPagiBagus=App.paymentFormat2.format(o.field(LajurdDao.kgpagibagus))+"";
	kgPagiRetak=App.paymentFormat2.format(o.field(LajurdDao.kgpagiretak))+"";
	kgSoreBagus=App.paymentFormat2.format(o.field(LajurdDao.kgsorebagus))+"";
	kgSoreRetak=App.paymentFormat2.format(o.field(LajurdDao.kgsoreretak))+"";
	totalKgBagus=App.getLajurdDao().getTotalKgBagus(o)+"";
	totalKgReteak=App.getLajurdDao().getTotalKgRetak(o)+"";
	hd=App.getLajurdDao().getHD(o)+"";
	btr=App.getLajurdDao().getP(o)+"";
}

public String getPopulasi() {
	return populasi;
}

public void setPopulasi(String populasi) {
	this.populasi = populasi;
}



}

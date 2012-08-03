package com.bmb.app.db;

import java.util.Date;

public class Piutangd {
	public static final int TYPE_PENJUALAN=1;
	public static final int TYPE_PEMBAYARAN=0;
	private Date	tgl;
	
private Piutang piutang;
private int type;
private double d;
private double k;
private double sisa;
private String ref;
public Piutang getPiutang() {
	return piutang;
}
public void setPiutang(Piutang piutang) {
	this.piutang = piutang;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public double getD() {
	return d;
}
public void setD(double d) {
	this.d = d;
}
public double getK() {
	return k;
}
public void setK(double k) {
	this.k = k;
}
public String getRef() {
	return ref;
}
public void setRef(String ref) {
	this.ref = ref;
}
public double getSisa() {
	return sisa;
}
public void setSisa(double sisa) {
	this.sisa = sisa;
}
public Date getTgl() {
	return tgl;
}
public void setTgl(Date tgl) {
	this.tgl = tgl;
}

}

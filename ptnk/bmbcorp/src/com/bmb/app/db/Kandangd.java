package com.bmb.app.db;

import java.util.Date;

public class Kandangd {
	private Kandang kandang;
	private Date tgl;
	private int umur;
	private long pupulasi;
	private int mati;
	private double pakan;
	
	private int bagusbutir;
	private double baguskg;
	
	private int retakbutir;
	private double retakkg;
	public Kandang getKandang() {
		return kandang;
	}
	public void setKandang(Kandang kandang) {
		this.kandang = kandang;
	}
	public Date getTgl() {
		return tgl;
	}
	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}
	public int getUmur() {
		return umur;
	}
	public void setUmur(int umur) {
		this.umur = umur;
	}
	public long getPupulasi() {
		return pupulasi;
	}
	public void setPupulasi(long pupulasi) {
		this.pupulasi = pupulasi;
	}
	public int getMati() {
		return mati;
	}
	public void setMati(int mati) {
		this.mati = mati;
	}
	public double getPakan() {
		return pakan;
	}
	public void setPakan(double pakan) {
		this.pakan = pakan;
	}
	public int getBagusbutir() {
		return bagusbutir;
	}
	public void setBagusbutir(int bagusbutir) {
		this.bagusbutir = bagusbutir;
	}
	public double getBaguskg() {
		return baguskg;
	}
	public void setBaguskg(double baguskg) {
		this.baguskg = baguskg;
	}
	public int getRetakbutir() {
		return retakbutir;
	}
	public void setRetakbutir(int retakbutir) {
		this.retakbutir = retakbutir;
	}
	public double getRetakkg() {
		return retakkg;
	}
	public void setRetakkg(double retakkg) {
		this.retakkg = retakkg;
	}

}

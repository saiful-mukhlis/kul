package com.bmb.app.db;

import java.util.Date;

public class Penjualan {
	private String code;
	private Date tgl;
	private Pelanggan pelanggan;
	private Product product;
	private double harga;
	private double jml;
	private double total1;
	private double diskon;
	private double diskonp;
	private double totaldiskon;
	private double total;
	private double bayar;
	private double d;
	private double k;
	private String note;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getTgl() {
		return tgl;
	}
	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}
	public Pelanggan getPelanggan() {
		return pelanggan;
	}
	public void setPelanggan(Pelanggan pelanggan) {
		this.pelanggan = pelanggan;
	}
	public double getHarga() {
		return harga;
	}
	public void setHarga(double harga) {
		this.harga = harga;
	}
	public double getJml() {
		return jml;
	}
	public void setJml(double jml) {
		this.jml = jml;
	}
	public double getTotal1() {
		return total1;
	}
	public void setTotal1(double total1) {
		this.total1 = total1;
	}
	public double getDiskon() {
		return diskon;
	}
	public void setDiskon(double diskon) {
		this.diskon = diskon;
	}
	public double getDiskonp() {
		return diskonp;
	}
	public void setDiskonp(double diskonp) {
		this.diskonp = diskonp;
	}
	public double getTotaldiskon() {
		return totaldiskon;
	}
	public void setTotaldiskon(double totaldiskon) {
		this.totaldiskon = totaldiskon;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getBayar() {
		return bayar;
	}
	public void setBayar(double bayar) {
		this.bayar = bayar;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}

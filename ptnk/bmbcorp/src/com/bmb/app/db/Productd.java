package com.bmb.app.db;

import java.util.Date;

public class Productd {
	public static final int TYPE_PENJUALAN=0;
	public static final int TYPE_PRODUKSI_PLUS=1;
	public static final int TYPE_PRODUKSI_EDIT=2;
	public static final int TYPE_PRODUKSI_HAPUS=3;
	public static final int TYPE_EDIT=4;
	private Product product;
	private Date tgl;
	private int type;
	private double d;
	private double k;
	private double sisa;
	private String ref;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getTgl() {
		return tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
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

}

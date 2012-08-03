package com.bmb.app.db;

import java.math.BigDecimal;

public class Piutang {
private Pelanggan pelanggan;
private BigDecimal	total;
public Pelanggan getPelanggan() {
	return pelanggan;
}
public void setPelanggan(Pelanggan pelanggan) {
	this.pelanggan = pelanggan;
}
public BigDecimal getTotal() {
	return total;
}
public void setTotal(BigDecimal total) {
	this.total = total;
}

}

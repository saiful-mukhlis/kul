package com.bmb.app.other;


public class NamaBulan {
private int intBulan;
private String namaBulan;
public NamaBulan(int intBulan, String namaBulan) {
	super();
	this.intBulan = intBulan;
	this.namaBulan = namaBulan;
}
public int getIntBulan() {
	return intBulan;
}
public void setIntBulan(int intBulan) {
	this.intBulan = intBulan;
}
public String getNamaBulan() {
	return namaBulan;
}
public void setNamaBulan(String namaBulan) {
	this.namaBulan = namaBulan;
}
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return namaBulan;
	}
}

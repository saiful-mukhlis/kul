package com.bmb.app.db;

public class Grp {
private String code;
private String name;
private String note;
private Logdb logdb;
private String key;

public Grp() {}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Logdb getLogdb() {
	return logdb;
}
public void setLogdb(Logdb logdb) {
	this.logdb = logdb;
}

public String getKey() {
	return key;
}

public void setKey(String key) {
	this.key = key;
}




}

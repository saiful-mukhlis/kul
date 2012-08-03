package com.bmb.app.previllage;
import java.util.Set;
import java.util.TreeSet;

import com.bmb.app.db.Grp;
public class HakAkses {

private String name;
private String code;
private boolean aktif;
private int key;
private HakAkses parent;
private Set<HakAkses> childs= new TreeSet();



public HakAkses(String nama, int key) {
	super();
	this.name = nama;
	this.key = key;
	this.code="x"+key+"x";
	this.aktif=true;
	this.parent=null;
}


public HakAkses(String nama, int key, Grp group) {
	super();
	this.name = nama;
	this.key = key;
	this.code="x"+key+"x";
	if (group!=null) {
		String data=group.getKey();
		if (data!=null) {
			if (data.indexOf(code)==-1) {
				this.aktif=false;
			}else{
				this.aktif=true;
			}
		}
	}else{
	}
	
	this.parent=null;
}


public void add(HakAkses child){
	child.setParent(this);
	childs.add(child);
}


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


public boolean isAktif() {
	return aktif;
}


public void setAktif(boolean aktif) {
	this.aktif = aktif;
}


public int getKey() {
	return key;
}


public void setKey(int key) {
	this.key = key;
}


public HakAkses getParent() {
	return parent;
}


public void setParent(HakAkses parent) {
	this.parent = parent;
}









}

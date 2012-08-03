package org.bmb.app.view.tree.model;


import java.util.ArrayList;
import java.util.List;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.db.Grp;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class HakAkses {
private String nama;
private String kode;
private boolean aktif;
private int key;
private HakAkses parent;
private List<HakAkses> childs=new ArrayList<HakAkses>();
private ODocument group;

public HakAkses(String nama, int key) {
	super();
	this.nama = nama;
	this.key = key;
	this.kode="x"+key+"x";
	this.aktif=true;
	this.parent=null;
	
}


public HakAkses(String nama, int key, ODocument groupa) {
	super();
	group=groupa;
	this.nama = nama;
	this.key = key;
	this.kode="x"+key+"x";
	if (group!=null) {
		String data=group.field(GrpDao.key);
		if (data!=null) {
			if (data.indexOf(kode)==-1) {
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
public String getNama() {
	return nama;
}
public void setNama(String nama) {
	this.nama = nama;
}
public String getKode() {
	return kode;
}
public void setKode(String kode) {
	this.kode = kode;
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
public List<HakAkses> getChilds() {
	return childs;
}
public void setChilds(List<HakAkses> childs) {
	this.childs = childs;
}


public ODocument getGroup() {
	return group;
}


public void setGroup(ODocument group) {
	this.group = group;
	if (group!=null) {
		String data=group.field(GrpDao.key);
		if (data!=null) {
			if (data.indexOf(kode)==-1) {
				this.aktif=false;
			}else{
				this.aktif=true;
			}
		}else{
			this.aktif=false;
		}
	}else{
		this.aktif=false;
	}
}

}

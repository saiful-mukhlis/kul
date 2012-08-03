package com.bmb.app.db;

public class Usr {
	private String username;
	private String password;
	private String nikName;
	private byte status;
	private Grp grp;
	private Logdb logdb;
	
//	public static final int AKTIF=1;
//	public static final int NON_AKTIF=0;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNikName() {
		return nikName;
	}
	public void setNikName(String nikName) {
		this.nikName = nikName;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public Grp getGrp() {
		return grp;
	}
	public void setGrp(Grp grp) {
		this.grp = grp;
	}
	public Logdb getLogdb() {
		return logdb;
	}
	public void setLogdb(Logdb logdb) {
		this.logdb = logdb;
	}

	
	
	
	
	
	
	
	
	
}

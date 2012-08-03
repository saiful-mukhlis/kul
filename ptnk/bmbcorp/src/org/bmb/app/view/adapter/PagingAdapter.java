package org.bmb.app.view.adapter;

import javax.swing.JPanel;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public interface PagingAdapter {
public void loadFirst(ODatabaseDocumentTx db);
public void setCurentHalaman(int curentHalaman);
public int getCurentHalaman();
public void setJumlahPerHalaman(int jumlahPerHalaman) ;
public int getJumlahPerHalaman();
public void setJumlahHalaman(int jumlahHalaman);
public int getJumlahHalaman();
public void setJumlahData(long jumlahData);
public Long getJumlahData();
public JPanel getPanelPaging();
}

package com.bmb.app.view.master;

import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.PelangganComponetEdit;
import org.bmb.app.view.form.PelangganComponetNew;
import org.bmb.app.view.form.PelangganComponetView;

import com.bmb.app.config.DataUser;
import com.bmb.app.view.table.PelangganTable;

public class PelangganMaster extends MasterAbstract {

	public PelangganMaster() {
		super();
		lebar=0.35;
		title="   Master Data Pelanggan";
		icon="icon customer 16";
		viewForm=new ComponentView(new PelangganComponetView());
		table = new PelangganTable();
	}


	@Override
	public void setEditForm() {
		setEditForm(new ComponentEdit(new PelangganComponetEdit()));
	}

	@Override
	public void setForm() {
		setForm(new ComponentNew(new PelangganComponetNew()));
	}
	
	public void changeHakAkses() {
		tambah.setEnabled(getAdd());
		edit.setEnabled(getEdit());
		hapus.setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.PELANGGAN_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.PELANGGAN_DEL;
	}

	public boolean getLihat() {
		return DataUser.PELANGGAN_VIEW;
	}

	public boolean getEdit() {
		return DataUser.PELANGGAN_EDIT;
	}

}

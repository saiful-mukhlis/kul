package com.bmb.app.view.master;




import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.ComponentView;
import org.bmb.app.view.form.UsrComponetEdit;
import org.bmb.app.view.form.UsrComponetNew;
import org.bmb.app.view.form.UsrComponetView;

import com.bmb.app.config.DataUser;
import com.bmb.app.view.table.UsrTable;

public class UsrMaster extends MasterAbstract {

	
	
	public UsrMaster() {
		super();
		lebar=0.35;
		title="   Master Data Pegawai";
		icon="icon pegawai 16";
		viewForm=new ComponentView(new UsrComponetView());
		table = new UsrTable();
		
	}

	@Override
	public void setEditForm() {
		setEditForm(new ComponentEdit(new UsrComponetEdit()));
	}

	@Override
	public void setForm() {
		setForm(new ComponentNew(new UsrComponetNew()));
	}

	public void changeHakAkses() {
		tambah.setEnabled(getAdd());
		edit.setEnabled(getEdit());
		hapus.setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.USR_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.USR_DEL;
	}

	public boolean getLihat() {
		return DataUser.USR_VIEW;
	}

	public boolean getEdit() {
		return DataUser.USR_EDIT;
	}
	

}

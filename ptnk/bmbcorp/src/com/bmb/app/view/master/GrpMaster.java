package com.bmb.app.view.master;


import org.bmb.app.view.abst.master.MasterAbstract;
import org.bmb.app.view.form.ComponentEdit;
import org.bmb.app.view.form.ComponentNew;
import org.bmb.app.view.form.GrpComponetEdit;
import org.bmb.app.view.form.GrpComponetNew;
import org.bmb.app.view.tree.TreeHakAkses;

import com.bmb.app.config.DataUser;
import com.bmb.app.view.table.GrpTable;

public class GrpMaster extends MasterAbstract {

	public GrpMaster() {
		super();
		lebar=0.35;
		title="   Data Group Hak Akses";
		icon="icon hak akses 16";
		viewForm=new TreeHakAkses();
		table = new GrpTable();
	}
	
	public void setEditForm() {
		setEditForm(new ComponentEdit(new GrpComponetEdit()));
	}

	public void setForm() {
		setForm(new ComponentNew(new GrpComponetNew()));
		
	}
	
	public void changeHakAkses() {
		tambah.setEnabled(getAdd());
		edit.setEnabled(getEdit());
		hapus.setEnabled(getHapus());
	}

	public boolean getAdd() {
		return DataUser.HAK_AKSES_ADD;
	}
	
	public boolean getHapus() {
		return DataUser.HAK_AKSES_HAPUS;
	}

	public boolean getLihat() {
		return DataUser.HAK_AKSES_VIEW;
	}

	public boolean getEdit() {
		return DataUser.HAK_AKSES_EDIT;
	}

}

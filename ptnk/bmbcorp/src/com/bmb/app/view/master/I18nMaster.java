package com.bmb.app.view.master;


import org.bmb.app.view.abst.master.MasterAbstract;
import com.bmb.app.view.table.I18nTable;

public class I18nMaster extends MasterAbstract {

	

	public I18nMaster() {
		super();
		lebar=0.4;
		title="   Data Lajur";
		icon="icon kandang 16";
//		viewForm=new LajurViewForm();
		table = new I18nTable();
	}

	@Override
	public void setEditForm() {
//		setEditForm(new UsrEditForm());
	}

	@Override
	public void setForm() {
//		setForm(new UsrForm());
	}

}

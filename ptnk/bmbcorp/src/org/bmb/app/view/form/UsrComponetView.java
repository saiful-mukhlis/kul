package org.bmb.app.view.form;


import org.bmb.app.view.adapter.ComponetViewAdapter;
import org.bmb.app.view.component.PasswordField;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrComponetView extends UsrComponetFormDefault implements
		ComponetViewAdapter {
	protected TextField status;
	protected TextField grp;
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Lihat Data Pegawai";
		icon = "icon lihat 16";
		initComponent(db);
		setColorView();
		setEditable(false);
		buildLabel(db);
		buildForm(db);
		buildPanel();
	}

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else if (modelIsTrue(model)) {
			setContentComponent(model);
		}else if(model.field("@class").equals(App.getGrpDao().getClassName())){
			setContentComponent(model);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public void resetContentComponent(){
		nikName.setText("");
		username.setText("");
		password.setText("");
		status.setText("");
		grp.setText("");
	}
	
	
	
	public void setColorView(){
		nikName.setBackground(App.whiteSmoke);
		username.setBackground(App.whiteSmoke);
		password.setBackground(App.whiteSmoke);
		status.setBackground(App.whiteSmoke);
		grp.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		nikName.setEditable(isEdit);
		username.setEditable(isEdit);
		password.setEditable(isEdit);
		status.setEditable(isEdit);
		grp.setEditable(isEdit);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 nikName.setText(model.field(UsrDao.nikName)+"");
				username.setText(model.field(UsrDao.username)+"");
				password.setText("-");
				byte tmp=model.field(UsrDao.status);
				ODatabaseDocumentTx db=App.getDbd();
				 ODatabaseRecordThreadLocal. INSTANCE.set(db);
				if (tmp==1) {
					status.setText(App.getT(db, "Aktif"));
				}else{
					status.setText(App.getT(db, "Tidak Aktif"));
				}
				db.close();
		 }else if (model.field("@class").equals(App.getGrpDao().getClassName())) {
				grp.setText(model.field(GrpDao.name)+"");
			}
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		nikName=new TextField();
		username=new TextField();
		password=new PasswordField();
		status=new  TextField();
		grp=new TextField();
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		super.buildForm(db);
		builder.append(db, "Status", status, 1, 7, 5);
		builder.append(db, "Hak Akses", grp, 1, 9, 5);
		
	}


	
}

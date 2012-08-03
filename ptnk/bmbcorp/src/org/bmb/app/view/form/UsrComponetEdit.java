package org.bmb.app.view.form;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;
import org.bmb.app.view.component.ComboBox;
import org.bmb.app.view.component.PasswordField;
import org.bmb.app.view.component.TextField;
import org.bmb.app.view.model.ODocumentToString;

import com.bmb.app.config.UtilAccount;
import com.bmb.app.dao.UsrDao;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class UsrComponetEdit extends UsrComponetFormDefault implements
		ComponetEditAdapter {
	protected PasswordField ulang;
//	protected DefaultComboBoxModel statusModel;
	protected ComboBox status;
	protected ComboBox grp;
	protected ODocumentToString [] modelGrp;
	
	protected ODocument model;
	
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Menambahkan Data Kandang Baru";
		icon = "icon tambah 16";
		
		initComponent(db);
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	

	public void load(ODocument model) {
		if (model==null) {
			nikName.setText("");
			username.setText("");
			password.setText("");
			status.setSelectedItem(null);
			grp.setSelectedItem(null);
		}else
		if (modelIsTrue(model)) {
			this.model=model;
			nikName.setText(model.field(UsrDao.nikName)+"");
			username.setText(model.field(UsrDao.username)+"");
			password.setText("");
			byte tmp=model.field(UsrDao.status);
			status.setSelectedIndex(tmp);
			//grp.setText(model.field(UsrDao.grp)+"");
			
		}else if (model.field("@class").equals(App.getGrpDao().getClassName())) {
			for (ODocumentToString os : modelGrp) {
				ODocument o=os.getO();
				if (o!=null) {
					if (o.getIdentity().equals(model.getIdentity())) {
						grp.setSelectedItem(os);
						break;
					}
				}
			}
		}
		
	}

	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		builder.append(db, "Ketik Ulang", ulang, 1, 7, 5);
		builder.append(db, "Status", status, 1, 9, 5);
		builder.append(db, "Hak Akses", grp, 1, 11, 5);
		
		buildButton(db);

	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 13);
	}
	
	
	public void setFocusEnter(){
		setFocusEnter(nikName, username);
		setFocusEnter(username, password);
		setFocusEnter(password, ulang);
		setFocusEnter(ulang, status);
		setFocusEnter(status, grp);
		setFocusEnter(grp, save);
//		setFocusEnter(save, reset);
		setFocusEnter(reset, nikName);
	}
	
	
	/**
	 *  harus ada di new
	 * @return
	 */

	public boolean validate(ODatabaseDocumentTx db){

		if (!validate(db, nikName, "Nama")) {
			return false;
		}
		if (!validate(db, username, "Username")) {
			return false;
		}
		
		if (password.getPassword().length>0) {
			if (!validate(db, password, ulang, "Ketik Ulang")) {
				return false;
			}
		}
		if (!username.getText().equalsIgnoreCase((String) model.field(UsrDao.username))) {
			if (!validate(db, App.getUsrDao(), username, UsrDao.fusername, UsrDao.username)) {
				return false;
			}
		}
		
		if (!validate(db, grp,  UsrDao.fgrp)) {
			return false;
		}
		return true;
	
	}
	public void aksiSave() {
		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			byte tmps=0;
			if (status.getSelectedIndex()==1) {
				tmps=1;
			}
			model.field(UsrDao.nikName, nikName.getText());
			model.field(UsrDao.username, username.getText());
			if (password.getPassword().length>0) {
				try {
					model.field(UsrDao.password, new UtilAccount().md5(new String(password.getPassword())));
				} catch (Exception e) {
					App.printErr(e);
				}
			}
			model.field(UsrDao.status, tmps, OType.BYTE);
			model.field(UsrDao.grp, ((ODocumentToString) grp.getSelectedItem()).getO(), OType.LINK);
			ODocument tmp=model.field(UsrDao.logdb);
			tmp.field("updatedBy", new Date(), OType.DATE);
			model.field(UsrDao.logdb, tmp, OType.EMBEDDED);
			
			
			try {
				model.save();
				for (WidgetAdapter w: widgeds) {
					w.modelWidgetChange(model);
				}
				for (WidgetAdapter w: widgeds) {
					w.modelWidgetChange(((ODocumentToString) grp.getSelectedItem()).getO());
				}
				App.showSaveOk();
				
			} catch (Exception e) {
				App.printErr(e);
			}finally{
				db.close();
			}
			
		}
	
		
	}
	
	
	public void initComponent(ODatabaseDocumentTx db){
		nikName=new TextField();
		username=new TextField();
		password=new PasswordField();
		
		String [] stringStatus={App.getT(db, "Tidak Aktif"),App.getT(db, "Aktif")};
		
		List<ODocument> grps=App.getGrpDao().getAll(db);
		modelGrp=new ODocumentToString[grps.size()+1];
		modelGrp[0]=new ODocumentToString(App.getGrpDao(), App.getT(db, "Pilih Hak Akses"));
		int i=1;
		for (ODocument o : grps) {
			modelGrp[i]=new ODocumentToString(App.getGrpDao(), o);
			i++;
		}
		grp=new ComboBox(modelGrp);
		
		ulang=new PasswordField();
		status=new ComboBox(stringStatus);
		
		save=new JButton(App.getT(db, "Save"));
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiSave();
				
			}
		})	;
	}


	
}

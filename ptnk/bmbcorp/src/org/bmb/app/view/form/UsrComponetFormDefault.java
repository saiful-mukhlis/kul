package org.bmb.app.view.form;



import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.PasswordField;
import org.bmb.app.view.component.TextField;

import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class UsrComponetFormDefault extends ComponetFormAbstract{
	
	protected TextField nikName;
	protected TextField username;
	protected PasswordField password;
	
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.37;
		dao=App.getUsrDao();
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		Double tmp = App.getW()*lebar;
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		 layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(db, "Nama", nikName, 1, 1, 5);
		builder.append(db, "Username", username, 1, 3, 5);
		builder.append(db, "Password", password, 1, 5, 5);

	}


	
	
	
	
	//untuk new
}

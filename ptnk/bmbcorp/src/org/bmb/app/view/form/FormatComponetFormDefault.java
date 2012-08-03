package org.bmb.app.view.form;



import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.FormatDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class FormatComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField name;
	protected TextField kop1;
	protected TextField kop2;
	protected TextField kop3;
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.36;
		dao=App.getFormatDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		name.setText("");
		kop1.setText("");
		kop2.setText("");
		kop3.setText("");
	}
	
	
	
	public void setColorView(){
		name.setBackground(App.whiteSmoke);
		kop1.setBackground(App.whiteSmoke);
		kop2.setBackground(App.whiteSmoke);
		kop3.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		name.setEditable(false);
		kop1.setEditable(false);
		kop2.setEditable(false);
		kop3.setEditable(false);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 name.setText(model.field(FormatDao.name)+"");
				kop1.setText(model.field(FormatDao.kop1)+"");
				kop2.setText(model.field(FormatDao.kop2)+"");
				kop3.setText(model.field(FormatDao.kop3)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		name=new TextField();
		kop1=new TextField();
		kop2=new TextField();
		kop3=new TextField();
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"10px,   	f:max("+tmp.intValue()+"px;p):g,  8px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.add(  name, builder.getCc().xyw(2, 1, 5));
		builder.add(  kop1, builder.getCc().xyw(2, 3, 5));
		builder.add(  kop2, builder.getCc().xyw(2, 5, 5));
		builder.add(  kop3, builder.getCc().xyw(2, 7, 5));
		
		
	}


	
	
	
	
	//untuk new
}

package org.bmb.app.view.form;



import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.ScrollPane;
import org.bmb.app.view.component.TextArea;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.GrpDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrpComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane snote;
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.36;
		dao=App.getGrpDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		code.setText("");
		name.setText("");
		note.setText("");
	}
	
	
	
	public void setColorView(){
		code.setBackground(App.whiteSmoke);
		name.setBackground(App.whiteSmoke);
		note.setBackground(App.whiteSmoke);
		snote.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		code.setEditable(false);
		name.setEditable(false);
		note.setEditable(false);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 code.setText(model.field(GrpDao.code)+"");
				name.setText(model.field(GrpDao.name)+"");
				note.setText(model.field(GrpDao.note)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		name=new TextField();
		note=new TextArea();
		snote=new ScrollPane(note);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  8px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,    f:40dlu,3dlu, p,  3dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(db, GrpDao.fcode, code, 1, 1, 5);
		builder.append(db, GrpDao.fname, name, 1, 3, 5);
		builder.append(db, GrpDao.fnote, snote, 1, 5, 5);
		
		
	}
	
}

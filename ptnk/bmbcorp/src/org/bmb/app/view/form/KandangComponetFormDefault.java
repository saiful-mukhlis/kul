package org.bmb.app.view.form;


import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.ScrollPane;
import org.bmb.app.view.component.TextArea;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField code;
	protected TextField name;
	protected TextArea note;
	protected ScrollPane aNote;
	
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.51;
		dao=App.getKandangDao();
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
		aNote.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		code.setEditable(isEdit);
		name.setEditable(isEdit);
		note.setEditable(isEdit);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 code.setText(model.field(KandangDao.code)+"");
			 name.setText(model.field(KandangDao.name)+"");
			 note.setText(model.field(KandangDao.note)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		code=new TextField();
		name=new TextField();
		note=new TextArea();
		note.setLineWrap(true);
		aNote=new ScrollPane(note);
		note.setPane(aNote);
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p),  5px,   	f:80px,  10px,   f:80px,   	10px,",

				"p,3dlu,   p,3dlu,  f:25dlu:g ,5dlu,   p,3dlu");

		 layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(db,  KandangDao.fcode, code, 1, 1, 5);
		builder.append(db, KandangDao.fname, name, 1, 3, 5);
		builder.append(db, KandangDao.fnote, aNote, 1, 5, 5);
		
	}


	
	
	
	
	//untuk new
}

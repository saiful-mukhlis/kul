package org.bmb.app.view.form;



import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.ScrollPane;
import org.bmb.app.view.component.TextArea;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.ProductDao;
import com.bmb.app.dao.ProductdDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class ProductdComponetFormDefault extends ComponetFormAbstract{
	
	
	
	protected TextField harga;
	protected TextField stock;
	
	public void init(ODatabaseDocumentTx db){
		lebar=0.01;
		dao=App.getProductDao();
		initComponent(db);
	}
	
	public void resetContentComponent(){
		harga.setText("");
		stock.setText("");
	}
	
	
	
	public void setColorView(){
		harga.setBackground(App.whiteSmoke);
		stock.setBackground(App.whiteSmoke);
	}
	
	public void setEditable(boolean isEdit){
		harga.setEditable(false);
		stock.setEditable(false);
	}
	
	public void setContentComponent(ODocument model){
		 if (modelIsTrue(model)){
			 harga.setText(model.field(ProductDao.harga)+"");
			stock.setText(model.field(ProductDao.saldo)+"");
		 }
	}
	
	public void initComponent(ODatabaseDocumentTx db){
		harga=new TextField();
		stock=new TextField();
	}
	
	public void buildForm(ODatabaseDocumentTx db) {
		
		Double tmp = App.getW()*lebar;//0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("+tmp.intValue()+"px;p):g,  8px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,   p,3dlu,   p,3dlu,   f:40dlu,3dlu,   p,3dlu,  p,3dlu,  p,3dlu ,10dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		builder.append(db, ProductDao.fharga, harga, 1, 1, 5);
		builder.append(db,  ProductDao.fsaldo, stock, 1, 3, 5);
		
		
	}


	
	
	
	
	//untuk new
}

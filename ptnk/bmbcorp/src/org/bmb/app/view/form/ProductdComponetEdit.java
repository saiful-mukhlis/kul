package org.bmb.app.view.form;


import java.util.Date;

import org.bmb.app.view.adapter.ComponetEditAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;

import com.bmb.app.dao.ProductDao;
import com.bmb.app.db.Productd;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;

public class ProductdComponetEdit extends ProductdComponetFormDefault implements
		ComponetEditAdapter {
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		title = "   Edit Data Harga dau Update Stock";
		icon = "icon edit 16";
		
		buildLabel(db);
		buildForm(db);
		buildPanel();
		
		setFocusEnter();
		aksiReset();
	}
	
	
	protected ODocument model;

	public void load(ODocument model) {
		if (model==null) {
			resetContentComponent();
		}else
		if (modelIsTrue(model)) {
			
			this.model=model;
			setContentComponent(model);
		}
	}

	@Override
	public void buildForm(ODatabaseDocumentTx db) {
		super.buildForm(db);
		buildButton(db);
	}



	@Override
	public void buildButton(ODatabaseDocumentTx db) {
		super.buildButton(db);
		builder.append( save, 7, 5);
	}
	
	
	public void setFocusEnter() {
		setFocusEnter(harga, stock);
		setFocusEnter(stock, save);
		setFocusEnter(save, reset);
		setFocusEnter(reset, harga);
	}
	
	protected double hargaa=0;
	protected double stocka=0;
	
	public boolean validate(ODatabaseDocumentTx db){
		try {
			hargaa=Double.parseDouble(harga.getText());
			if (hargaa<=0) {
				harga.setText("");
				App.showErrorFieldEmpty(db, "Harga");
				return false;
			}
		} catch (Exception e) {
			hargaa=0;
			App.printErr(e);
			harga.setText("");
			App.showErrorFieldEmpty(db, "Harga");
			return false;
		}
		
		try {
			stocka=Double.parseDouble(stock.getText());
			if (stocka<0) {
				stock.setText("");
				App.showErrorFieldEmpty(db, "Stock");
				return false;
			}
		} catch (Exception e) {
			stocka=0;
			App.printErr(e);
			stock.setText("");
			App.showErrorFieldEmpty(db, "Stock");
			return false;
		}
		
		return true;
	}
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=model;
			//update
			double stockLama=tmp.field(ProductDao.saldo);
			
			
			double da=0;
			double ka=0;
			
			if (stockLama==stocka) {
				 tmp.field(ProductDao.harga, hargaa, OType.DOUBLE);
				 tmp.save();
				 App.showSaveOk();
					getPanel().setVisible(false);
					db.close();
			}else{
				if (stockLama>stocka) {
					ka=stockLama-stocka;
				}else{
					da=stocka-stockLama;
				}
				Date dtmp=new Date();
				//+ productd = 
				ODocument htmp=App.getProductdDao().factoryModel(tmp,dtmp , Productd.TYPE_EDIT, da, ka, stocka, "Edit stock "+App.dateFormat.format(dtmp), 0);
				 
				  
				  tmp.field(ProductDao.harga, hargaa, OType.DOUBLE);
				tmp.field(ProductDao.saldo, stocka, OType.DOUBLE);
				
				
				try {
					db.begin(TXTYPE.OPTIMISTIC);
					 htmp.save();
					tmp.save();
					
					db.commit();
					for (WidgetAdapter w: widgeds) {
						w.modelWidgetAdd(htmp);
						this.model=tmp;
					}
					App.showSaveOk();
					getPanel().setVisible(false);
				} catch (Exception e) {
					db.rollback();
				}finally{
					db.close();
				}
			}
			
		}
	
		
	}


	
}

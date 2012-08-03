package org.bmb.app.view.form;


import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.TextField;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class LajurdComponetFormDefault extends ComponetFormAbstract {

	protected TextField kandang;
	protected TextField lajur;
	protected TextField tgl;
	protected TextField pupulasi;
	protected TextField mati;
	protected TextField butirpagibagus;
	protected TextField butirpagiretak;
	protected TextField butirsorebagus;
	protected TextField butirsoreretak;

	protected TextField totalbutirbagus;
	protected TextField totalbutirretak;

	protected TextField kgpagibagus;
	protected TextField kgpagiretak;
	protected TextField kgsorebagus;
	protected TextField kgsoreretak;

	protected TextField totalkgbagus;
	protected TextField totalkgretak;

	protected TextField hd;
	protected TextField btr;
	protected List<TextField> field=new ArrayList<TextField>();

	protected ODocument kandangModel;
	protected ODocument lajurModel;
	protected ODocument lajurdModel;

	public void init(ODatabaseDocumentTx db) {
		lebar = 0.05;
		dao = App.getLajurdDao();
		
		initComponent(db);
		field.add(kandang);
		field.add(lajur);
		field.add(tgl);
		field.add(pupulasi);
		field.add(mati);
		field.add(butirpagibagus);
		field.add(butirpagiretak);
		field.add(butirsorebagus);
		field.add(butirsoreretak);

		field.add(totalbutirbagus);
		field.add(totalbutirretak);

		field.add(kgpagibagus);
		field.add(kgpagiretak);
		field.add(kgsorebagus);
		field.add(kgsoreretak);

		field.add(totalkgbagus);
		field.add(totalkgretak);
		
		field.add(hd);
		field.add(btr);
		
	
		
	}

	public void resetContentComponent() {
		for (TextField t : field) {
			clearText(t);
		}
	}

	public void setColorView() {
		for (TextField t : field) {
			t.setBackground(App.whiteSmoke);
		}
	}

	public void setEditable(boolean isEdit) {
		for (TextField t : field) {
			t.setEditable(isEdit);
		}
	}

	public void setContentComponent(ODocument model) {
		if (modelIsTrue(model)) {
			kandang.setText(kandangModel.field(KandangDao.name) + "");
			lajur.setText(lajurModel.field(LajurDao.name) + "");
			tgl.setText(App.dateFormat.format(lajurdModel.field(LajurdDao.tgl)) + "");
			pupulasi.setText(lajurdModel.field(LajurdDao.pupulasi) + "");
			mati.setText(lajurdModel.field(LajurdDao.mati) + "");
			butirpagibagus.setText(lajurdModel.field(LajurdDao.butirpagibagus)
					+ "");
			butirpagiretak.setText(lajurdModel.field(LajurdDao.butirpagiretak)
					+ "");
			butirsorebagus.setText(lajurdModel.field(LajurdDao.butirsorebagus)
					+ "");
			butirsoreretak.setText(lajurdModel.field(LajurdDao.butirsoreretak)
					+ "");
			totalbutirbagus.setText(App.getLajurdDao().getTotalButirBagus(
					lajurdModel)
					+ "");
			totalbutirretak.setText(App.getLajurdDao().getTotalButirRetak(
					lajurdModel)
					+ "");
			kgpagibagus.setText(App.paymentFormat2.format(lajurdModel.field(LajurdDao.kgpagibagus) )+ "");
			kgpagiretak.setText(App.paymentFormat2.format(lajurdModel.field(LajurdDao.kgpagiretak) )+ "");
			kgsorebagus.setText(App.paymentFormat2.format(lajurdModel.field(LajurdDao.kgsorebagus) )+ "");
			kgsoreretak.setText(App.paymentFormat2.format(lajurdModel.field(LajurdDao.kgsoreretak)) + "");
			totalkgbagus.setText(App.paymentFormat2.format(App.getLajurdDao()
					.getTotalKgBagus(lajurdModel)) + "");
			totalkgretak.setText(App.paymentFormat2.format(App.getLajurdDao()
					.getTotalKgRetak(lajurdModel) )+ "");
			hd.setText(App.paymentFormat2.format(App.getLajurdDao().getHD(lajurdModel) )+ "");
			btr.setText(App.paymentFormat2.format(App.getLajurdDao().getP(lajurdModel)) + "");

		}
	}

	public void initComponent(ODatabaseDocumentTx db) {
		kandang= new TextField();
		lajur= new TextField();
		tgl= new TextField();
		pupulasi= new TextField();
		mati= new TextField();
		butirpagibagus= new TextField();
		butirpagiretak= new TextField();
		butirsorebagus= new TextField();
		butirsoreretak= new TextField();

		totalbutirbagus= new TextField();
		totalbutirretak= new TextField();

		kgpagibagus= new TextField();
		kgpagiretak= new TextField();
		kgsorebagus= new TextField();
		kgsoreretak= new TextField();

		totalkgbagus= new TextField();
		totalkgretak= new TextField();

		hd= new TextField();
		btr= new TextField();
		
		butirpagibagus.setHorizontalAlignment(SwingConstants.RIGHT);
		butirpagiretak.setHorizontalAlignment(SwingConstants.RIGHT);
		butirsorebagus.setHorizontalAlignment(SwingConstants.RIGHT);
		butirsoreretak.setHorizontalAlignment(SwingConstants.RIGHT);

		totalbutirbagus.setHorizontalAlignment(SwingConstants.RIGHT);
		totalbutirretak.setHorizontalAlignment(SwingConstants.RIGHT);

		kgpagibagus.setHorizontalAlignment(SwingConstants.RIGHT);
		kgpagiretak.setHorizontalAlignment(SwingConstants.RIGHT);
		kgsorebagus.setHorizontalAlignment(SwingConstants.RIGHT);
		kgsoreretak.setHorizontalAlignment(SwingConstants.RIGHT);

		totalkgbagus.setHorizontalAlignment(SwingConstants.RIGHT);
		totalkgretak.setHorizontalAlignment(SwingConstants.RIGHT);

		hd.setHorizontalAlignment(SwingConstants.RIGHT);
		btr.setHorizontalAlignment(SwingConstants.RIGHT);
		
	}

	public void buildForm(ODatabaseDocumentTx db) {

		Double tmp = App.getW() * lebar;// 0.51
		layout = new FormLayout(
				"r:p,   	10px,   	f:max("
						+ tmp.intValue()
						+ "px;p):g(.4),  10px,   	f:max(80px;p),  10px,   	f:max(80px;p),     	10px,",

				"p,3dlu,   p,3dlu,    p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,   p,3dlu,    p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu,     p,3dlu");

		layout.setColumnGroups(new int[][] { { 5, 7 } });
		builder = new FormBuilder(layout, true);

		int i = -1;


		
		builder.append(db, "Kandang", kandang, 1, (i = i + 2), 5);
		builder.append(db, "Lajur", lajur, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.ftgl, tgl, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fpupulasi, pupulasi, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fmati, mati, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, "Prodoksi telur /  Butir", 1, (i = i + 2), 7);
		builder.append(db, LajurdDao.fbutirpagibagus, butirpagibagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fbutirsorebagus, butirsorebagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fbagus, totalbutirbagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fbutirpagiretak, butirpagiretak, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fbutirsoreretak, butirsoreretak, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fretak, totalbutirretak, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, "Prodoksi telur / Kg", 1, (i = i + 2), 7);
		builder.append(db, LajurdDao.fkgpagibagus, kgpagibagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fkgsorebagus, kgsorebagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fbagus, totalkgbagus, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fkgpagiretak, kgpagiretak, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fkgsoreretak, kgsoreretak, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fretak, totalkgretak, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, "Peforma Produksi", 1, (i = i + 2), 7);
		builder.append(db, LajurdDao.fhd, hd, 1, (i = i + 2), 5);
		builder.append(db, LajurdDao.fp, btr, 1, (i = i + 2), 5);

	}

	public ODocument getKandangModel() {
		return kandangModel;
	}

	public void setKandangModel(ODocument kandangModel) {
		this.kandangModel = kandangModel;
	}

	public ODocument getLajurModel() {
		return lajurModel;
	}

	public void setLajurModel(ODocument lajurModel) {
		this.lajurModel = lajurModel;
	}

	public ODocument getLajurdModel() {
		return lajurdModel;
	}

	public void setLajurdModel(ODocument lajurdModel) {
		this.lajurdModel = lajurdModel;
	}

	// untuk new
}

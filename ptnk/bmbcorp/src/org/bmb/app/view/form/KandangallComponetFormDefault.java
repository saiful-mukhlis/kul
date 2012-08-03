package org.bmb.app.view.form;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.bmb.app.view.abst.form.ComponetFormAbstract;
import org.bmb.app.view.builder.FormBuilder;

import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.KandangdDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.dao.LajurdDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class KandangallComponetFormDefault extends ComponetFormAbstract {

	protected JTextField tgl;
	
	protected JTextField umur;
	protected JTextField pupulasi;
	protected JTextField mati;
	protected JTextField pakan;
	
	protected JTextField bagusbutir;
	protected JTextField baguskg;
	
	protected JTextField retakbutir;
	protected JTextField retakkg;
	
	protected JTextField totalbutir;
	protected JTextField totalkg;
	
	protected JTextField hd;
	protected JTextField btr;
	
	protected JTextField fcr;
	protected JTextField ket1;
	
	protected JTextField abn;
	protected JTextField ket2;
	
	protected List<JTextField> field=new ArrayList<JTextField>();

	
	protected ODocument kandangdModel;

	public void init(ODatabaseDocumentTx db) {
		lebar = 0.05;
		dao = App.getKandangallDao();
		
		initComponent(db);
		
		field.add(tgl);
		
		field.add(umur);
		field.add(pupulasi);
		field.add(mati);
		field.add(pakan);
		
		field.add(bagusbutir);
		field.add(baguskg);
		
		field.add(retakbutir);
		field.add(retakkg);
		
		field.add(totalbutir);
		field.add(totalkg);
		
		field.add(hd);
		field.add(btr);
		
		field.add(fcr);
		field.add(ket1);
		
		field.add(abn);
		field.add(ket2);

		
	
		
	}

	public void resetContentComponent() {
		for (JTextField t : field) {
			clearText(t);
			//t.setBackground(App.whiteSmoke);
		}
	}

	public void setColorView() {
		for (final JTextField t : field) {
			t.setBackground(App.whiteSmoke);
			t.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseExited(MouseEvent e) {
					if (t.getBackground()!=App.selected) {
						t.setBackground(App.whiteSmoke);
					}
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					if (t.getBackground()!=App.selected) {
						t.setBackground(Color.WHITE);
					}
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					if (t.getBackground()==Color.WHITE) {
						t.setBackground(App.selected);
					}else{
						t.setBackground(Color.WHITE);
					}
				}
			});
		}
	}

	public void setEditable(boolean isEdit) {
		for (JTextField t : field) {
			t.setEditable(isEdit);
		}
	}

	public void setContentComponent(ODocument model) {
		if (modelIsTrue(model)) {
			
			tgl.setText(App.dateFormat.format(kandangdModel.field(KandangdDao.tgl)) + "");
			
			umur.setText(kandangdModel.field(KandangdDao.umur) + "");
			pupulasi.setText(kandangdModel.field(KandangdDao.pupulasi) + "");
			mati.setText(kandangdModel.field(KandangdDao.mati) + "");
			pakan.setText(kandangdModel.field(KandangdDao.pakan) + "");
			
			bagusbutir.setText(kandangdModel.field(KandangdDao.bagusbutir) + "");
			baguskg.setText(App.paymentFormat2.format(kandangdModel.field(KandangdDao.baguskg)) + "");
			
			retakbutir.setText(kandangdModel.field(KandangdDao.retakbutir) + "");
			retakkg.setText(App.paymentFormat2.format(kandangdModel.field(KandangdDao.retakkg)) + "");
			
			totalbutir.setText(App.getKandangdDao().getTotalTelurButir(kandangdModel) + "");
			totalkg.setText(App.paymentFormat2.format(App.getKandangdDao().getTotalTelurKg(kandangdModel)) + "");
			
			hd.setText(App.paymentFormat2.format(App.getKandangdDao().getHD(kandangdModel)) + "");
			btr.setText(App.paymentFormat2.format(App.getKandangdDao().getP(kandangdModel)) + "");
			
			fcr.setText(App.paymentFormat2.format(App.getKandangdDao().getFcr(kandangdModel)) + "");
			ket1.setText(App.getKandangdDao().getKet1(kandangdModel) + "");
			
			abn.setText(App.paymentFormat2.format(App.getKandangdDao().getA(kandangdModel)) + "");
			ket2.setText(App.getKandangdDao().getKet2(kandangdModel) + "");
			
			
			

		}
	}

	public void initComponent(ODatabaseDocumentTx db) {
		
		tgl= new JTextField();
		
		umur= new JTextField();
		pupulasi= new JTextField();
		mati= new JTextField();
		pakan= new JTextField();
		
		bagusbutir= new JTextField();
		baguskg= new JTextField();
		
		retakbutir= new JTextField();
		retakkg= new JTextField();
		
		totalbutir= new JTextField();
		totalkg= new JTextField();
		
		hd= new JTextField();
		btr= new JTextField();
		
		fcr= new JTextField();
		ket1= new JTextField();
		
		abn= new JTextField();
		ket2= new JTextField();
		
		
		bagusbutir.setHorizontalAlignment(SwingConstants.RIGHT);
		baguskg.setHorizontalAlignment(SwingConstants.RIGHT);
		
		retakbutir.setHorizontalAlignment(SwingConstants.RIGHT);
		retakkg.setHorizontalAlignment(SwingConstants.RIGHT);
		
		totalbutir.setHorizontalAlignment(SwingConstants.RIGHT);
		totalkg.setHorizontalAlignment(SwingConstants.RIGHT);
		
		hd.setHorizontalAlignment(SwingConstants.RIGHT);
		btr.setHorizontalAlignment(SwingConstants.RIGHT);
		
		fcr.setHorizontalAlignment(SwingConstants.RIGHT);
		
		abn.setHorizontalAlignment(SwingConstants.RIGHT);

		
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


		
		builder.append(db, KandangdDao.ftgl, tgl, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fumur, umur, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fpupulasi, pupulasi, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fmati, mati, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fpakan, pakan, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, KandangdDao.fproduksi, 1, (i = i + 2), 7);
		builder.append(db, KandangdDao.fbutir, bagusbutir, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fkg, retakbutir, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, KandangdDao.fretak, 1, (i = i + 2), 7);
		builder.append(db, KandangdDao.fbutir, baguskg, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fkg, retakkg, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, KandangdDao.ftotaltelor, 1, (i = i + 2), 7);
		builder.append(db, KandangdDao.fbutir, totalbutir, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fkg, totalkg, 1, (i = i + 2), 5);
		
		builder.addSeparator(db, KandangdDao.fpp, 1, (i = i + 2), 7);
		builder.append(db, KandangdDao.fbutir, hd, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fkg, btr, 1, (i = i + 2), 5);
		
		builder.append(db, KandangdDao.ffcr, fcr, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fket, ket1, 1, (i = i + 2), 5);
		
		builder.append(db, KandangdDao.fa, abn, 1, (i = i + 2), 5);
		builder.append(db, KandangdDao.fket, ket2, 1, (i = i + 2), 5);

		
		
	}


	public ODocument getKandangdModel() {
		return kandangdModel;
	}

	public void setKandangdModel(ODocument kandangdModel) {
		this.kandangdModel = kandangdModel;
	}


	// untuk new
}

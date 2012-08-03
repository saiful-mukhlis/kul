package com.bmb.app.print;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


import ar.com.fdvs.dj.domain.DynamicReport;

import com.bmb.app.dao.FormatDao;
import com.bmb.app.dao.KandangDao;
import com.bmb.app.dao.KandangdDao;
import com.bmb.app.global.App;
import com.bmb.app.other.DialogLoading;
import com.bmb.app.other.NamaBulan;
import com.bmb.app.print.adapter.ModelAdapter;
import com.bmb.app.print.model.KandangdPrintModel;
import com.bmb.app.print.model.LajurdPrintModel;
import com.bmb.app.view.adapter.LoadingAdapter;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class KandangdPrint extends PrintAbstract{
	
	public ModelAdapter createModel(){
		return new KandangdPrintModel();
	}
	
	public void init(ODatabaseDocumentTx db,String kandang, String bulan){
		ODocument f=App.getFormatDao().getOne(db, FormatDao.code, "kandang");
		if (f!=null) {
			title=f.field(FormatDao.kop1);
			subTitle=f.field(FormatDao.kop2)+" "+kandang;
			String tmp=f.field(FormatDao.kop3);
			if (tmp!=null && !tmp.equalsIgnoreCase("")) {
				subTitle=subTitle+"\\n"+tmp+" "+bulan;
			}
			tinggiHeader=20;
			satuHalamanPenuh=true;
		}else{
			title="BUKU CATATAN HARIAN";
			subTitle="Bulan : ";
			tinggiHeader=50;
			satuHalamanPenuh=true;
		}
		
	}

	public void buildColumn(){
		reportChild.addColumn(buildColumn("no", "No", 8));
		reportChild.addColumn(buildColumn("tgl", "Tanggal", 28));
		
		reportChild.addColumn(buildColumn("umur", "Umur", 15));
		reportChild.addColumn(buildColumn("populasi", "Populasi", 20));
		reportChild.addColumn(buildColumn("mati", "Mati", 15));
		reportChild.addColumn(buildColumn("pakan", "pakan/hari", 25));
		reportChild.addColumn(buildColumn("bagusButir", "Butir", 15));
		reportChild.addColumn(buildColumn("bagusKg", "Kg", 15));
		reportChild.addColumn(buildColumn("retakButir", "Butir", 15));
		reportChild.addColumn(buildColumn("retakKg", "Kg", 15));
		reportChild.addColumn(buildColumn("totalButir", "Butir", 15));
		reportChild.addColumn(buildColumn("totalKg", "Kg", 15));
		reportChild.addColumn(buildColumn("hd", "% HD", 20));
		reportChild.addColumn(buildColumn("btr", "Btr Tlr[gr/bt]", 30));
		reportChild.addColumn(buildColumn("fcr", "FCR", 20));
		reportChild.addColumn(buildColumn("ket1", "Ket", 20));
		reportChild.addColumn(buildColumn("abn", "Abnormalitas", 20));
		reportChild.addColumn(buildColumn("ket2", "Ket", 20));
		
	}
	
	public void buildColSpan(){
		reportChild.setColspan(5, 1, "Jumlah");
		reportChild.setColspan(6, 2, "Prodoksi Telur");
		reportChild.setColspan(8, 2, "Jumlah yg Retak");
		reportChild.setColspan(10, 2, "Total telur");
		reportChild.setColspan(12, 2, "Peforma Produksi");
	}
	public void buildReport(ODatabaseDocumentTx db, ODocument lajura, NamaBulan bln , int tahun){
		initStyles();
		String tmpk=lajura.field(KandangDao.name);
		if (tmpk==null) {
			tmpk="";
		}
		init(db, tmpk,bln.getNamaBulan());
//		subTitle=subTitle+bln.getNamaBulan();
		initReportChild();
		buildColumn();
		buildColSpan();
		
		data=new ArrayList<ModelAdapter>();
		model=App.getKandangdDao().getAllForPrint(db, lajura, bln.getIntBulan(), tahun);
		int index=0;
		for (ODocument oDocument : model) {
			int no = index + 1;
			ModelAdapter m=new KandangdPrintModel();
			m.setNo(no+"");
			m.setO(oDocument);
			data.add(m);
			
			index++;
		}
			
	}
	public void run(final ODatabaseDocumentTx db, final ODocument lajura){
		Object[] possibilities = App.getBulan();
		final NamaBulan s = (NamaBulan)JOptionPane.showInputDialog(getFrame(panel),
		                    "Bulan : \n",
		                    "Pilih Bulan",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    possibilities,
		                    possibilities[0]);
		if (s!=null) {
			boolean salah=true;
			while (salah) {
				String inputValue = JOptionPane.showInputDialog(getFrame(panel), "Tahun :", "Inputkan Tahun", JOptionPane.PLAIN_MESSAGE); 
				if (inputValue!=null) {
					if (inputValue.length()==4) {
						try {
							final int th=Integer.parseInt(inputValue);
							salah=false;
							DialogLoading dl=new DialogLoading(db,getWindow(panel),new LoadingAdapter() {

								@Override
								public void runTask() {
									List<DynamicReport> childReport=new ArrayList<DynamicReport>();
									List<List> datas=new ArrayList<List>();
									buildReport(db, lajura, s, th);
									childReport.add(reportChild.build());
									datas.add(data);

									String t1="";
									String t2="";
									String t3="";
									ODocument f=App.getFormatDao().getOne(db, FormatDao.code, "kop");
									if (f!=null) {
										t1=f.field(FormatDao.kop1);
										t2=f.field(FormatDao.kop2);
										t3=f.field(FormatDao.kop3);
										tinggiHeader=50;
										satuHalamanPenuh=true;
									}
									
									AbstractJadwalReport report = new AbstractJadwalReport(t1, t2, t3, "");
									//report.setLandscape(false);
									report.setDatas(datas);
									report.setChildReport(childReport);
									runReport(db,report);
									
								}
							
							});
							dl.setVisible(true);
						} catch (Exception e) {
							// TODO: handle exception
							App.printErr(e);
						}
					}
				}else{
					salah=false;
				}
				
			}
		}
		
		
	}
	
	
	
	
	
	public KandangdPrint(JPanel panel) {
		super(panel);
	}
	
	
	
}

package com.bmb.app.print;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;


import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.constants.Page;

import com.bmb.app.dao.FormatDao;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.PenjualanbDao;
import com.bmb.app.dao.PenjualanhDao;
import com.bmb.app.global.App;
import com.bmb.app.other.DialogLoading;
import com.bmb.app.print.adapter.ModelAdapter;
import com.bmb.app.print.model.KandangdPrintModel;
import com.bmb.app.print.model.PenjualanPrintModel;
import com.bmb.app.view.adapter.LoadingAdapter;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class PenjualanPrint extends PrintAbstract{
	
	public ModelAdapter createModel(){
		return new PenjualanPrintModel();
	}
	
	public void init(ODatabaseDocumentTx db, boolean isBulan, int bulan, Date tgla, String total){
		String tmp1;
		if (isBulan) {
			tmp1="penjualanb";
		}else{
			tmp1="penjualanh";
		}
		ODocument f=App.getFormatDao().getOne(db, FormatDao.code, tmp1);
		if (f!=null) {
			title=f.field(FormatDao.kop1);
			if (isBulan) {
				subTitle=f.field(FormatDao.kop2)+" "+App.bln[bulan];
			}else{
				subTitle=f.field(FormatDao.kop2)+" "+App.dateFormat.format(tgla);
			}
			String tmp=f.field(FormatDao.kop3);
			if (tmp!=null && !tmp.equalsIgnoreCase("")) {
				subTitle=subTitle+"\\n"+tmp+" "+total;
			}
			tinggiHeader=50;
			satuHalamanPenuh=true;
		}else{
			title="BUKU CATATAN HARIAN";
			subTitle="Bulan : ";
			tinggiHeader=50;
			satuHalamanPenuh=true;
		}
	}

	public void buildColumn(){
		reportChild.addColumn(buildColumn("no", "No", 20));
		reportChild.addColumn(buildColumn("tgl", "Tanggal", 20));
		
		reportChild.addColumn(buildColumn("nota", "Nota", 20));
		reportChild.addColumn(buildColumn("pembeli", "Nama Pembeli", 20));
		reportChild.addColumn(buildColumn("jumlah", "Jumlah (kg)", 20));
		reportChild.addColumn(buildColumn("harga", "Harga Satuan", 20));
		reportChild.addColumn(buildColumn("total", "Total", 20));
		reportChild.addColumn(buildColumn("bayar", "Dibayar", 20));
		reportChild.addColumn(buildColumn("hutang", "Hutang", 20));
		reportChild.addColumn(buildColumn("ket", "Keterangan", 20));
		
		
		
		
		
	}
	
	public void buildColSpan(){
		reportChild.setColspan(7, 2, "Jumlah Uang");
	}
	public void buildReport(ODatabaseDocumentTx db, ODocument penjualanb){
		int bulan=penjualanb.field(PenjualanbDao.b);
		int tahun=penjualanb.field(PenjualanbDao.t);
		initStyles();
		Double total=penjualanb.field(PenjualanbDao.total);
		if (total==null) {
			total=(double) 0;
		}
		String tot="Rp.  "+App.paymentFormat2.format(total);
		init(db, true, bulan, null, tot);
		//subTitle=subTitle+" "+App.bln[bulan];
		initReportChild();
		reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		buildColumn();
		buildColSpan();
		
		data=new ArrayList<ModelAdapter>();
		model=App.getPenjualanDao().getAllForPrintByBulan(db, bulan, tahun);
		int index=0;
		for (ODocument oDocument : model) {
			int no = index + 1;
			ModelAdapter m=new PenjualanPrintModel();
			ODocument p=oDocument.field(PenjualanDao.pelanggan);
			p.field(PelangganDao.name);
			m.setO(p);
			m.setNo(no+"");
			m.setO(oDocument);
			data.add(m);
			
			index++;
		}
			
	}
	
	public void buildReporth(ODatabaseDocumentTx db, ODocument penjualanh){
		Date tgla=penjualanh.field(PenjualanhDao.tgl);
		Double total=penjualanh.field(PenjualanhDao.total);
		if (total==null) {
			total=(double) 0;
		}
		String tot="Rp.  "+App.paymentFormat2.format(total);
		initStyles();
		init(db, false, 0, tgla, tot);
		//subTitle=subTitle+" "+App.dateFormat.format(tgla);
		initReportChild();
		reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		buildColumn();
		buildColSpan();
		
		data=new ArrayList<ModelAdapter>();
		model=App.getPenjualanDao().getAllForPrintByTgl(db, tgla);
		int index=0;
		for (ODocument oDocument : model) {
			int no = index + 1;
			ModelAdapter m=new PenjualanPrintModel();
			ODocument p=oDocument.field(PenjualanDao.pelanggan);
			p.field(PelangganDao.name);
			m.setO(p);
			m.setNo(no+"");
			m.setO(oDocument);
			data.add(m);
			
			index++;
		}
			
	}
	
	public void run(final ODatabaseDocumentTx db, final ODocument modelPenjualan){
		if (modelPenjualan!=null) {
			

			if (modelPenjualan.field("@class").equals(
					App.getPenjualanbDao().getClassName())) {
				//b
				DialogLoading dl=new DialogLoading(db,getWindow(panel),new LoadingAdapter() {

					@Override
					public void runTask() {
						List<DynamicReport> childReport=new ArrayList<DynamicReport>();
						List<List> datas=new ArrayList<List>();
						buildReport(db, modelPenjualan);
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
						report.setLandscape(false);
						report.setDatas(datas);
						report.setChildReport(childReport);
						runReport(db,report);
						
					}
				
				});
				dl.setVisible(true);
				
				
				
			} else if (modelPenjualan.field("@class").equals(
					App.getPenjualanhDao().getClassName())) {
				//h
				DialogLoading dl=new DialogLoading(db,getWindow(panel),new LoadingAdapter() {

					@Override
					public void runTask() {
						List<DynamicReport> childReport=new ArrayList<DynamicReport>();
						List<List> datas=new ArrayList<List>();
						buildReporth(db, modelPenjualan);
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
						report.setLandscape(false);
						report.setDatas(datas);
						report.setChildReport(childReport);
						runReport(db,report);
						
					}
				
				});
				dl.setVisible(true);
				
				
				
			}
		
			

			
		}
	}
	
	
	
	
	
	public PenjualanPrint(JPanel panel) {
		super(panel);
	}
	
	
	
}

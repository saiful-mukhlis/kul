package com.bmb.app.print;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



import ar.com.fdvs.dj.domain.DynamicReport;

import com.bmb.app.dao.FormatDao;
import com.bmb.app.dao.LajurDao;
import com.bmb.app.global.App;
import com.bmb.app.other.DialogLoading;
import com.bmb.app.other.NamaBulan;
import com.bmb.app.print.adapter.ModelAdapter;
import com.bmb.app.print.model.LajurdPrintModel;
import com.bmb.app.view.adapter.LoadingAdapter;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class LajurdPrint extends PrintAbstract{
	
	
	public void buildReport(ODatabaseDocumentTx db, ODocument lajura, NamaBulan bln , int tahun){
		initStyles();
		String tmpl=lajura.field(LajurDao.name);
		if (tmpl==null) {
			tmpl="";
		}
		init(db,tmpl, bln.getNamaBulan());
//		subTitle=subTitle+bln.getNamaBulan();
		initReportChild();
		buildColumn();
		buildColSpan();
		
		data=new ArrayList<ModelAdapter>();
		model=App.getLajurdDao().getAllForPrint(db, lajura, bln.getIntBulan(), tahun);
		int index=0;
		for (ODocument oDocument : model) {
			int no = index + 1;
			ModelAdapter m=new LajurdPrintModel();
			m.setNo(no+"");
			m.setO(oDocument);
			data.add(m);
			
			index++;
		}
			
	}
	
	public void init(ODatabaseDocumentTx db, String lajurNama,String bln ){
		ODocument f=App.getFormatDao().getOne(db, FormatDao.code, "lajur");
		if (f!=null) {
			title=f.field(FormatDao.kop1);
			subTitle=f.field(FormatDao.kop2)+" "+lajurNama;
			String tmp=f.field(FormatDao.kop3);
			if (tmp!=null && !tmp.equalsIgnoreCase("")) {
				subTitle=subTitle+"\\n"+tmp+" "+bln;
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
		
		reportChild.addColumn(buildColumn("populasi", "Populasi", 20));
		reportChild.addColumn(buildColumn("mati", "Mati", 15));
		reportChild.addColumn(buildColumn("butirPagiBagus", "Pagi [Bgs]", 20));
		reportChild.addColumn(buildColumn("butirPagiRetak", "Pagi[ Rtk]", 20));
		reportChild.addColumn(buildColumn("butirSoreBagus", "Sore [Bgs]", 20));
		reportChild.addColumn(buildColumn("butirSoreRetak", "Sore [Rtk]", 20));
		reportChild.addColumn(buildColumn("totalButirBagus", "Bagus", 15));
		reportChild.addColumn(buildColumn("totalButirReteak", "Retak", 15));
		reportChild.addColumn(buildColumn("kgPagiBagus", "Pagi [Bgs]", 20));
		reportChild.addColumn(buildColumn("kgPagiRetak", "Pagi[ Rtk]", 20));
		reportChild.addColumn(buildColumn("kgSoreBagus", "Sore [Bgs]", 20));
		reportChild.addColumn(buildColumn("kgSoreRetak", "Sore [Rtk]", 20));
		reportChild.addColumn(buildColumn("totalKgBagus", "Bagus", 15));
		reportChild.addColumn(buildColumn("totalKgReteak", "Retak", 15));
		reportChild.addColumn(buildColumn("hd", "% HD", 15));
		reportChild.addColumn(buildColumn("btr", "Btr Tlr[gr/bt]", 25));
		
		
	}
	
	public void buildColSpan(){
		reportChild.setColspan(4, 4, "Prodoksi telur /  Butir");
		reportChild.setColspan(8, 2, "Total Btr");
		reportChild.setColspan(10, 4, "Prodoksi telur / Kg");
		reportChild.setColspan(14, 2, "Total Kg");
		reportChild.setColspan(16, 2, "Peforma Produksi");
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
	
	
	
	
	
	
	
//	protected DynamicReportBuilder reportChild;
//	protected Style isiStyle;
//	protected Style headerStyle;
//	protected Style titleStyle;
//	protected List<ODocument> model;
//	protected List<ModelAdapter> data;
//	protected String title;
//	protected String subTitle;
//	protected int tinggiHeader;
//	protected boolean satuHalamanPenuh;
//	protected JPanel panel;
//	
//	public void runReport(ODatabaseDocumentTx db, AbstractJadwalReport report){
//		report.setTtda1("");
//		report.setTtda2("");
//		report.setTtda3("");
//		report.setTtda4("");
//		
//		report.setTtdb1("");
//		report.setTtdb2("");
//		report.setTtdb3("");
//		report.setTtdb4("");
//		
//		
//		try {
//			report.testReport();
//		} catch (Exception e1) {
//			App.printErr(e1);
//		}
//		//JasperViewer.viewReport(report.getJp(), false);	
//		JasperViewer jasperViewer = new JasperViewer(report.getJp(),
//				false);
//		jasperViewer.setAlwaysOnTop(true);
//		JFrame x=getFrame(jasperViewer);
//		x.setIconImage(App.getImage(db, "icon app 16").getImage());
//		x.setTitle(title);
//		jasperViewer.setVisible(true);
//	}
//	
//	
//
//	
//	public void initReportChild(){
//		reportChild = new DynamicReportBuilder();
//		reportChild.setTitle(title);
//		reportChild.setSubtitle(subTitle);
//		reportChild.setHeaderHeight(tinggiHeader);
//		reportChild.setUseFullPageWidth(satuHalamanPenuh);
//		reportChild.setColumnsPerPage(1);
//		reportChild.setAllowDetailSplit(true);
//		reportChild.setDefaultStyles(titleStyle, titleStyle,
//				headerStyle, isiStyle);
//		reportChild.setPageSizeAndOrientation(Page.Page_A4_Landscape());
//		
//		reportChild.setAllowDetailSplit(true);
//		//reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
//		//reportChild.setPageSizeAndOrientation(Page.Page_Legal_Portrait());
//	}
//	
//
//	
//	public void buildReport(ODatabaseDocumentTx db, ODocument lajura, NamaBulan bln , int tahun){
//		initStyles();
//		subTitle=subTitle+bln.getNamaBulan();
//		init(db);
//		initReportChild();
//		buildColumn();
//		buildColSpan();
//		
//		data=new ArrayList<ModelAdapter>();
//		model=App.getLajurdDao().getAllForPrint(db, lajura, bln.getIntBulan(), tahun);
//		int index=0;
//		for (ODocument oDocument : model) {
//			int no = index + 1;
//			LajurdPrintModel m=new LajurdPrintModel();
//			m.setNo(no+"");
//			m.setO(oDocument);
//			data.add(m);
//			
//			index++;
//		}
//			
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	public void initStyles() {
//
//		
//		headerStyle = new StyleBuilder(true)
//				.setHorizontalAlign(HorizontalAlign.CENTER)
//				.setVerticalAlign(VerticalAlign.MIDDLE)
//				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
//				.setBorderColor(Color.BLACK)
//				.setTransparency(Transparency.OPAQUE)
//				.setBackgroundColor(Color.WHITE).build();
//
//		isiStyle = new StyleBuilder(true)
//				.setHorizontalAlign(HorizontalAlign.CENTER)
//				.setVerticalAlign(VerticalAlign.MIDDLE)
//				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
//				.setBorderColor(Color.BLACK).setStretchWithOverflow(false)
//				.setTextColor(Color.BLACK)
//				// .setStretchWithOverflow(true)
//				// .setBackgroundColor(Color.BLUE)
//				.setTransparency(Transparency.OPAQUE)
//				// .setTransparent(false)
//				.build();
//
//		titleStyle = new StyleBuilder(true)
//				.setHorizontalAlign(HorizontalAlign.CENTER)
//				.setVerticalAlign(VerticalAlign.MIDDLE)
//				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setTextColor(Color.BLACK) //VERDANA_BIG_BOLD
//				// .setBackgroundColor(Color.BLUE)
//				// .setTransparency(Transparency.OPAQUE)
//				// .setTransparent(false)
//				.build();
//
//	}
//	
//	
//	public AbstractColumn buildColumn(String property, String title, int width,
//			Style style, Style styleHeader, Collection styleCond) {
//		ColumnBuilder c = ColumnBuilder.getNew();
//		c.setColumnProperty(property, String.class.getName());
//		c.setTitle(title);
//		c.setWidth(width);
//		c.setStyle(style);
//		c.addConditionalStyles(styleCond);
//		c.setHeaderStyle(styleHeader);
//		return c.build();
//	}
//	
//	public AbstractColumn buildColumn(String property, String title, int width) {
//		ColumnBuilder c = ColumnBuilder.getNew();
//		c.setColumnProperty(property, String.class.getName());
//		c.setTitle(title);
//		c.setWidth(width);
//		c.setStyle(isiStyle);
//		c.setHeaderStyle(headerStyle);
//		return c.build();
//	}
//	
//	
//	public Window getWindow(Object o){
//		if (o instanceof Window) {
//			return ((Window) o);
//		} else {
//			if (o instanceof Component) {
//				return  getWindow(((Component) o).getParent());
//			}else{
//				return null;
//			}
//		}
//	}
//	public JFrame getFrame(Object o){
//		if (o instanceof JFrame) {
//			return ((JFrame) o);
//		} else {
//			if (o instanceof Component) {
//				return  getFrame(((Component) o).getParent());
//			}else{
//				return null;
//			}
//		}
//	}
	public LajurdPrint(JPanel panel) {
		super(panel);
	}
	
	
	
}

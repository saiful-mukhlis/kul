package com.bmb.app.print;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class AbstractJadwalReport {
	
	

	public AbstractJadwalReport(String title1, String title2, String title3, String title4) {
		super();
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;
		this.title4 = title4;
	}

//	protected Style headerStyle;
//	protected Style isiStyle;
//	protected Style titleStyle;

	protected Map params = new HashMap();
	protected JasperPrint jp;
	protected JasperReport jr;
	protected DynamicReport dr;
	protected DynamicReportBuilder frb;
	
	protected String title1;
	protected String title2;
	protected String title3;
	protected String title4;
	
	protected String ttda1="";
	protected String ttda2="";
	protected String ttda3="";
	protected String ttda4="";
	
	protected String ttdb1="";
	protected String ttdb2="";
	protected String ttdb3="";
	protected String ttdb4="";
	
	protected boolean isLandscape=true;
	
	protected FastReportBuilder reportBasic;
	
	protected List<DynamicReport> childReport=new ArrayList<DynamicReport>();
	protected List<List> datas=new ArrayList<List>();


	public DynamicReport buildReport() throws Exception {

		reportBasic = buildReportBasic();
		
		int i=0;
		for (DynamicReport r : childReport) {
			addConcatenatedReport(reportBasic, r, "data"+i);
			params.put("data"+i, datas.get(i));
			i++;
		}

		DynamicReport dynamicReport2 = buildTtd();


		addConcatenatedReport(reportBasic, dynamicReport2, "statistics3");

		params.put("statistics3", dataTtd());

		DynamicReport dr = reportBasic.build();

		return dr;
	}


	public void testReport() throws Exception {
		dr = buildReport();
		JRDataSource ds = getDataSource();
		jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);

		if (ds != null) {
			jp = JasperFillManager.fillReport(jr, params, ds);
		} else {
			jp = JasperFillManager.fillReport(jr, params);
		}
	}
	
	protected JRDataSource getDataSource() {
		Collection dummyCollection = getDummyCollection();
//		dummyCollection = SortUtils.sortCollection(dummyCollection,
//				dr.getColumns());

		JRDataSource ds = new JRBeanCollectionDataSource(dummyCollection); 

		return ds;
	}
	
	public static List getDummyCollection() {

		List<String> datas = new ArrayList<String>();
		int i = 0;

		datas.add("asdf");
		return datas;
	}




	public JasperPrint getJp() {
		return jp;
	}

	public JasperReport getJr() {
		return jr;
	}



	
	
	
	
	
	
	
	
	
	
	

	public void setChildReport(List<DynamicReport> childReport) {
		this.childReport = childReport;
	}

	public void setDatas(List<List> datas) {
		this.datas = datas;
	}

	public void setLandscape(boolean isLandscape) {
		this.isLandscape = isLandscape;
	}

	public FastReportBuilder buildReportBasic() {
		FastReportBuilder buildReportBasic = new FastReportBuilder();
		if (isLandscape) {
			buildReportBasic.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		}else{
			buildReportBasic.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		}
		buildReportBasic.setColumnsPerPage(new Integer(1));
		String gb1="";
		if (title1!=null && !title1.equalsIgnoreCase("")) {
			gb1="\\n";
		}else{
			title1="";
		}
		String gb2="";
		if (title2!=null && !title2.equalsIgnoreCase("")) {
			gb2="\\n";
		}else{
			title2="";
		}
		String gb3="";
		if (title3!=null && !title3.equalsIgnoreCase("")) {
			gb3="\\n";
		}else{
			title4="";
		}
		String gb4="";
		if (title4!=null && !title4.equalsIgnoreCase("")) {
			gb4="\\n";
		}else{
			title4="";
		}
		Style styleNul = new StyleBuilder(true)
		.setHorizontalAlign(HorizontalAlign.CENTER)
		.setVerticalAlign(VerticalAlign.MIDDLE)
		.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();
		buildReportBasic.setDefaultStyles(styleNul, styleNul, styleNul, styleNul);
		buildReportBasic.setTitle(title1+gb1+title2+gb2+title3+gb3+title4+gb4);
		buildReportBasic.setUseFullPageWidth(true);
		return buildReportBasic;
	}
	public DynamicReport buildTtd() {
		DynamicReportBuilder drb2 = new DynamicReportBuilder();
		
		if (isLandscape) {
			drb2.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		}else{
			drb2.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		}
		
		drb2.setUseFullPageWidth(true);
		drb2.setColumnsPerPage(1);
		drb2.setAllowDetailSplit(false);

		Style styleNul = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).build();

		
		drb2.addColumn(ColumnBuilder.getNew()
				.setColumnProperty("kosong", String.class.getName())
				.setTitle("").setWidth(new Integer(300)).setFixedWidth(false)
				.setStyle(styleNul).build());

		drb2.addColumn(buildColumn("kosong", "", 100, styleNul));
		drb2.addColumn(buildColumn("ket", "", 400, styleNul));
		drb2.addColumn(buildColumn("kosong", "", 900, styleNul));
		drb2.addColumn(buildColumn("ket2", "", 500, styleNul));
		drb2.addColumn(buildColumn("kosong", "", 300, styleNul));

		return drb2.build();
	}
	


	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		c.setHeaderStyle(styleHeader);
		return c.build();
	}

	public AbstractColumn buildColumn(String property, String title, int width,
			Style style) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		return c.build();
	}

	public Collection dataTtd() {
		List<TtdModel> ttd = new ArrayList<TtdModel>();
		TtdModel t = new TtdModel(ttda1, ttdb1);
		ttd.add(t);
		t = new TtdModel(ttda2, ttdb2);
		ttd.add(t);
		t = new TtdModel("", "");
		ttd.add(t);
		t = new TtdModel("", "");
		ttd.add(t);
		t = new TtdModel("", "");
		ttd.add(t);
		t = new TtdModel("", "");
		ttd.add(t);

		ttd.add(t);
		t = new TtdModel(ttda3, ttdb3);
		ttd.add(t);
		t = new TtdModel(ttda4, ttdb4);
		ttd.add(t);
		return ttd;
	}

	public void addConcatenatedReport(FastReportBuilder buildReportBasic,
			DynamicReport dynamicReport, String dataPath) {
		buildReportBasic.addConcatenatedReport(dynamicReport,
				new ClassicLayoutManager(), dataPath,
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER,
				DJConstants.DATA_SOURCE_TYPE_COLLECTION, false);
	}

	public String getTtda1() {
		return ttda1;
	}

	public void setTtda1(String ttda1) {
		this.ttda1 = ttda1;
	}

	public String getTtda2() {
		return ttda2;
	}

	public void setTtda2(String ttda2) {
		this.ttda2 = ttda2;
	}

	public String getTtda3() {
		return ttda3;
	}

	public void setTtda3(String ttda3) {
		this.ttda3 = ttda3;
	}

	public String getTtda4() {
		return ttda4;
	}

	public void setTtda4(String ttda4) {
		this.ttda4 = ttda4;
	}

	public String getTtdb1() {
		return ttdb1;
	}

	public void setTtdb1(String ttdb1) {
		this.ttdb1 = ttdb1;
	}

	public String getTtdb2() {
		return ttdb2;
	}

	public void setTtdb2(String ttdb2) {
		this.ttdb2 = ttdb2;
	}

	public String getTtdb3() {
		return ttdb3;
	}

	public void setTtdb3(String ttdb3) {
		this.ttdb3 = ttdb3;
	}

	public String getTtdb4() {
		return ttdb4;
	}

	public void setTtdb4(String ttdb4) {
		this.ttdb4 = ttdb4;
	}
	
	
}


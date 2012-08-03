package com.bmb.app.print;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sf.jasperreports.view.JasperViewer;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

import com.bmb.app.global.App;
import com.bmb.app.other.NamaBulan;
import com.bmb.app.print.adapter.ModelAdapter;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

public class PrintAbstract {
	
	
	public void buildColSpan() {	}
	public void buildColumn() {	}
	public void init(ODatabaseDocumentTx db) {	}
	public ModelAdapter createModel(){return null;	}

	protected DynamicReportBuilder reportChild;
	protected Style isiStyle;
	protected Style headerStyle;
	protected Style titleStyle;
	protected List<ODocument> model;
	protected List<ModelAdapter> data;
	protected String title;
	protected String subTitle;
	protected int tinggiHeader;
	protected boolean satuHalamanPenuh;
	protected JPanel panel;
	
	public JFrame runReport(ODatabaseDocumentTx db, AbstractJadwalReport report){
		report.setTtda1("");
		report.setTtda2("");
		report.setTtda3("");
		report.setTtda4("");
		
		report.setTtdb1("");
		report.setTtdb2("");
		report.setTtdb3("");
		report.setTtdb4("");
		
		
		try {
			report.testReport();
		} catch (Exception e1) {
			App.printErr(e1);
		}
		//JasperViewer.viewReport(report.getJp(), false);	
		JasperViewer jasperViewer = new JasperViewer(report.getJp(),
				false);
		//JFrame x=getFrame(jasperViewer);
		jasperViewer.setIconImage(App.getImage(db, "icon app 16").getImage());
		jasperViewer.setTitle(title);
		jasperViewer.setVisible(true);
		return jasperViewer;
//		jasperViewer.set
	}

	

	
	public void initReportChild(){
		reportChild = new DynamicReportBuilder();
		reportChild.setTitle(title);
		reportChild.setSubtitle(subTitle);
		//reportChild.setHeaderHeight(tinggiHeader);
		reportChild.setUseFullPageWidth(satuHalamanPenuh);
		reportChild.setColumnsPerPage(1);
		reportChild.setAllowDetailSplit(true);
		reportChild.setDefaultStyles(titleStyle, titleStyle,
				headerStyle, isiStyle);
		reportChild.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		
		reportChild.setAllowDetailSplit(true);
		//reportChild.setPageSizeAndOrientation(Page.Page_A4_Portrait());
		//reportChild.setPageSizeAndOrientation(Page.Page_Legal_Portrait());
	}
	

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	





	public void initStyles() {

		
		headerStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
				.setBorderColor(Color.BLACK)
				.setTransparency(Transparency.OPAQUE)
				.setBackgroundColor(Color.WHITE).build();

		isiStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setBorder(Border.THIN)
				.setBorderColor(Color.BLACK).setStretchWithOverflow(false)
				.setTextColor(Color.BLACK)
				// .setStretchWithOverflow(true)
				// .setBackgroundColor(Color.BLUE)
				.setTransparency(Transparency.OPAQUE)
				// .setTransparent(false)
				.build();

		titleStyle = new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setFont(Font.TIMES_NEW_ROMAN_MEDIUM).setTextColor(Color.BLACK) //VERDANA_BIG_BOLD
				// .setBackgroundColor(Color.BLUE)
				// .setTransparency(Transparency.OPAQUE)
				// .setTransparent(false)
				.build();

	}
	
	
	public AbstractColumn buildColumn(String property, String title, int width,
			Style style, Style styleHeader, Collection styleCond) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(style);
		c.addConditionalStyles(styleCond);
		c.setHeaderStyle(styleHeader);
		return c.build();
	}
	
	public AbstractColumn buildColumn(String property, String title, int width) {
		ColumnBuilder c = ColumnBuilder.getNew();
		c.setColumnProperty(property, String.class.getName());
		c.setTitle(title);
		c.setWidth(width);
		c.setStyle(isiStyle);
		c.setHeaderStyle(headerStyle);
		return c.build();
	}
	
	
	public Window getWindow(Object o){
		if (o instanceof Window) {
			return ((Window) o);
		} else {
			if (o instanceof Component) {
				return  getWindow(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	public JFrame getFrame(Object o){
		if (o instanceof JFrame) {
			return ((JFrame) o);
		} else {
			if (o instanceof Component) {
				return  getFrame(((Component) o).getParent());
			}else{
				return null;
			}
		}
	}
	public PrintAbstract(JPanel panel) {
		super();
		this.panel = panel;
	}
	
	
	
}

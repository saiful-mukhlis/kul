package org.bmb.app.view.tree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.bmb.app.view.adapter.ViewAdapter;
import org.bmb.app.view.tree.model.HakAksesModel;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class TreeHakAkses implements ViewAdapter{
	protected JPanel panel;
	protected JXTreeTable treeTable;
	
	
	
	private ODocument group=null;
	
	protected HakAksesModel model;
	
	

	
	public void setLayout(){
		panel.add(new JScrollPane(treeTable), BorderLayout.CENTER);
	}
	public void buildTable(){
		treeTable=new JXTreeTable(model);
		treeTable.setHorizontalScrollEnabled(true);
		treeTable.setColumnControlVisible(true);
		treeTable.setHighlighters(HighlighterFactory.createSimpleStriping());
		 
		treeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		treeTable.setSelectionBackground(new Color(135, 206, 250));
		treeTable.expandAll();
	}
	
	


	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void load(ODocument object) {
		if (object==null || object.field("@class").equals(App.getGrpDao().getClassName())) {
//			panel.removeAll();
//			model=new HakAksesModel(object);
			model.setGroup(object);
//			buildTable();
//			setLayout();
//			panel.validate();
//			panel.repaint();
		}
		
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		initComponent(db);
		buildTable();
		setLayout();
		
	}



	@Override
	public void modelWidgetChange(ODocument model) {
		load(model);
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	public void buildForm(ODatabaseDocumentTx db) {
		// TODO Auto-generated method stub
		
	}

	public void initComponent(ODatabaseDocumentTx db) {
		panel=new JPanel();
		panel.setLayout(new BorderLayout());
		
		//Groupp g=daoGroup.getById((long) 1);
		model=new HakAksesModel(group);
		
	}
	@Override
	public Component getPanelForm() {
		return treeTable;
	}
	@Override
	public Component getLabelTitle() {
		return treeTable.getTableHeader();
	}
}

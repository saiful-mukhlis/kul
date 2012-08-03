package org.bmb.app.view.abst.table.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.bmb.app.view.adapter.PagingAdapter;
import org.bmb.app.view.adapter.TableAdapter;
import org.bmb.app.view.adapter.TableModelAdapter;

import com.bmb.app.dao.I18nDao;
import com.bmb.app.dao.adapter.DaoAdapter;
import com.bmb.app.db.I18n;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public abstract class TableModelAbstract extends AbstractTableModel implements
		TableModelAdapter {

	protected String[] namaKolom;
	protected List<ODocument> model=new ArrayList<ODocument>();

	protected PagingAdapter paging;
	protected TableAdapter table;
	protected DaoAdapter dao;

	public TableModelAbstract(ODatabaseDocumentTx db) {
		super();
		initDao();
		initNamaKolom(db);
	}

	public int getColumnCount() {
		return namaKolom.length;
	}
	


	public int getRowCount() {
		return model.size();
	}

	public String getColumnName(int col) {
		return namaKolom[col];
	}

	public void reload(ODatabaseDocumentTx db) {
//		loadJumlahData(db);
		loadDataModel(db);
//		fireTableStructureChanged();
		fireTableDataChanged();
		setDefaultLebar(getTable().getTable());
		getTable().selected();
	}

	public void load(ODatabaseDocumentTx db) {
//		if (paging != null) {
//			paging.setCurentHalaman(0);
//		}
		if (paging != null) {
			paging.loadFirst(db);
		}
		//reload(db);

	}

	public void setPaging(PagingAdapter paging) {
		this.paging = paging;
	}

	public List getModel() {
		return model;
	}

	public TableAdapter getTable() {
		return table;
	}

	public void setTable(TableAdapter table) {
		this.table = table;
	}

	public DaoAdapter getDao() {
		return dao;
	}
	
	@Override
	public void loadDataModel(ODatabaseDocumentTx db) {
		if (paging != null) {
			int tmp=(paging.getCurentHalaman()-1)
					* paging.getJumlahPerHalaman();
			if (tmp<0) {
				tmp=0;
			}
			model = getDao().getAll(db, tmp
					,
					paging.getJumlahPerHalaman());
		} else {
			model = (List<ODocument>) getDao().getAll(db);
		}
	}

	@Override
	public void loadJumlahData(ODatabaseDocumentTx db) {
		if (paging != null) {
			paging.setJumlahData(dao.getCount(db));
			if (paging.getJumlahData() > paging.getJumlahPerHalaman()) {
				paging.getPanelPaging().setVisible(true);
			} else {
				paging.getPanelPaging().setVisible(false);
			}
		}
		
	}
	@Override
	public void addObjModel(ODocument model) {
		if (model!=null && model.field("@class").equals(dao.getClassName())) {
			this.model.add(model);
			fireTableDataChanged();
		}
	}

	public String[] getNamaKolom() {
		return namaKolom;
	}

	public void setNamaKolom(String[] namaKolom) {
		this.namaKolom = namaKolom;
	}
	
}

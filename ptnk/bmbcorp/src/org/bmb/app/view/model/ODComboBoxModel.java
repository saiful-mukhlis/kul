package org.bmb.app.view.model;

import java.io.Serializable;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.MutableComboBoxModel;

import com.orientechnologies.orient.core.record.impl.ODocument;

public class ODComboBoxModel extends AbstractListModel implements
		MutableComboBoxModel, Serializable {
	
	protected BluprintODforComboBox domain;
	private List<ODocument> domains;
	
	private String fieldName;
	private DefaultComboBoxModel name;
	
	

	@Override
	public Object getSelectedItem() {
		if (domain!=null) {
			domain.setNameField(fieldName);
		}
		return domain;
	}

	@Override
	public void setSelectedItem(Object obj) {
		if (obj==null) {
			domain=null;
			//fireContentsChanged(this, -1, -1);
		}else if (domain != null && !domain.equals(obj)
				|| domain == null && obj != null) {
			domain=(BluprintODforComboBox) obj;
			//fireContentsChanged(this, -1, -1);
		}
		
	}
	@Override
	public Object getElementAt(int i) {
		if (i >= 0 && i < getDomains().size()){
			ODocument tmp=  getDomains().get(i);
				if (domain==null) {
					domain=new BluprintODforComboBox();
					domain.setNameField(fieldName);
				}
				domain.setO(tmp);
			return domain;//getDomains().get(i).field(getFieldName());
		}else
			return null;
	}

	@Override
	public int getSize() {
		return getDomains().size();
	}

	@Override
	public void addElement(Object arg0) {
		getDomains().add((ODocument) arg0);
	}

	@Override
	public void insertElementAt(Object item, int index) {
		getDomains().set(index, (ODocument) item);
		
	}

	@Override
	public void removeElement(Object obj) {
		getDomains().remove(obj);
	}

	@Override
	public void removeElementAt(int i) {
		getDomains().remove(i);
	}

	public List<ODocument> getDomains() {
		return domains;
	}

	public void setDomains(List<ODocument> domains) {
		this.domains = domains;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


}

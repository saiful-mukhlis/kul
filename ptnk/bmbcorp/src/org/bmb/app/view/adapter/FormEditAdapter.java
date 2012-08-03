package org.bmb.app.view.adapter;

import java.awt.Component;


public interface FormEditAdapter extends WidgetAdapter{
	public void addWidgetModel(WidgetAdapter e);
	public Component getPanelForm();
	public Component getLabelTitle();
}

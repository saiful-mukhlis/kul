package org.bmb.app.view.adapter;

import java.awt.Component;

public interface FormAdapter extends WidgetAdapter{
	public void aksiReset();
	public void addWidgetModel(WidgetAdapter table);
	public Component getPanelForm();
	public Component getLabelTitle();
}

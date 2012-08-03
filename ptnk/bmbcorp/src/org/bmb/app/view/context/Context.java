package org.bmb.app.view.context;
import java.awt.Component;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.bmb.app.view.adapter.WindowAdapter;
import org.bmb.app.view.component.ImagePanel;
import org.bmb.app.view.impl.Komponent;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.yasaf.plaf.view.MapViewContext;
import org.noos.xing.yasaf.view.ViewContextChangeListener;
import org.noos.xing.yasaf.view.event.ViewContextChangeEvent;

import com.bmb.app.view.master.KandangMaster;
import com.bmb.app.view.master.UsrMaster;

public class Context  extends MapViewContext {

	public enum ActionKey {
		NEST_TOOLMANAGER,
	}



	protected ToolWindowManager toolWindowManager;
	
	protected WindowAdapter window;

	

	public Context(final WindowAdapter window, ToolWindowManager toolWindowManager,
			final Component parentComponent) {
		this.window=window;
		this.toolWindowManager=toolWindowManager;
		List<Komponent> komponents=window.getKomponents();
		for (Komponent komponent : komponents) {
			addViewContextChangeListener(komponent.getMaster().getClass()	, komponent.getAdd());
		}
//		addViewContextChangeListener(UsrMaster.class, window.getUsrAca());
//		addViewContextChangeListener(KandangMaster.class, window.getKandangAca());
		addViewContextChangeListener(ImagePanel.class, window.getWelcomeAca());
		


	}

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value);
	}
}

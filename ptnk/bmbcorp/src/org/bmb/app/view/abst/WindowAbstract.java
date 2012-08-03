package org.bmb.app.view.abst;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.bmb.app.view.adapter.MenuAdapter;
import org.bmb.app.view.adapter.ToolbarAdapter;
import org.bmb.app.view.adapter.WindowAdapter;
import org.bmb.app.view.component.BosOne;
import org.bmb.app.view.listener.HakAksesListener;
import org.noos.xing.mydoggy.Content;
import org.noos.xing.mydoggy.ContentManagerListener;
import org.noos.xing.mydoggy.ToolWindow;
import org.noos.xing.mydoggy.ToolWindowManager;
import org.noos.xing.mydoggy.event.ContentManagerEvent;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.mydoggy.plaf.MyDoggyToolWindowManager;
import org.noos.xing.mydoggy.plaf.ui.cmp.ExtendedTableLayout;
import org.noos.xing.yasaf.view.ViewContext;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public abstract class WindowAbstract implements WindowAdapter{
	protected ToolbarAdapter toolbar;
	protected MenuAdapter menu;
	protected ToolWindowManager toolWindowManager;
	protected ViewContext myDoggySetContext;
	protected JFrame frame;
	
	protected JProgressBar progressBar;
	protected boolean useBar=false;
	protected BosOne bosOne;
	
	protected List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	
	@Override
	public void build(ODatabaseDocumentTx db) {
		init(db);
		
	}
	public void init(ODatabaseDocumentTx db){
		setValueBar(75);
		seta(db);
		setValueBar(80);
		initFrame(db);
		initToolWindowManager();
		buildMaster(db);
		initContext(db);
		buildActions(db);
		initMenu(db);
		initToolbar(db);
		
		setLocationFrame();
		setAvailableWindow();
		setValueBar(90);
		manageWelcome();
		showWelcome();
		setValueBar(99);
		showFrame();
	}
	public void manageWelcome() {
		toolWindowManager.getContentManager().addContentManagerListener(
				new ContentManagerListener() {

					@Override
					public void contentSelected(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void contentRemoved(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub
						if (toolWindowManager.getContentManager()
								.getContentCount() == 0) {
							showWelcome();
						}
					}

					@Override
					public void contentAdded(ContentManagerEvent arg0) {
						// TODO Auto-generated method stub
						if (toolWindowManager.getContentManager()
								.getContentCount() > 1) {
							try {
								Content c = toolWindowManager.getContentManager()
								.getContentByComponent(getComponentWelcome());
						toolWindowManager.getContentManager()
								.removeContent(c);
							} catch (Exception e) {
								// TODO: handle exception
							}
							
						}

					}
				});
	}
	public void setAvailableWindow() {
		for (ToolWindow window : toolWindowManager.getToolWindows()) {
			window.setAvailable(true);
		}
	}
	public void initToolWindowManager() {
		JPanel p = new JPanel();
		p.setLayout(new ExtendedTableLayout(new double[][] { { 0, -1, 0 },
				{ 0, -1, 0 } }));

		final MyDoggyToolWindowManager myDoggyToolWindowManager = new MyDoggyToolWindowManager();
		toolWindowManager = myDoggyToolWindowManager;

		p.add(myDoggyToolWindowManager, "1,1,");
		frame.add(p, BorderLayout.CENTER);

	}
	
	
	public void setValueBar(int value){
		if (useBar) {
			progressBar.setValue(value);
		}
	}
	
	public void initFrame(ODatabaseDocumentTx db) {
		frame = new JFrame(App.getT(db, "Application Peternakan"+" "+bosOne.getLoc()));
		frame.setIconImage(App.getImage(db, "icon app 16").getImage());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setLocationFrame() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenResolution = toolkit.getScreenResolution();
		float zoom = screenResolution / 72.0F;

		int height = (int) (550.0F * zoom);
		if (height > screenSize.getHeight()) {
			height = (int) screenSize.getHeight();
		}
		int width = (int) (750.0F * zoom);
		if (width > screenSize.getWidth()) {
			width = (int) screenSize.getWidth();
		}

		Dimension dimension = new Dimension(width, height);
		frame.setSize(dimension);
		frame.setLocation((screenSize.width - width) / 2,
				(screenSize.height - height) / 2);
	}
	public void showFrame() {
		frame.setVisible(true);
	}
	
	public AddContentAction factoryContentAction(ODatabaseDocumentTx db, String title, Icon icon,
			Component component, int shortCut) {
		return new AddContentAction(toolWindowManager,
				App.getT(db, title), App.getT(db, title),
				icon, component, App.getT(db, title), shortCut);
	}
	public ToolbarAdapter getToolbar() {
		return toolbar;
	}
	public void setToolbar(ToolbarAdapter toolbar) {
		this.toolbar = toolbar;
	}
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
}

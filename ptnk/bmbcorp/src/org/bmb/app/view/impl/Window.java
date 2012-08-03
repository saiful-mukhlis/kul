package org.bmb.app.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bmb.app.view.abst.WindowAbstract;
import org.bmb.app.view.component.BosOne;
import org.bmb.app.view.component.ImagePanel;
import org.bmb.app.view.context.Context;
import org.bmb.app.view.listener.HakAksesListener;
import org.bmb.app.view.login.LoginDialog;
import org.noos.xing.mydoggy.mydoggyset.action.AddContentAction;
import org.noos.xing.yasaf.plaf.action.ViewContextAction;

import com.bmb.app.config.DataUser;
import com.bmb.app.global.App;
import com.bmb.app.view.master.FormatMaster;
import com.bmb.app.view.master.GrpMaster;
import com.bmb.app.view.master.I18nMaster;
import com.bmb.app.view.master.KandangMaster;
import com.bmb.app.view.master.KandangallMaster;
import com.bmb.app.view.master.KandangdInputPakanMaster;
import com.bmb.app.view.master.KandangdMaster;
import com.bmb.app.view.master.LajurdMaster;
import com.bmb.app.view.master.PelangganMaster;
import com.bmb.app.view.master.PenjualanFormMaster;
import com.bmb.app.view.master.PenjualanMaster;
import com.bmb.app.view.master.PiutangMaster;
import com.bmb.app.view.master.ProductdMaster;
import com.bmb.app.view.master.ProduksiFormMaster;
import com.bmb.app.view.master.UsrMaster;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Window extends WindowAbstract{
	
	private List<Komponent> komponents=new ArrayList<Komponent>();
	private List<HakAksesListener> cangeHakAkses = new ArrayList<HakAksesListener>();
	
	
//	protected UsrMaster usrMaster;
//	protected Component usrComponent;
//	
//	protected AddContentAction usrAca;
//	protected ViewContextAction usrVca;
//	
	protected AddContentAction windowAca;
	protected ViewContextAction windowVca;
//	
//	protected KandangMaster kandangMaster;
//	protected Component kandangComponent;
//	
//	protected AddContentAction kandangAca;
//	protected ViewContextAction kandangVca;
	
	protected Component welcomeComponent;
	
	@Override
	public void buildMaster(ODatabaseDocumentTx db) {
		getKomponents().add(new Komponent(db, toolWindowManager, new UsrMaster(), "Master Pegawai", 'P', "icon pegawai"));
		getKomponents().add(new Komponent(db, toolWindowManager, new GrpMaster(), "Hak Hakses", 'H', "icon hak akses"));
		getKomponents().add(new Komponent(db, toolWindowManager, new KandangMaster(), "Master Kandang", 'K', "icon kandang"));
		getKomponents().add(new Komponent(db, toolWindowManager, new PelangganMaster(), "Master Pelanggan", 'E', "icon customer"));
		
		getKomponents().add(new Komponent(db, toolWindowManager, new ProduksiFormMaster(), "Input Produksi", 'I', "icon produksi"));
		getKomponents().add(new Komponent(db, toolWindowManager, new PenjualanFormMaster(), "Input Penjualan", 'J', "icon penjualan"));
		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdInputPakanMaster(), "Pakan & Umur", 'U', "icon pakan"));
		
		
		getKomponents().add(new Komponent(db, toolWindowManager, new FormatMaster(), "Format Laporan", 'F', "icon format"));
		getKomponents().add(new Komponent(db, toolWindowManager, new LajurdMaster(), "Produksi Lajur", 'O', "icon lajur"));
		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdMaster(), "Produksi Kandang", 'D', "icon kandang"));
		getKomponents().add(new Komponent(db, toolWindowManager, new KandangallMaster(), "Total Produksi", 'T', "icon total"));
		getKomponents().add(new Komponent(db, toolWindowManager, new PenjualanMaster(), " Laporan Penjualan ", 'L', "icon penjualan"));
		getKomponents().add(new Komponent(db, toolWindowManager, new ProductdMaster(), "Laporan Stock", 'S', "icon stock"));
		getKomponents().add(new Komponent(db, toolWindowManager, new PiutangMaster(), " Laporan Piutang ", 'G', "icon piutang"));
		
		
		
		
//		getKomponents().add(new Komponent(db, toolWindowManager, new I18nMaster(), "Bahasa", 'B', "icon pegawai"));
//		getKomponents().add(new Komponent(db, toolWindowManager, new KandangdMaster(), "Laporan Kandang", 'L', "icon pegawai"));
		
		
//		usrMaster=new UsrMaster();
//		usrMaster.build(db);
		
//		kandangMaster=new KandangMaster();
//		kandangMaster.build(db);
		
		for (Komponent komponent : getKomponents()) {
			cangeHakAkses.add(komponent.getHakAksesListener());
		}
		
//		cangeHakAkses.add(usrMaster);
//		cangeHakAkses.add(kandangMaster);
//		
//		usrComponent=usrMaster.getPanel();
//		kandangComponent=kandangMaster.getPanel();
		
		JPanel panel = new ImagePanel(
				new FlowLayout(FlowLayout.CENTER, 50, 180));
		welcomeComponent = panel;
		
//		usrAca = factoryContentAction(db, "Pegawai", App.getIcon(db, "icon pegawai 16"),
//				usrComponent, (int) 'P');
//		
//		kandangAca = factoryContentAction(db, "Kandang", App.getIcon(db, "icon kandang 16"),
//				kandangComponent, (int) 'K');
		
		windowAca = factoryContentAction(db, "Welcome", App.getIcon(db, "icon app 16"),
				welcomeComponent, (int) 'W');
		
	}


	@Override
	public void buildActions(ODatabaseDocumentTx db) {
		for (Komponent komponent : getKomponents()) {
			komponent.setView(db, komponent.getMaster().getClass(), myDoggySetContext);
		}
		
		windowVca = new ViewContextAction(
				App.getT(db, "Welcome"), App.getIcon(db, "icon app 16"),
				myDoggySetContext, ImagePanel.class);
		
	}
	
	@Override
	public void initContext(ODatabaseDocumentTx db) {
		myDoggySetContext = new Context(this, toolWindowManager, frame);
	}
	

	@Override
	public void initToolbar(ODatabaseDocumentTx db) {
		setToolbar(new Toolbar());
		getToolbar().setWindow(this);
		getToolbar().build(db);
		frame.getContentPane().add(getToolbar().getPanel(), BorderLayout.NORTH);
		cangeHakAkses.add(toolbar);
	}

	@Override
	public void initMenu(ODatabaseDocumentTx db) {
		menu=new Menu();
		menu.setWindow(this);
		menu.build(db);
		frame.setJMenuBar(menu.getMenu());
	}


	@Override
	public void showWelcome() {
		myDoggySetContext.put(ImagePanel.class, null);
		
	}

	@Override
	public Component getComponentWelcome() {
		return welcomeComponent;
	}

	@Override
	public void seta(ODatabaseDocumentTx db) {
		bosOne=new BosOne();
		bosOne.seta(db);
		
	}



	@Override
	public void load(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelWidgetChange(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}


//	public AddContentAction getUsrAca() {
//		return usrAca;
//	}
//	
//	public AddContentAction getKandangAca() {
//		return kandangAca;
//	}
	
	public AddContentAction getWelcomeAca() {
		return windowAca;
	}


	@Override
	public void showToolbar() {
		if (toolbar.getPanel().isVisible()) {
			toolbar.getPanel().setVisible(false);
		}else{
			toolbar.getPanel().setVisible(true);
		}
		
	}


	public List<Komponent> getKomponents() {
		return komponents;
	}


	public void setKomponents(List<Komponent> komponents) {
		this.komponents = komponents;
	}


	public void login() {
		
		if (DataUser.getUsr() == null) {
			LoginDialog form = new LoginDialog();
			ODatabaseDocumentTx db = App.getDbd();
		    ODatabaseRecordThreadLocal. INSTANCE.set(db);
			form.buildComponent(db);
			db.close();
			JDialog d = new JDialog(frame);
			d.setModalityType(ModalityType.APPLICATION_MODAL);
			d.getContentPane().add(form.getPanel());
			d.pack();
			setCenterDialog(d);
			d.setVisible(true);
		} else {
			//logout
			DataUser.setUsr(null);
			DataUser.setGrp(null);
			closeAllWindow();
			
		}
		DataUser.setAkses();
		for (HakAksesListener hakAksesListener : cangeHakAkses) {
			hakAksesListener.changeHakAkses();
		}
//		if (DataUser.usr != null) {
//			// open default like welcome
//
//		}
	}
	
	public void closeAllWindow() {
		toolWindowManager.getContentManager().removeAllContents();

	}
	
	public void setCenterDialog(JDialog d) {
		d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - d
				.getPreferredSize().width) / 2, (Toolkit.getDefaultToolkit()
				.getScreenSize().height - d.getPreferredSize().height) / 2);

	}

}

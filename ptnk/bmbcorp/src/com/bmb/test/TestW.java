package com.bmb.test;


import javax.swing.UIManager;

import org.bmb.app.view.impl.Window;

import com.bmb.app.global.App;
import com.bmb.app.view.screen.SplashScreen;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class TestW {
public static void main(String[] args) {
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}
	try {
		
		SplashScreen splash = new SplashScreen();
	    splash.setVisible(true);
	    splash.getBar().setValue(30);
		Window w=new  Window();
		splash.getBar().setValue(40);
		w.setProgressBar(splash.getBar());
		ODatabaseDocumentTx db = App.getDbd();
	    ODatabaseRecordThreadLocal. INSTANCE.set(db);
	    splash.getBar().setValue(70);
		w.build(db);
	    
		db.close();
		splash.setVisible(false);
	    splash.dispose();
		
	} catch (Exception e) {
		App.showErrSementara(e.getMessage());
		App.printErr(e);
		System.exit(0);
	}
	
}
}

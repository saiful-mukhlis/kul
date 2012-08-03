package com.bmb.test;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.bmb.app.view.impl.Window;

import com.bmb.app.dao.PelangganDao;
import com.bmb.app.global.App;
import com.bmb.app.managedb.StartDb;
import com.bmb.app.view.table.PelangganTable;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class Test2 {
public static void main(String[] args) {
//	System.out.println("asdasd");
//	PelangganTable t=new PelangganTable();
//	ODatabaseDocumentTx db=App.getDbd();
//	t.build(db);
//	db.close();
//	JFrame f=new JFrame("xxx");
//	f.getContentPane().setLayout(new BorderLayout());
//	f.getContentPane().add(t.getPanel(), BorderLayout.CENTER);
//f.addWindowListener(new WindowListener() {
//		
//		@Override
//		public void windowOpened(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void windowIconified(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void windowDeiconified(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void windowDeactivated(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void windowClosing(WindowEvent e) {
//			// TODO Auto-generated method stub
//			System.exit(0);
//		}
//		
//		@Override
//		public void windowClosed(WindowEvent e) {
//			// TODO Auto-generated method stub
//			System.exit(0);
//		}
//		
//		@Override
//		public void windowActivated(WindowEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
//	f.setVisible(true);
	System.out.println("asdasd");
	//tes();
	tes2();
}

public static void tes3(){
	ODatabaseDocumentTx db=App.getDbd();
	PelangganDao dao=App.getPelangganDao();
	dao.deleteAll(db);
	db.close();
	//System.exit(0);
}
public static void tes2(){
	ODatabaseDocumentTx db=App.getDbd();
	PelangganDao dao=App.getPelangganDao();
	dao.printAll(db);
	db.close();
	System.exit(0);
}
public static void tes(){
	ODatabaseDocumentTx db=App.getDbd();
	PelangganDao dao=App.getPelangganDao();
	ODocument d=dao.factoryModel("AUTO", "Toko Sip", "Saya", "234234", "asdfsadf");
	dao.save(db, d);
	db.close();
	//System.exit(0);
}
}

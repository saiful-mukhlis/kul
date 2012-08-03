package com.bmb.app.other;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import com.bmb.app.global.App;
import com.bmb.app.view.adapter.LoadingAdapter;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;



public class DialogLoading extends JDialog{

	private LoadingAdapter proses;
	public DialogLoading(ODatabaseDocumentTx db, LoadingAdapter task) {
		proses=task;
		initComponent(db);
	}
	public DialogLoading(ODatabaseDocumentTx db, Window w,  LoadingAdapter task) {
		super(w);
		proses=task;
		initComponent(db);
	}

	public void initComponent(ODatabaseDocumentTx db){
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //DO_NOTHING_ON_CLOSE
		setIconImage(App.getImage(db, "icon app 16").getImage());
		final JProgressBar b=new JProgressBar();
		b.setBackground(SystemColor.activeCaption);
		b.setValue(44);
		JLabel l = new JLabel("Loading.........");
		JPanel p2=new JPanel(new BorderLayout());
		JPanel p=new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 30));
		p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 50, 5));
		JLabel l2 = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
		setLayout(new BorderLayout());
		//p.add(l2, BorderLayout.NORTH);
		p.add(l,BorderLayout.CENTER);
		p2.add(l2,BorderLayout.CENTER);
		p.add(b,BorderLayout.SOUTH);
		add(p , BorderLayout.EAST);
		add(p2 , BorderLayout.WEST);
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				proses.runTask();
				setVisible(false);
				dispose();
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setSize(300, 200);
		 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screen.width - 300) / 2;
		    int y = (screen.height - 200) / 2;
		    setBounds(x, y, 300, 200);
		pack();
	}
}
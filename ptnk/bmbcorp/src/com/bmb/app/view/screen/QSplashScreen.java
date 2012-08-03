package com.bmb.app.view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.ImageIcon;
public class QSplashScreen extends JWindow  {
	protected JProgressBar bar ;
	protected int width;
	protected int height;
	protected String pathImageResource; 
	protected JLabel imageLabel;
	public QSplashScreen() {
		super();
	}

	protected void initComponent(){
		width = 400;
		height = 249;
		pathImageResource="/image/screen.jpg";

	}
	
	protected void buildComponent(){
		initComponent();
		bar = new JProgressBar();
		bar.setBackground(SystemColor.activeCaption);
		JPanel content = (JPanel) getContentPane();
	    content.setBackground(Color.white);
	    imageLabel = new JLabel(new ImageIcon(QSplashScreen.class.getResource(pathImageResource)));
	    setLayout();
	    setCenterWindow(this, width, height);
	}
	
	protected void setLayout(){
		JPanel content = (JPanel) getContentPane();
		content.add(imageLabel, BorderLayout.CENTER);
	    getContentPane().add(bar, BorderLayout.SOUTH);
	}
	
	public void setCenterWindow(JWindow window, int width, int height){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    window.setBounds(x, y, width, height);
	}

	public JProgressBar getBar() {
		return bar;
	}
}


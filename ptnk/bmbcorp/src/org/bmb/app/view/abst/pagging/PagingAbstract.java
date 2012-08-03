package org.bmb.app.view.abst.pagging;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bmb.app.view.adapter.PagingAdapter;
import org.bmb.app.view.adapter.TableModelAdapter;

import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;


public abstract class PagingAbstract implements PagingAdapter {

	protected TableModelAdapter tableModel;

	protected JPanel panelPaging;
	protected JButton reloadButton;
	protected JButton firstButton;
	protected JButton backButton;
	protected JTextField curentTextFild;
	protected JLabel pageLabel;
	protected JButton nextButton;
	protected JButton endButton;
	protected JLabel jumlahRowLabel;

	protected long jumlahData=(long) 0;
	protected int curentHalaman=1;
	protected int jumlahHalaman=1;
	protected int jumlahPerHalaman=50;

	@Override
	public void loadFirst(ODatabaseDocumentTx db) {
		resetJumlahHalaman(db);
//		curentHalaman=jumlahHalaman;
//		reset();
		last(db);
		reset();
	}

	public void resetJumlahHalaman(ODatabaseDocumentTx db) {
		tableModel.loadJumlahData(db);
		jumlahHalaman = (int) (jumlahData / jumlahPerHalaman);
		if (jumlahData % jumlahPerHalaman != 0) {
			jumlahHalaman++;
		}
		
		reset();
	}

	public void reset() {
		curentTextFild.setText(curentHalaman + "");
		pageLabel.setText("  from " + (jumlahHalaman) + " page  ");
		jumlahRowLabel.setText(" Total " + jumlahData + " row");
	}
	

	public void setFistPaging() {
		reset();
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (jumlahHalaman <= 1) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
			curentTextFild.setEditable(false);
		} else {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			curentTextFild.setEditable(true);
		}

	}

	public void next(ODatabaseDocumentTx db) {
		curentHalaman++;
		if (curentHalaman == jumlahHalaman) {
			nextButton.setEnabled(false);
			endButton.setEnabled(false);
		}
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload(db);
	}

	public void prev(ODatabaseDocumentTx db) {
		curentHalaman--;
		if (curentHalaman == 1) {
			backButton.setEnabled(false);
			firstButton.setEnabled(false);
		}
		nextButton.setEnabled(true);
		endButton.setEnabled(true);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload(db);
	}

	public void first(ODatabaseDocumentTx db) {
		curentHalaman = 1;
		backButton.setEnabled(false);
		firstButton.setEnabled(false);
		if (jumlahHalaman > 1) {
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
		}
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload(db);
	}

	public void last(ODatabaseDocumentTx db) {
		curentHalaman = jumlahHalaman ;
		backButton.setEnabled(true);
		firstButton.setEnabled(true);
		nextButton.setEnabled(false);
		endButton.setEnabled(false);
		curentTextFild.setText(curentHalaman + "");
		tableModel.reload(db);
	}

	public void go(ODatabaseDocumentTx db) {
		int tmp = curentHalaman;
		try {
			curentHalaman = Integer.parseInt(curentTextFild.getText());
		} catch (Exception e) {
		}
		if (curentHalaman == 1 || curentHalaman > (jumlahHalaman)) {
			curentHalaman = tmp;
		} else {
			//curentHalaman--;
		}
		if (curentHalaman == 1) {
			first(db);
		} else if (curentHalaman == jumlahHalaman ) {
			last(db);
		} else {
			backButton.setEnabled(true);
			firstButton.setEnabled(true);
			nextButton.setEnabled(true);
			endButton.setEnabled(true);
			tableModel.reload(db);
		}

	}
	
	public void initComponent() {

		panelPaging = new JPanel();
		reloadButton = new JButton();
		firstButton = new JButton();
		backButton = new JButton();
		curentTextFild = new JTextField(5);
		pageLabel = new JLabel();
		nextButton = new JButton();
		endButton = new JButton();
		jumlahRowLabel = new JLabel();


		reloadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				tableModel.reload(db);
				db.close();
			}
		});
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				next(db);
				db.close();
			}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				prev(db);
				db.close();
			}
		});
		firstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				first(db);
				db.close();
			}
		});
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				last(db);
				db.close();
			}
		});
		curentTextFild.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ODatabaseDocumentTx db=App.getDbd();
				go(db);
				db.close();
			}
		});
	}

	public Long getJumlahData() {
		return jumlahData;
	}

	public void setJumlahData(long jumlahData) {
		this.jumlahData = jumlahData;
	}

	public int getJumlahHalaman() {
		return jumlahHalaman;
	}

	public void setJumlahHalaman(int jumlahHalaman) {
		this.jumlahHalaman = jumlahHalaman;
	}

	public int getJumlahPerHalaman() {
		return jumlahPerHalaman;
	}

	public void setJumlahPerHalaman(int jumlahPerHalaman) {
		this.jumlahPerHalaman = jumlahPerHalaman;
	}

	public int getCurentHalaman() {
		if (curentHalaman<=0) {
			curentHalaman=1;
		}
		return curentHalaman;
	}

	public void setCurentHalaman(int curentHalaman) {
		this.curentHalaman = curentHalaman;
	}

	public JPanel getPanelPaging() {
		return panelPaging;
	}
	
	

}

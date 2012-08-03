package org.bmb.app.view.def;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bmb.app.view.abst.pagging.PagingAbstract;
import org.bmb.app.view.adapter.TableModelAdapter;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class PagingDefault extends PagingAbstract{
	@Override
	public void initComponent() {
		super.initComponent();
		setPanelPaging(panelPaging, reloadButton, firstButton, backButton,
				curentTextFild, pageLabel, nextButton, endButton,
				jumlahRowLabel);
	}

	public PagingDefault(TableModelAdapter tableModel) {
		super();
		super.tableModel=tableModel;
		super.tableModel.setPaging(this);
		initComponent();
	}
	public void setPanelPaging(JPanel panelPaging, JButton reloadButton,
			JButton firstButton, JButton backButton,
			JTextField curentTextFild, JLabel pageLabel, JButton nextButton,
			JButton endButton, JLabel jumlahRowLabel) {
		FormLayout layout = new FormLayout(
				"p:g, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, 4dlu, p, p:g");
		layout.setColumnGroups(new int[][] { { 3, 5, 7, 13, 15 } });
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		// builder.setDefaultDialogBorder();

		reloadButton.setIcon(getIconReload());
		reloadButton.setContentAreaFilled(false);
		reloadButton.setBorder(null);
		firstButton.setIcon(getIconFirst());
		firstButton.setContentAreaFilled(false);
		firstButton.setBorder(null);
		backButton.setIcon(getIconBack());
		backButton.setContentAreaFilled(false);
		backButton.setBorder(null);
		nextButton.setIcon(getIconNext());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorder(null);
		endButton.setIcon(getIconEnd());
		endButton.setContentAreaFilled(false);
		endButton.setBorder(null);

		builder.nextColumn();
		builder.nextColumn();
		builder.append(reloadButton);
		builder.append(firstButton);
		builder.append(backButton);
		builder.append(curentTextFild);
		builder.append(pageLabel);
		builder.append(nextButton);
		builder.append(endButton);
		builder.append(jumlahRowLabel);
		JPanel p = builder.getPanel();
		panelPaging.setLayout(new BorderLayout(0, 0));
		panelPaging.add(p, BorderLayout.CENTER);
	}
	/**
	 * @return {@link Icon} icon image reload
	 */
	public Icon getIconReload() {
		return new ImageIcon(
				getClass().getResource("/image/refresh-16.png"));
	}

	/**
	 * @return {@link Icon} icon image first
	 */
	public Icon getIconFirst() {
		return new ImageIcon(
				getClass().getResource("/image/first-16.png"));
	}

	/**
	 * @return {@link Icon} icon image back
	 */
	public Icon getIconBack() {
		return new ImageIcon(
				getClass().getResource("/image/prev-16.png"));
	}

	/**
	 * @return {@link Icon} icon image next
	 */
	public Icon getIconNext() {
		return new ImageIcon(
				getClass().getResource("/image/next-16.png"));
	}

	/**
	 * @return {@link Icon} icon image next
	 */
	public Icon getIconEnd() {
		return new ImageIcon(
				getClass().getResource("/image/last-16.png"));
	}

}

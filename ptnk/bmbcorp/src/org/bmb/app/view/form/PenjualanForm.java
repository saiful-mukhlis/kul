package org.bmb.app.view.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.bmb.app.view.abst.form.FormAbstract;
import org.bmb.app.view.adapter.FormAdapter;
import org.bmb.app.view.adapter.WidgetAdapter;
import org.bmb.app.view.builder.FormBuilder;
import org.bmb.app.view.component.DatePicker;
import org.bmb.app.view.component.FormattedTextField;
import org.bmb.app.view.component.TextField;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.PelangganDao;
import com.bmb.app.dao.PenjualanDao;
import com.bmb.app.dao.ProductDao;
import com.bmb.app.global.App;
import com.jgoodies.forms.layout.FormLayout;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class PenjualanForm extends FormAbstract implements FormAdapter {
	private TextField code;
	private DatePicker tgl;
	private TextField pelanggan;
	private FormattedTextField jml;
	private FormattedTextField harga;
	private FormattedTextField total1;
	private FormattedTextField diskonp;
	private FormattedTextField diskon;
	private FormattedTextField total;
	private TextField bayar;
	private FormattedTextField k;
	private FormattedTextField kembali;
	
	
	
	
    
	
	private JXList custList;
	private DefaultListModel listModel;
	
	private JPopupMenu popup;
	private ODocument cust;
	
	private Font f2;
	
	
	
	protected JButton save;
	protected JButton reset;
	
	
	private String codea;
	private Date tgla;
	private ODocument pelanggana;
	private ODocument producta;
	private double hargaa;
	private double jmla;
	private double total1a;
	private double diskona;
	private double diskonpa;
	private double totaldiskona;
	private double totala;
	private double bayara;
	private double da;
	private double ka;
	private String notea;
	public boolean validate(ODatabaseDocumentTx db){
		if (!(code.getText().equalsIgnoreCase("AUTO") || code.getText().trim().equalsIgnoreCase("")) ) {
			long tmp=App.getPenjualanDao().getCountByColumn(db, PenjualanDao.code, code.getText());
			if (tmp>0) {
				codea="";
				App.showErrorDataSudahAda(db, PenjualanDao.fcode);
				return false;
			}
		}
		codea=code.getText();
		tgla=tgl.getDate();
		if (tgla==null) {
			tgl.setDate(new Date());
			tgla=new Date();
			App.showErrorFieldEmpty(db, PenjualanDao.ftgl);
			return false;
		}
		
		if (cust==null) {
			pelanggan.setText("");
			App.showErrorFieldEmpty(db, PenjualanDao.fpelanggan);
			return false;
		}else{
			pelanggana=cust;
		}
		
		try {
			hargaa =Double.parseDouble(harga.getValue()+"");
			if (hargaa<0) {
				return false;
			}
		} catch (Exception e) {
			harga.setText("1");
			try {
				harga.commitEdit();
			} catch (ParseException e1) {
			}
			App.printErr(e);
			return false;
		}
		try {
			jmla=Double.parseDouble(jml.getValue()+"");
			if (jmla<=0) {
				App.showErrorJumlahTidakFalid();
			}
		} catch (Exception e) {
			jml.setText("");
			App.showErrorFieldEmpty(db, PenjualanDao.fjml);
			return false;
		}
		
		
		try {
			diskon.commitEdit();
			diskona=Double.parseDouble(diskon.getValue()+"");
			if (diskona<0) {
				App.showErrorNotValid(db, PenjualanDao.fdiskon);
			}
		} catch (Exception e) {
			App.showErrorFieldEmpty(db, PenjualanDao.fdiskon);
			App.printErr(e);
			return false;
		}
		
		try {
			diskonp.commitEdit();
			diskonpa=Double.parseDouble(diskonp.getValue()+"");
			if (diskonpa<0) {
				App.showErrorNotValid(db, PenjualanDao.fdiskonp);
			}
		} catch (Exception e) {
			App.showErrorFieldEmpty(db, PenjualanDao.fdiskonp);
			App.printErr(e);
			return false;
		}
		
		
		
		try {
			if (bayar.getText().equals("")) {
				App.showErrorFieldEmpty(db, PenjualanDao.fbayar);
				return false;
			}
			String stmp=bayar.getText().replaceAll("\\,", "");
			if (stmp==null || stmp.equalsIgnoreCase("")) {
				App.showErrorFieldEmpty(db, PenjualanDao.fbayar);
				return false;
			}
			bayara=Double.parseDouble(stmp);
			if (bayara<0) {
				App.showErrorNotValid(db, PenjualanDao.fbayar);
			}
		} catch (Exception e) {
			App.showErrorFieldEmpty(db, PenjualanDao.fbayar);
			App.printErr(e);
			return false;
		}
		
		try {
			k.commitEdit();
			ka=Double.parseDouble(k.getValue()+"");
		} catch (Exception e) {
			App.printErr(e);
			return false;
		}
		
		try {
			total1.commitEdit();
			total1a=Double.parseDouble(total1.getValue()+"");
		} catch (Exception e) {
			App.printErr(e);
			return false;
		}
		
		try {
			total.commitEdit();
			totala=Double.parseDouble(total.getValue()+"");
		} catch (Exception e) {
			App.printErr(e);
			return false;
		}
		
		if (ka>0) {
			da=bayara;
			notea=App.getT(db, "Hutang");
		}else{
			da=totala;
			notea=App.getT(db, "Lunas");
		}
		
		producta=DataUser.getProduct();
		
		return true;
	}
	
	
	@Override
	public void init(ODatabaseDocumentTx db) {
		super.init(db);
		initComponent(db);
	}

	@Override
	public void build(ODatabaseDocumentTx db) {
		super.build(db);
		buildForm(db);
	}

	public void initComponent(ODatabaseDocumentTx db){
//		amountFormat = NumberFormat.getNumberInstance();
//
//        percentFormat = NumberFormat.getNumberInstance();
//        percentFormat.setMinimumFractionDigits(2);

		
		
		
		listModel=new DefaultListModel();
		custList=new JXList(listModel);
		custList.setHighlighters(HighlighterFactory
				.createAlternateStriping());
		popup = new JPopupMenu();
		popup.add(new JScrollPane(custList), BorderLayout.CENTER);
		
		code = new TextField();
		tgl=new DatePicker();
		tgl.setFormats(App.dateFormat);
		pelanggan=new TextField();
		jml=new FormattedTextField(App.paymentFormat);
		harga=new FormattedTextField(App.paymentFormat);
		harga.setEditable(false);
		harga.setBackground(App.whiteSmoke);
		total1=new FormattedTextField(App.paymentFormat);
		total1.setEditable(false);
		total1.setBackground(App.whiteSmoke);
		diskon=new FormattedTextField(App.paymentFormat);
		diskonp=new FormattedTextField(App.paymentFormat2);
		total=new FormattedTextField(App.paymentFormat);
		total.setEditable(false);
		total.setBackground(App.whiteSmoke);
		bayar=new TextField();
		k=new  FormattedTextField(App.paymentFormat);
		k.setEditable(false);
		k.setBackground(App.whiteSmoke);
		kembali=new FormattedTextField(App.paymentFormat);
		kembali.setEditable(false);
		kembali.setBackground(App.whiteSmoke);
		
		Font f=UIManager.getFont("TextField.font");
		f2=f.deriveFont((float) 24.0);
		total1.setFont(f2);
		total.setFont(f2);
		kembali.setFont(f2);
		harga.setFont(f2);
		bayar.setFont(f2);
		jml.setFont(f2);
		k.setFont(f2);
		
		harga.setHorizontalAlignment(SwingConstants.RIGHT);
		total1.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		diskon.setHorizontalAlignment(SwingConstants.RIGHT);
		bayar.setHorizontalAlignment(SwingConstants.RIGHT);
		k.setHorizontalAlignment(SwingConstants.RIGHT);
		kembali.setHorizontalAlignment(SwingConstants.RIGHT);
		
		setFocusEnter(code, tgl.getEditor());
		setFocusEnter(tgl.getEditor(), pelanggan);
		setFocusEnter(jml, diskon);
		setFocusEnter(diskon, diskonp);
		setFocusEnter(diskonp, bayar);
		setFocusEnter(bayar, kembali);
		
		kembali.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				save.requestFocus();
			}
		});


		
		pelanggan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// bila pelanggan null maka menampilkan popup
				// bila tidak null berarti tidak menampilkan popup
				// karena dianggap sudah benar, sehingga bila inggin mengganti
				// menggani harus menghapus terlebih dahulu
				if (cust == null) {

					// hapus semua elemaent yang terdapat pada list
					listModel.removeAllElements();

					ODatabaseDocumentTx db = App.getDbd();
				    ODatabaseRecordThreadLocal. INSTANCE.set(db);
					
					// ambil dari database dengan batasan 0-10
					List<ODocument> ps=null;
					try {
						ps=App.getPelangganDao().getAllByColumnLike(db, PelangganDao.name,
								pelanggan.getText()+"%", 0, 50);
					} catch (Exception e2) {
					}
							
					// cek apakah ps null atau hasilnya kosong
					// bila kosong maka mengambil 10 default
					if (ps == null || ps.size() == 0) {
						ps = App.getPelangganDao().getAll(db, 0, 50);
					}

					// masukkan list ps kedalam model
					// untuk split menggunakan ":    "
					for (ODocument pelanggan : ps) {
						listModel.addElement(pelanggan.field(PelangganDao.code) + ", "
								+ pelanggan.field(PelangganDao.name));
					}

					// pengaturan preferred size pada jlist
					// - 19 digunakan untuk scrol
					custList.setPreferredSize(new Dimension(pelanggan
							.getSize().width - 4, custList
							.getPreferredSize().height));

					// +8 digunakan untuk meletakkan di bawah persis pd
					// jtextfiled
					popup.show(pelanggan, 0, 18);

					// pengaturan default
					custList.requestFocus();
					custList.setSelectedIndex(0);
				}

			}
		});
		
		
		custList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					popup.setVisible(false);
					if (custList.getSelectedIndex()!=-1) {
						String tmp =(String) listModel.getElementAt(custList
								.getSelectedIndex());
						if (tmp!=null) {
							
							pelanggan.setText( tmp);
							jml.requestFocus();
						}
					}
					
					listModel.removeAllElements();
				}
			}
		});
		
		pelanggan.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				String value = pelanggan.getText();
				String[] split = ((String) value).split(", ");
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				try {
					String id = split[0];
					cust = App.getPelangganDao().getOne(db, PelangganDao.code, id);
				} catch (Exception ex) {
					cust=null;
				}finally{
					db.close();
				}
				if (cust==null) {
					pelanggan.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				harga.setValue(DataUser.getProduct().field(ProductDao.harga));
			}
		});
		
		jml.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				if (jml.getText().equalsIgnoreCase("")) {
					App.showErrorFieldEmpty(db, "Jumlah");
					jml.setText("1");
					jml.requestFocus();
					jml.selectAll();
				}else{
					try {
						int tmp=Integer.parseInt(jml.getText());
						if (tmp==0) {
							App.showErrorFieldEmpty(db, "Jumlah");
							jml.setText("1");
							jml.requestFocus();
							jml.selectAll();
						}
						
					} catch (Exception e2) {
						App.showErrorFieldEmpty(db, "Jumlah");
						jml.setText("1");
						jml.requestFocus();
						jml.selectAll();
					}
					autoHarga();
				}
				
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		
		diskon.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				autoHarga();
			}

			@Override
			public void focusGained(FocusEvent e) {
				diskon.selectAll();
			}
		});
		diskonp.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				autoHarga();
			}

			@Override
			public void focusGained(FocusEvent e) {
				diskonp.selectAll();

			}
		});
		bayar.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				autoKembali();
			}

			@Override
			public void focusGained(FocusEvent e) {
				bayar.selectAll();

			}
		});
		
		bayar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String tmp=bayar.getText();
				try {
					if (tmp!=null&&!tmp.equalsIgnoreCase("")) {
						double tmp2=Double.parseDouble(tmp.replaceAll("\\,", ""));
						bayar.setText(App.paymentFormat.format(tmp2));
					}
				} catch (Exception e2) {
					App.printErr(e2);
					bayar.setText("");
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		save=new JButton(App.getT(db, "Save"));
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiSave();
				
			}
		})	;
		
		reset=new JButton(App.getT(db, "Reset"));
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aksiReset();
				
			}
		})	;
	}
	
	
	public void aksiSave() {

		ODatabaseDocumentTx db=App.getDbd();
		ODatabaseRecordThreadLocal. INSTANCE.set(db);
		if (validate(db)) {
			
			ODocument tmp=App.getPenjualanDao().factoryModel(codea, tgla, pelanggana,
					hargaa, jmla, total1a, diskona, diskonpa, totaldiskona, totala, bayara, da, ka, notea);
			
			try {
				App.getPenjualanDao().save(db, tmp);
				for (WidgetAdapter w: widgeds) {
					w.modelWidgetAdd(tmp);
					w.modelWidgetAdd(pelanggana);
//					this.model=tmp;
				}
				aksiReset();
				App.showSaveOk();
				
			} catch (Exception e) {
				App.printErr(e);
			}finally{
				db.close();
			}
			
		}
	
		
	}


	public void buildForm(ODatabaseDocumentTx db) {
		FormLayout layout = new FormLayout(
				"l:p,   	4dlu,   	f:max(60dlu;p):g(.4),    	15dlu,"
			  + "l:p,   	4dlu, 		f:max(60dlu;p):g(.4),   	15dlu, "
			  + "l:p,   	4dlu, 		f:max(88dlu;p):g(.2),  	4dlu, 		f:max(88dlu;p):g(.2),  	15dlu",

				"p,3dlu,  " +//nota
				" p,3dlu,  " +//cust
				"f:25dlu:g,3dlu,    " +//jml
				"f:25dlu:g,3dlu,    " +//total
				"p,3dlu,   " +//diskon
				"f:25dlu:g,3dlu,   " +//total
				"p,3dlu,   " +
				"p,3dlu,   " +
				"f:25dlu:g,3dlu,   " +
				"p,3dlu,   " +
//				"p,3dlu,  " +
//				" p,3dlu,   " +
//				"p,3dlu,   " +
				"p,3dlu");

		layout.setColumnGroups(new int[][] { { 1, 5 ,9}, { 3, 7 } });
		FormBuilder builder = new FormBuilder(layout, true);
		

		builder.append(db, PenjualanDao.fcode, code, 1, 1, 1);
		builder.append(db, PenjualanDao.ftgl, tgl, 5, 1, 1);
		builder.append(db, PenjualanDao.fpelanggan, pelanggan, 1, 3, 5);
		builder.append(db, f2,PenjualanDao.fjml, jml, 1, 5, 1);
		builder.append(db, f2,PenjualanDao.fharga, harga, 5, 5, 1);
		builder.append(db, f2,PenjualanDao.ftotal1, total1, 1, 7, 5);
		builder.append(db, PenjualanDao.fdiskon, diskon, 1, 9, 1);
		builder.append(db, PenjualanDao.fdiskonp, diskonp, 5, 9, 1);
		builder.append(db, f2,PenjualanDao.ftotal, total, 1, 11, 5);
		
		
		builder.append(db, f2,PenjualanDao.fbayar, bayar, 9, 1, 3, 3);
		builder.append(db, f2,PenjualanDao.fhutang, k, 9, 5, 3);
		builder.append(db, f2,PenjualanDao.fkembali, kembali, 9, 7, 3);
		
		builder.append( save, 11, 11);
		
		builder.append( reset, 13, 11);
		


		
		
		JPanel p = builder.getPanel();
		p.setBackground(Color.WHITE);
		JScrollPane pane=new JScrollPane(p);
		JLabel label=new JLabel(App.getIcon(db, "icon edit 16"));
		label.setText(App.getT(db, "   Input Data Penjualan"));
		label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(pane, BorderLayout.CENTER);
		panel.add(label, BorderLayout.NORTH);
		aksiReset();
	}

	@Override
	public void load(ODocument model) {
//		if (model==null) {
//			code.setText("");
//			name.setText("");
//			note.setText("");
//		}else
//		if (model.field("@class").equals("Kandang")) {
//			this.model=model;
//			code.setText(model.field(KandangDao.code)+"");
//			name.setText(model.field(KandangDao.name)+"");
//			note.setText(model.field(KandangDao.note)+"");
//			
//		}
		
	}


	@Override
	public void modelWidgetChange(ODocument model) {
		load(model);
		
	}

	@Override
	public void modelWidgetAdd(ODocument model) {
		// TODO Auto-generated method stub
		
	}

	public void autoHarga(){
		double harga_tmp=0;
		double jumlah_tmp=0;
		double total1_tmp=0;
		double diskon_tmp=0;
		double diskonp_tmp=0;
		double total_tmp=0;
		double bayar_tmp=0;
		try {
			harga.commitEdit();
			harga_tmp=Double.parseDouble(harga.getValue()+"");
		} catch (Exception e) {
			App.printErr(e);
		}
		try {
			jml.commitEdit();
			jumlah_tmp=Double.parseDouble(jml.getValue()+"");
		} catch (Exception e) {
			//App.showErrorJumlahTidakFalid();
			jml.setText("");
			App.printErr(e);
		}
		total1_tmp=harga_tmp*jumlah_tmp;
		total1.setValue(total1_tmp);
		
		try {
			diskon.commitEdit();
			diskon_tmp=Double.parseDouble(diskon.getValue()+"");
		} catch (Exception e) {
			diskon.setText("0");
			App.printErr(e);
		}
		try {
			diskonp.commitEdit();
			diskonp_tmp=Double.parseDouble(diskonp.getValue()+"");
		} catch (Exception e) {
			diskonp.setText("0");
			App.printErr(e);
		}
		
		double diskonp_rp=0;
		if (diskonp_tmp>0) {
			diskonp_rp=total1_tmp*diskonp_tmp/100;
		}
		
		double total_diskon=diskon_tmp+diskonp_rp;
		App.info(total_diskon+"");
		total_tmp=total1_tmp-total_diskon;
		
		total.setValue(total_tmp);
		try {
			total.commitEdit();
		} catch (ParseException e) {
			App.printErr(e);
		}
		
		
		
	autoKembali();
	}
	
	public void autoKembali(){
		double total_tmp=0;
		double bayar_tmp=0;
		double hutang_tmp=0;
		double kembali_tmp=0;
		
		
		if (!bayar.getText().equals("")) {
			try {
				bayar_tmp=Double.parseDouble(bayar.getText().replaceAll("\\,", ""));
			} catch (Exception e) {
				App.printErr(e);
			}
		}
		
		try {
			total.commitEdit();
			total_tmp=Double.parseDouble(total.getValue()+"");
		} catch (Exception e) {
			total.setText("0");
		}
		
		if (total_tmp>bayar_tmp) {
			hutang_tmp=total_tmp-bayar_tmp;
		}else{
			kembali_tmp=bayar_tmp-total_tmp;
		}
		
		k.setValue(hutang_tmp);
		kembali.setValue(kembali_tmp);
		try {
			k.commitEdit();
			kembali.commitEdit();
		} catch (ParseException e) {
			App.printErr(e);
		}
		
	}


	@Override
	public void aksiReset() {
		code.setText("AUTO");
		
		
		tgl.setDate(new Date());
		clearText(pelanggan);
		
		cust=null;
		
		clearText(jml);
		double p=DataUser.getProduct().field(ProductDao.harga);
		harga.setValue(p);
		
		
		clearText(total1);
		diskon.setText("0");
		diskonp.setText("0.00");
		clearText(total);
		bayar.setText("");
		k.setText("0");
		kembali.setText("0");
		setCursor();

	}
	public void setCursor() {
		pelanggan.requestFocus();

	}


	@Override
	public Component getPanelForm() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Component getLabelTitle() {
		// TODO Auto-generated method stub
		return null;
	}
}

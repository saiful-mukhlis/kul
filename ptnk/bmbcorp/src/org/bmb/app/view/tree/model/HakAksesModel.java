package org.bmb.app.view.tree.model;


import java.util.List;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

import com.bmb.app.config.DataUser;
import com.bmb.app.dao.GrpDao;
import com.bmb.app.db.Grp;
import com.bmb.app.global.App;
import com.orientechnologies.orient.core.db.ODatabaseRecordThreadLocal;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;


public class HakAksesModel extends AbstractTreeTableModel {
	private HakAkses myroot;
	private ODocument group;

	public HakAksesModel(ODocument group) {
		super();
		this.group = group;
		this.myroot = buildHakakses();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(Object arg0, int arg1) {
		HakAkses treenode = (HakAkses) arg0;
		switch (arg1) {
		case 0:
			return treenode.getNama();
		case 1:
//			if (treenode.getGroup()==null) {
//				if (group!=null) {
//					treenode.setGroup(group);
//				}
//			}else if(group!=null && treenode.getGroup()!=group){
//				treenode.setGroup(group);
//			}
//			App.info(treenode.isAktif()+" "+treenode.getNama()+" ");
			treenode.setGroup(group);
			return treenode.isAktif();
		default:
			return "Unknown";
		}
	}

	@Override
	public Object getChild(Object parent, int index) {
		HakAkses treenode = (HakAkses) parent;
		return treenode.getChilds().get(index);
	}

	@Override
	public int getChildCount(Object parent) {
		HakAkses treenode = (HakAkses) parent;
		return treenode.getChilds().size();
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		HakAkses treenode = (HakAkses) parent;
		for (int i = 0; i > treenode.getChilds().size(); i++) {
			if (treenode.getChilds().get(i) == child) {
				return i;
			}
		}

		return 0;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Nama";
		case 1:
			return "Aktifkan";
		default:
			return "Unknown";
		}
	}

	public boolean isLeaf(Object node) {
		HakAkses treenode = (HakAkses) node;
		if (treenode.getChilds().size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public Object getRoot() {
		return myroot;
	}

	public void reloadRoot() {
		this.myroot = buildHakakses();
	}

	@Override
	public boolean isCellEditable(Object node, int column) {
		if (column == 1) {
			return DataUser.HAK_AKSES_EDIT;
		}
		return false;
	}

	@Override
	public void setValueAt(Object value, Object node, int column) {
		if (group != null) {
			if (column == 1) {
				ODatabaseDocumentTx db = App.getDbd();
			    ODatabaseRecordThreadLocal. INSTANCE.set(db);
				try {

					((HakAkses) node).setAktif((Boolean) value);
					if ((Boolean) value) {
						String kode = ((HakAkses) node).getKode();
						String data = group.field(GrpDao.key);
						if (data != null) {
							if (data.indexOf(kode) == -1) {
								data = data + kode;
								group.field(GrpDao.key, data);
								group.save();
							}
						}else{
							data="";
							if (data.indexOf(kode) == -1) {
								data = data + kode;
								group.field(GrpDao.key, data);
								group.save();
							}
						}
					} else {
						String kode = ((HakAkses) node).getKode();
						String data = group.field(GrpDao.key);
						if (data != null) {
							if (data.indexOf(kode) != -1) {
								data = data.replaceAll(kode, "");
								group.field(GrpDao.key, data);
								group.save();
							}
						}
					}
					if ((Boolean) value) {
						HakAkses parent = ((HakAkses) node).getParent();
						if (parent != null) {
							((HakAkses) parent).setAktif((Boolean) value);

							String kode = ((HakAkses) parent).getKode();
							String data = group.field(GrpDao.key);
							if (data != null) {
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									group.field(GrpDao.key, data);
									group.save();
								}
							}else{
								data="";
								if (data.indexOf(kode) == -1) {
									data = data + kode;
									group.field(GrpDao.key, data);
									group.save();
								}
							}

						}
					}

					if (!(Boolean) value) {
						List<HakAkses> childs = ((HakAkses) node).getChilds();
						for (HakAkses hakAkses : childs) {
							((HakAkses) hakAkses).setAktif((Boolean) value);

							String kode = ((HakAkses) hakAkses).getKode();
							String data = group.field(GrpDao.key);
							if (data != null) {
								if (data.indexOf(kode) != -1) {
									data = data.replaceAll(kode, "");
									group.field(GrpDao.key, data);
									group.save();
								}
							}
						}

					}
					
					
				} catch (Exception e) {
					App.printErr(e);
				}finally{
					db.close();
				}
				
			}
			super.setValueAt(value, node, column);

		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 1) {
			return Boolean.class;
		}
		return super.getColumnClass(column);
	}

	public HakAkses buildHakakses() {
		HakAkses top = new HakAkses("Root", DataUser.XROOT);
		
		//Hak Akses===============================================

		HakAkses perent = new HakAkses("Hak Akses", DataUser.XHAK_AKSES_VIEW, group);
		top.add(perent);
		
		HakAkses anak = new HakAkses("Tambah Hak Akses", DataUser.XHAK_AKSES_ADD, group);
		perent.add(anak);

		anak = new HakAkses("Edit Hak Akses", DataUser.XHAK_AKSES_EDIT, group);
		perent.add(anak);
		
		anak = new HakAkses("Hapus Hak Akses", DataUser.XHAK_AKSES_HAPUS, group);
		perent.add(anak);
		
		
		//Pegawai
		perent = new HakAkses("Pegawai", DataUser.XUSR_VIEW, group);
		top.add(perent);

		anak = new HakAkses("Tambah Pegawai", DataUser.XUSR_ADD, group);
		perent.add(anak);
		
		anak = new HakAkses("Edit Pegawai", DataUser.XUSR_EDIT, group);
		perent.add(anak);
		
		anak = new HakAkses("Hapus Pegawai", DataUser.XUSR_DEL, group);
		perent.add(anak);
		
		//Kandang
				perent = new HakAkses("Kandang", DataUser.XKANDANG_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Tambah Kandang", DataUser.XKANDANG_ADD, group);
				perent.add(anak);
				
				anak = new HakAkses("Edit Kandang", DataUser.XKANDANG_EDIT, group);
				perent.add(anak);
				
				anak = new HakAkses("Hapus Kandang", DataUser.XKANDANG_DEL, group);
				perent.add(anak);
				
				//Pelanggan
				perent = new HakAkses("Pelanggan", DataUser.XPELANGGAN_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Tambah Pelanggan", DataUser.XPELANGGAN_ADD, group);
				perent.add(anak);
				
				anak = new HakAkses("Edit Pelanggan", DataUser.XPELANGGAN_EDIT, group);
				perent.add(anak);
				
				anak = new HakAkses("Hapus Pelanggan", DataUser.XPELANGGAN_DEL, group);
				perent.add(anak);
		
				//Produksi
				perent = new HakAkses("Produksi", DataUser.XPRODUKSI_VIEW, group);
				top.add(perent);
				
				//Penjualan
				perent = new HakAkses("Penjualan", DataUser.XPENJUALAN_VIEW, group);
				top.add(perent);
				
				//Pakan
				perent = new HakAkses("Pakan", DataUser.XPAKAN_VIEW, group);
				top.add(perent);
				
				//Laporan Lajur
				perent = new HakAkses("Laporan Lajur", DataUser.XLAJURD_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Print Laporan Lajur", DataUser.XLAJURD_PRINT, group);
				perent.add(anak);
				
				//Laporan Kandang
				perent = new HakAkses("Laporan Kandnag", DataUser.XKANDANGD_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Print Laporan Kandnag", DataUser.XKANDANGD_PRINT, group);
				perent.add(anak);
				
				//Laporan Kandang All
				perent = new HakAkses("Laporan Total Kandnag", DataUser.XKANDANGALL_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Print Laporan Total Kandnag", DataUser.XKANDANGALL_PRINT, group);
				perent.add(anak);
				
				//Laporan Kandang All
				perent = new HakAkses("Stock", DataUser.XPRODUCTD_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Edit Stock", DataUser.XPRODUCTD_EDIT, group);
				perent.add(anak);
		
				//Laporan Piutang
				perent = new HakAkses("Piutang", DataUser.XPIUTANG_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Pembayaran Piutang", DataUser.XPEMBAYARAN_EDIT, group);
				perent.add(anak);
				
				//Laporan Piutang
				perent = new HakAkses("Format Laporan", DataUser.XFORMAT_VIEW, group);
				top.add(perent);

				anak = new HakAkses("Edit Format Laporan", DataUser.XFORMAT_EDIT, group);
				perent.add(anak);
		
		
		
		return top;

	}

	public void setGroup(ODocument group) {
		this.group = group;
		reloadRoot();
	}

	public ODocument getGroup() {
		return group;
	}
}

package com.bmb.app.dao;


import java.util.Random;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import com.bmb.app.dao.abst.DaoAbstract;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

public class BosDao extends DaoAbstract {

	public static final String id = "id";
	public static final String name = "name";
	public static final String jml = "jml";



	public BosDao() {
		super("Bos");
	}

	public ODocument factoryModel(int ida, String namea, int jmla) {
		ODocument o = new ODocument(getClassName());
		setId(o, ida);
		setName(o, namea);
		setJml(o, jmla);
		return o;
	}

	public ODocument factoryModelUpdate(ODocument o,int ida, String namea, int jmla) {
		setId(o, ida);
		setName(o, namea);
		setJml(o, jmla);
		return o;
	}

	public void factoryFirst(ODatabaseDocumentTx db){
			ODocument o=factoryModel(1,"jmllog", 20);
			o.save();
			
			o=factoryModel(2,creatRendom(), 0);
			o.save();
	}
	
	public String getName(ODocument o) {
		return o.field(name);
	}

	public ODocument setName(ODocument o,String namea) {
		o.field(name, namea);
		return o;
	}

	public int getJml(ODocument o) {
		return o.field(jml);
	}

	public ODocument setJml(ODocument o, int jmla) {
		o.field(jml, jmla, OType.INTEGER);
		return o;
	}

	public int getId(ODocument o) {
		return o.field(id);
	}

	public ODocument setId(ODocument o, int ida) {
		o.field(id, ida, OType.INTEGER);
		return o;
	}
	
	public boolean check(ODatabaseDocumentTx db){
		ODocument o=getOne(db, id, 2);
		if (o==null) {
			factoryFirst(db);
			//baru pertama jadi belum reg
			return false;
		}
		int jumlahStatus=getJml(o);
		String kodenya=getName(o);
		if (jumlahStatus==1) {
			//sudah registrasi
			Preferences userPref = Preferences.userRoot();
			String x=userPref.get("ortptnk", "x");
			if (!x.equalsIgnoreCase(kodenya)) {
				if (jumlahStatus==1) {
					setName(o, creatRendom());
					setJml(o, 0);
					o.save();
				}
				//di hack
				return false;
			}
			return true;
		}
		return false;
	}
	
	public String creatRendom(){
		Random r = new Random();

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZOIUYTR";
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			tmp.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}
		return tmp.toString();
	}
	
	public void minus(ODatabaseDocumentTx db){
		ODocument o=getOne(db, id, 2);
		if (o!=null) {
//			String namaKode=getName(o);
			int jmlStatus=getJml(o);
			if (jmlStatus==0) {
				//belum reg
				ODocument o1=getOne(db, id, 1);
				if (o1!=null) {
//					String namaKode1=getName(o1);
					int jmlStatus1=getJml(o1);
					if (jmlStatus1<0) {
						JOptionPane.showMessageDialog(null, "Masa Trial Sudah Habis");
						System.exit(0);
					}
					jmlStatus1--;
					setJml(o1, jmlStatus1);
					o1.save();
				}else{
					factoryFirst(db);
					db.close();
					System.exit(0);
				}
			}else{
				//System.exit(1);
			}
		}else{
			factoryFirst(db);
			db.close();
			System.exit(0);
		}
	}
}

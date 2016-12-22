package id.ac.its.depandi.dynamic_srs.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import id.ac.its.depandi.dynamic_srs.core.NFR;

@XmlRootElement(name = "nfr_cat")
public class Cat{

	private int cat_id;
	private String cat_name;
	private ArrayList<NFR> listNFR;
	
	@XmlTransient
	public int getCat_id() {
		return cat_id;
	}
	
	@XmlAttribute
	public String getCat_name() {
		return cat_name;
	}
	
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	@XmlElement(name = "nfr")
	public ArrayList<NFR> getListNFR() {
		return listNFR;
	}

	public void setListNFR(ArrayList<NFR> listNFR) {
		this.listNFR = listNFR;
	}
	
	public Cat() {
		super();
	}
	
	public Cat(String cat_name){
		super();
		this.cat_name = cat_name;
	}

	public Cat(int cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}

	@Override
	public String toString() {
		return cat_name;
	}
}

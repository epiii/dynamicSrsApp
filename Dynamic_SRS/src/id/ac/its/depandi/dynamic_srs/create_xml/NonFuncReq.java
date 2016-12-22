package id.ac.its.depandi.dynamic_srs.create_xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import id.ac.its.depandi.dynamic_srs.core.Cat;

@XmlRootElement(name = "non_fun_req")
public class NonFuncReq {

	private ArrayList<Cat> listCat;

	@XmlElement(name = "nfr_cat")
	public ArrayList<Cat> getListCat() {
		return listCat;
	}

	public void setListCat(ArrayList<Cat> listCat) {
		this.listCat = listCat;
	}

	public NonFuncReq() {
		super();
	}

	@Override
	public String toString() {
		return "NonFuncReq [listCat=" + listCat + "]";
	}
	
}

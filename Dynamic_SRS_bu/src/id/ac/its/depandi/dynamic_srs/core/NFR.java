package id.ac.its.depandi.dynamic_srs.core;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "nfr")
@XmlType(propOrder = { "nfr_name", "nfr_desc", "nfr_fit_cri" })
public class NFR {

	private int nfr_id;
	private int cat_id;
	private int srs_id;
	private String nfr_name;
	private String nfr_desc;
	private String nfr_fit_cri;
	
	@XmlTransient
	public int getNfr_id() {
		return nfr_id;
	}
	
	public void setNfr_id(int nfr_id) {
		this.nfr_id = nfr_id;
	}
	
	@XmlTransient
	public int getCat_id() {
		return cat_id;
	}
	
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	
	@XmlTransient
	public int getSrs_id() {
		return srs_id;
	}

	public void setSrs_id(int srs_id) {
		this.srs_id = srs_id;
	}

	public String getNfr_name() {
		return nfr_name;
	}
	
	public void setNfr_name(String nfr_name) {
		this.nfr_name = nfr_name;
	}
	
	public String getNfr_desc() {
		return nfr_desc;
	}
	
	public void setNfr_desc(String nfr_desc) {
		this.nfr_desc = nfr_desc;
	}
	
	public String getNfr_fit_cri() {
		return nfr_fit_cri;
	}
	
	public void setNfr_fit_cri(String nfr_fit_cri) {
		this.nfr_fit_cri = nfr_fit_cri;
	}
	
	public NFR() {
		super();
	}

	public NFR(int cat_id, int srs_id, String nfr_name, String nfr_desc, String nfr_fit_cri) {
		super();
		this.cat_id = cat_id;
		this.srs_id = srs_id;
		this.nfr_name = nfr_name;
		this.nfr_desc = nfr_desc;
		this.nfr_fit_cri = nfr_fit_cri;
	}

	@Override
	public String toString() {
		return "NFR [nfr_name=" + nfr_name + ", nfr_desc=" + nfr_desc + ", nfr_fit_cri=" + nfr_fit_cri + "]";
	}
}

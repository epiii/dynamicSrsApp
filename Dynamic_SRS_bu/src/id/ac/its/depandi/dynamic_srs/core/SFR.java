package id.ac.its.depandi.dynamic_srs.core;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sfr")
@XmlType(propOrder = { "sfr_name", "sfr_desc", "sfr_fit_cri" })
public class SFR {
	
	private int sfr_id;
	private int step_id;
	private int srs_id;
	private String sfr_name;
	private String sfr_desc;
	private String sfr_fit_cri;
	
	@XmlTransient
	public int getSfr_id() {
		return sfr_id;
	}

	public void setSfr_id(int sfr_id) {
		this.sfr_id = sfr_id;
	}

	@XmlTransient
	public int getStep_id() {
		return step_id;
	}

	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	@XmlTransient
	public int getSrs_id() {
		return srs_id;
	}

	public void setSrs_id(int srs_id) {
		this.srs_id = srs_id;
	}

	public String getSfr_name() {
		return sfr_name;
	}
	
	public String getSfr_desc() {
		return sfr_desc;
	}
	
	public String getSfr_fit_cri() {
		return sfr_fit_cri;
	}
	
	public void setSfr_name(String sfr_name) {
		this.sfr_name = sfr_name;
	}
	
	public void setSfr_desc(String sfr_desc) {
		this.sfr_desc = sfr_desc;
	}
	
	public void setSfr_fit_cri(String sfr_fit_cri) {
		this.sfr_fit_cri = sfr_fit_cri;
	}
	
	public SFR() {
		super();
	}

	public SFR(int step_id, int srs_id, String sfr_name, String sfr_desc, String sfr_fit_cri) {
		super();
		this.step_id = step_id;
		this.srs_id = srs_id;
		this.sfr_name = sfr_name;
		this.sfr_desc = sfr_desc;
		this.sfr_fit_cri = sfr_fit_cri;
	}

	@Override
	public String toString() {
		return "SFR [sfr_name=" + sfr_name + ", sfr_desc=" + sfr_desc + ", sfr_fit_cri=" + sfr_fit_cri + "]";
	}
}

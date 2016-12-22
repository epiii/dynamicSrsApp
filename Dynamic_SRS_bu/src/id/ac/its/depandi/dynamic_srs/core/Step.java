package id.ac.its.depandi.dynamic_srs.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import id.ac.its.depandi.dynamic_srs.core.SFR;

@XmlRootElement(name = "step")
@XmlType(propOrder = { "step_name", "listSFR" })
public class Step {

	private int step_id;
	private String step_name;
	private ArrayList<SFR> listSFR;
	
	@XmlTransient
	public int getStep_id() {
		return step_id;
	}
	
	@XmlElement(name = "step_no")
	public String getStep_name() {
		return step_name;
	}
	
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}
	
	public void setStep_name(String step_name) {
		this.step_name = step_name;
	}
	
	@XmlElement(name = "sfr")
	public ArrayList<SFR> getListSFR() {
		return listSFR;
	}

	public void setListSFR(ArrayList<SFR> listSFR) {
		this.listSFR = listSFR;
	}

	public Step() {
		super();
	}
	
	public Step(String stepName){
		super();
		this.step_name = stepName;
	}

	public Step(int step_id, String step_name) {
		super();
		this.step_id = step_id;
		this.step_name = step_name;
	}

	@Override
	public String toString() {
		return step_name;
	}
}

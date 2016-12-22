package id.ac.its.depandi.dynamic_srs.create_xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import id.ac.its.depandi.dynamic_srs.core.Step;

@XmlRootElement(name = "use_case")
public class UseCase {

	private ArrayList<Step> listStep;

	@XmlElement(name = "step")
	public ArrayList<Step> getListStep() {
		return listStep;
	}

	public void setListStep(ArrayList<Step> listStep) {
		this.listStep = listStep;
	}

	public UseCase() {
		super();
	}

	@Override
	public String toString() {
		return "UseCase [listStep=" + listStep + "]";
	}
	
	
}

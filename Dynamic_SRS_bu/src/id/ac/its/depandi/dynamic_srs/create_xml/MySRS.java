package id.ac.its.depandi.dynamic_srs.create_xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import id.ac.its.depandi.dynamic_srs.core.Description;
import id.ac.its.depandi.dynamic_srs.core.Introduction;

@XmlRootElement(name = "srs")
@XmlType(propOrder = { "intro", "gen_des", "specific_req", "appendices" })
public class MySRS {

	private Introduction intro;
	private Description gen_des;
	private SpesificReq specific_req;
	private String appendices = "";

	@XmlElement(name = "Intro")
	public Introduction getIntro() {
		return intro;
	}

	public void setIntro(Introduction intro) {
		this.intro = intro;
	}

	public Description getGen_des() {
		return gen_des;
	}

	public void setGen_des(Description gen_des) {
		this.gen_des = gen_des;
	}

	public SpesificReq getSpecific_req() {
		return specific_req;
	}

	public void setSpecific_req(SpesificReq specific_req) {
		this.specific_req = specific_req;
	}

	public String getAppendices() {
		return appendices;
	}

	public void setAppendices(String appendices) {
		this.appendices = appendices;
	}

	@Override
	public String toString() {
		return "MySRS [intro=" + intro + ", gen_des=" + gen_des + ", specific_req=" + specific_req + ", appendices="
				+ appendices + "]";
	}
}

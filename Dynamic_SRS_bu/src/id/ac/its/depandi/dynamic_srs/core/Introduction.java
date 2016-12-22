package id.ac.its.depandi.dynamic_srs.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Intro")
@XmlType(propOrder = { "purpose", "scope", "definition", "reference", "overview" })
public class Introduction {

	private int intro_id;
	private int srs_id;
	private String purpose;
	private String scope;
	private String definition;
	private String reference;
	private String overview;
	
	@XmlTransient
	public int getIntro_id() {
		return intro_id;
	}
	
	public void setIntro_id(int intro_id) {
		this.intro_id = intro_id;
	}
	
	@XmlTransient
	public int getSrs_id() {
		return srs_id;
	}
	
	public void setSrs_id(int srs_id) {
		this.srs_id = srs_id;
	}
	
	public String getPurpose() {
		return purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	@XmlElement(name = "ref")
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public Introduction() {
		super();
	}

	public Introduction(int srs_id, String purpose, String scope, String definition, String reference,
			String overview) {
		super();
		this.srs_id = srs_id;
		this.purpose = purpose;
		this.scope = scope;
		this.definition = definition;
		this.reference = reference;
		this.overview = overview;
	}

	@Override
	public String toString() {
		return "Introduction [purpose=" + purpose + ", scope=" + scope + ", definition=" + definition + ", reference="
				+ reference + ", overview=" + overview + "]";
	}
}

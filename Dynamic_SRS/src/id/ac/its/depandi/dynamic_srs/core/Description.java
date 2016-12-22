package id.ac.its.depandi.dynamic_srs.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "gen_des")
@XmlType(propOrder = { "perspective", "functions", "characteristic", "constraints", "dependencies" })
public class Description {

	private int desc_id;
	private int srs_id;
	private String perspective;
	private String functions;
	private String characteristic;
	private String constraints;
	private String dependencies;
	
	@XmlTransient
	public int getDesc_id() {
		return desc_id;
	}

	public void setDesc_id(int desc_id) {
		this.desc_id = desc_id;
	}

	@XmlTransient
	public int getSrs_id() {
		return srs_id;
	}
	
	public void setSrs_id(int srs_id) {
		this.srs_id = srs_id;
	}
	
	@XmlElement(name = "product_pres")
	public String getPerspective() {
		return perspective;
	}
	
	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}
	
	@XmlElement(name = "product_fun")
	public String getFunctions() {
		return functions;
	}
	
	public void setFunctions(String functions) {
		this.functions = functions;
	}
	
	@XmlElement(name = "user_char")
	public String getCharacteristic() {
		return characteristic;
	}
	
	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}
	
	@XmlElement(name = "gen_cons")
	public String getConstraints() {
		return constraints;
	}
	
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	
	@XmlElement(name = "assum_defen")
	public String getDependencies() {
		return dependencies;
	}
	
	public void setDependencies(String dependencies) {
		this.dependencies = dependencies;
	}
	
	public Description() {
		super();
	}
	
	public Description(int srs_id, String perspective, String functions, String characteristic, String constraints,
			String dependencies) {
		super();
		this.srs_id = srs_id;
		this.perspective = perspective;
		this.functions = functions;
		this.characteristic = characteristic;
		this.constraints = constraints;
		this.dependencies = dependencies;
	}

	@Override
	public String toString() {
		return "Description [perspective=" + perspective + ", functions=" + functions + ", characteristic="
				+ characteristic + ", constraints=" + constraints + ", dependencies=" + dependencies + "]";
	}
}

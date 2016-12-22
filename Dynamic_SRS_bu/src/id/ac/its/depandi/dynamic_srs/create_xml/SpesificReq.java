package id.ac.its.depandi.dynamic_srs.create_xml;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "specific_req")
@XmlType(propOrder = { "fun_req", "non_fun_req", "data_req", "manag_req", "other_req" })
public class SpesificReq {

	private FuncReq fun_req;
	private NonFuncReq non_fun_req;
	private String data_req = "";
	private String manag_req = "";
	private String other_req = "";

	public FuncReq getFun_req() {
		return fun_req;
	}

	public void setFun_req(FuncReq fun_req) {
		this.fun_req = fun_req;
	}

	public NonFuncReq getNon_fun_req() {
		return non_fun_req;
	}

	public void setNon_fun_req(NonFuncReq non_fun_req) {
		this.non_fun_req = non_fun_req;
	}

	public String getData_req() {
		return data_req;
	}

	public void setData_req(String data_req) {
		this.data_req = data_req;
	}

	public String getManag_req() {
		return manag_req;
	}

	public void setManag_req(String manag_req) {
		this.manag_req = manag_req;
	}

	public String getOther_req() {
		return other_req;
	}

	public void setOther_req(String other_req) {
		this.other_req = other_req;
	}

	public SpesificReq() {
		super();
	}

	@Override
	public String toString() {
		return "SpesificReq [fun_req=" + fun_req + ", non_fun_req=" + non_fun_req + ", data_req=" + data_req
				+ ", manag_req=" + manag_req + ", other_req=" + other_req + "]";
	}

}

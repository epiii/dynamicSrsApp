package id.ac.its.depandi.dynamic_srs.create_xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "fun_req")
public class FuncReq {

	private ArrayList<UseCase> listUseCase;

	@XmlElement(name = "use_case")
	public ArrayList<UseCase> getListUseCase() {
		return listUseCase;
	}

	public void setListUseCase(ArrayList<UseCase> listUseCase) {
		this.listUseCase = listUseCase;
	}

	public FuncReq() {
		super();
	}

	@Override
	public String toString() {
		return "FuncReq [listUseCase=" + listUseCase + "]";
	}
	
}

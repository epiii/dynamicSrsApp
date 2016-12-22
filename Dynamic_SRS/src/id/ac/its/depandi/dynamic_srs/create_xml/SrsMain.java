package id.ac.its.depandi.dynamic_srs.create_xml;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import id.ac.its.depandi.dynamic_srs.core.Cat;
import id.ac.its.depandi.dynamic_srs.core.Description;
import id.ac.its.depandi.dynamic_srs.core.Introduction;
import id.ac.its.depandi.dynamic_srs.core.NFR;
import id.ac.its.depandi.dynamic_srs.core.SFR;
import id.ac.its.depandi.dynamic_srs.core.Step;

public class SrsMain {
	
	private static final String FILE_NAME = "./data/srs.xml";

	public static void main(String[] args) {
//-----------------------------------------------------------
		Introduction intro = new Introduction(1, "data", "data", "data", "data", "data");
		Description gen_des = new Description(1, "data", "data", "data", "data", "data");
//-----------------------------------------------------------
		FuncReq fun_req = new FuncReq();
		
		UseCase use_case1 = new UseCase();
		//-----------------------------------------------------------
		Step step1 = new Step("Step 1");
		SFR sfr1 = new SFR(1, 1, "data", "data", "data");
		ArrayList<SFR> listSFR1 = new ArrayList<SFR>();
		listSFR1.add(sfr1);
		step1.setListSFR(listSFR1);
		//-----------------------------------------------------------
		Step step2 = new Step("Step 2");
		SFR sfr2 = new SFR(1, 1, "data", "data", "data");
		SFR sfr3 = new SFR(1, 1, "data", "data", "data");
		SFR sfr4 = new SFR(1, 1, "data", "data", "data");
		ArrayList<SFR> listSFR2 = new ArrayList<SFR>();
		listSFR2.add(sfr2);
		listSFR2.add(sfr3);
		listSFR2.add(sfr4);
		step2.setListSFR(listSFR2);
		//-----------------------------------------------------------
		ArrayList<Step> listStep = new ArrayList<Step>();
		listStep.add(step1);
		listStep.add(step2);
		//-----------------------------------------------------------
		use_case1.setListStep(listStep);
		
		ArrayList<UseCase> listUseCase = new ArrayList<UseCase>();
		listUseCase.add(use_case1);
		
		fun_req.setListUseCase(listUseCase);
//-----------------------------------------------------------
		NonFuncReq non_fun_req = new NonFuncReq();
		
		Cat cat1 = new Cat("Look-and-feel requirements");
		NFR nfr1 = new NFR(1, 1, "data", "data", "data");
		NFR nfr2 = new NFR(1, 1, "data", "data", "data");
		ArrayList<NFR> listNFR1 = new ArrayList<NFR>();
		listNFR1.add(nfr1);
		listNFR1.add(nfr2);
		cat1.setListNFR(listNFR1);
		//-----------------------------------------------------------
		Cat cat2 = new Cat("Usability requirements");
		NFR nfr3 = new NFR(1, 1, "data", "data", "data");
		NFR nfr4 = new NFR(1, 1, "data", "data", "data");
		ArrayList<NFR> listNFR2 = new ArrayList<NFR>();
		listNFR2.add(nfr3);
		listNFR2.add(nfr4);
		cat2.setListNFR(listNFR2);
		//-----------------------------------------------------------
		ArrayList<Cat> listCat = new ArrayList<Cat>();
		listCat.add(cat1);
		listCat.add(cat2);
		
		non_fun_req.setListCat(listCat);
//-----------------------------------------------------------
		SpesificReq specific_req = new SpesificReq();
		specific_req.setFun_req(fun_req);
		specific_req.setNon_fun_req(non_fun_req);
//-----------------------------------------------------------
		MySRS mySRS = new MySRS();
		mySRS.setIntro(intro);
		mySRS.setGen_des(gen_des);
		mySRS.setSpecific_req(specific_req);
		
		jaxbObjectToXML(mySRS);

		MySRS objSRS = jaxbXMLToObject();
		System.out.println(objSRS.toString());
	}
	
	private static MySRS jaxbXMLToObject() {
		try {
			JAXBContext context = JAXBContext.newInstance(MySRS.class);
			Unmarshaller un = context.createUnmarshaller();
			MySRS mySRS = (MySRS) un.unmarshal(new File(FILE_NAME));
			return mySRS;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void jaxbObjectToXML(MySRS mySRS) {

		try {
			JAXBContext context = JAXBContext.newInstance(MySRS.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			m.marshal(mySRS, System.out);

			// Write to File
			m.marshal(mySRS, new File(FILE_NAME));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}

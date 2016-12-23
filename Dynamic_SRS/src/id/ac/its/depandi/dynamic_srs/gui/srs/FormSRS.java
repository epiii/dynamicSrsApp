package id.ac.its.depandi.dynamic_srs.gui.srs;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import id.ac.its.depandi.dynamic_srs.core.Cat;
import id.ac.its.depandi.dynamic_srs.core.Description;
import id.ac.its.depandi.dynamic_srs.core.Introduction;
import id.ac.its.depandi.dynamic_srs.core.NFR;
import id.ac.its.depandi.dynamic_srs.core.SFR;
import id.ac.its.depandi.dynamic_srs.core.SRS;
import id.ac.its.depandi.dynamic_srs.core.Step;
import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.create_xml.FuncReq;
import id.ac.its.depandi.dynamic_srs.create_xml.MySRS;
import id.ac.its.depandi.dynamic_srs.create_xml.NonFuncReq;
import id.ac.its.depandi.dynamic_srs.create_xml.SpesificReq;
import id.ac.its.depandi.dynamic_srs.create_xml.UseCase;
import id.ac.its.depandi.dynamic_srs.dao.SrsDAO;
import id.ac.its.depandi.dynamic_srs.gui.home.FormHome;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@SuppressWarnings("rawtypes")
public class FormSRS extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	// panel SRSName
	private JTextField txtSRSName;
	private JLabel lblCreatedBy;

	// Tab Introduction
	private JTextArea txtPurpose;
	private JTextArea txtScope;
	private JTextArea txtDefinition;
	private JTextArea txtReferences;
	private JTextArea txtOverview;

	// Tab General Description
	private JTextArea txtPerspective;
	private JTextArea txtFunctions;
	private JTextArea txtCharacteristics;
	private JTextArea txtConstraints;
	private JTextArea txtDependencies;

	// Tab SFR
	private JComboBox comboBoxStep;
	private JButton btnAddNewStep;
	private JTextField txtNameSFR;
	private JTextArea txtDescSFR;
	private JTextArea txtCriteriaSFR;

	// Tab NFR
	private JComboBox comboBoxCat;
	private JButton btnAddCat;
	private JTextField txtNameNFR;
	private JTextArea txtDescNFR;
	private JTextArea txtCriteriaNFR;

	// panel button
	private JButton btnSaveToDatabase;
	private JButton btnSaveToXml;

	private User theUser;
	private SRS theSRS;
	private SrsDAO srsDAO;

	private Introduction theIntro;
	private Description theDesc;
	private SFR theSFR;
	private NFR theNFR;

	private int choice;

	private boolean isValidSRS;
	private boolean isValidIntro;
	private boolean isValidDesc;

	private static final String dirName = "./data/";
	private String fileName;
	private MySRS mySRS;
	private FuncReq fun_req;
	private ArrayList<UseCase> listUseCase;
	private ArrayList<Step> listStep;
	private ArrayList<SFR> listSFR;
	private UseCase use_case;
	private NonFuncReq non_fun_req;
	private ArrayList<Cat> listCat;
	private ArrayList<NFR> listNFR;
	private SpesificReq specific_req;

	private Step tempStep;
	private Cat tempCat;
	
	private int klikStep;
	private int klikCat;

	public FormSRS(User theUser) {
		this();
		this.theUser = theUser;
		srsDAO = new SrsDAO();
		setLoginUserName();
		setIsAdmin();
		refreshStepView();
		refreshCatView();
		mySRS = new MySRS();
		fun_req = new FuncReq();
		use_case = new UseCase();
		listUseCase = new ArrayList<UseCase>();
		non_fun_req = new NonFuncReq();
		specific_req = new SpesificReq();
		klikStep = 0;
		klikCat = 0;
	}

	private void loadFHome() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure to close this window?", "Really Closing?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			dispose();
			FormHome fHome = new FormHome(theUser);
			fHome.setLoginUserName();
			fHome.pack();
			fHome.setLocationRelativeTo(null);
			fHome.setVisible(true);
		}
	}

	/**
	 * Create the frame.
	 */
	public FormSRS() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				loadFHome();
			}
		});
		setResizable(false);
		setTitle("Create New SRS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 660, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		// ============================================================================================================================
		JPanel panelSRS = new JPanel();
		panelSRS.setBorder(null); // atas, kiri, bawah, kanan
		contentPane.add(panelSRS, BorderLayout.NORTH);
		panelSRS.setLayout(new BoxLayout(panelSRS, BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelSRS.add(panel_1);

		JLabel lblSrsName = new JLabel("SRS Name : ");
		panel_1.add(lblSrsName);

		txtSRSName = new JTextField();
		panel_1.add(txtSRSName);
		txtSRSName.setColumns(25);

		JPanel panel = new JPanel();
		panelSRS.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		lblCreatedBy = new JLabel("Created By : Depandi Enda");
		panel.add(lblCreatedBy);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// Panel
		// 1============================================================================================================================
		JPanel panelIntroduction = new JPanel();
		panelIntroduction.setLayout(null);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelPurpose = new JPanel();
		panelPurpose.setBorder(new TitledBorder(null, "Purpose", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelPurpose.setBounds(10, 10, 607, 100); // jarak kiri, jarak atas,
													// lebar content, tinggi
													// content
		panelPurpose.setLayout(new BorderLayout(0, 0));

		txtPurpose = new JTextArea();

		JScrollPane scrollPurpose = new JScrollPane(txtPurpose);
		scrollPurpose.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelPurpose.add(scrollPurpose, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelScope = new JPanel();
		panelScope.setBorder(new TitledBorder(null, "Scope", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelScope.setBounds(10, 110, 607, 100); // jarak kiri, jarak atas,
													// lebar content, tinggi
													// content
		panelScope.setLayout(new BorderLayout(0, 0));

		txtScope = new JTextArea();

		JScrollPane scrollScope = new JScrollPane(txtScope);
		scrollScope.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelScope.add(scrollScope, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelDefinition = new JPanel();
		panelDefinition
				.setBorder(new TitledBorder(null, "Definition", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDefinition.setBounds(10, 210, 607, 100); // jarak kiri, jarak atas,
														// lebar content, tinggi
														// content
		panelDefinition.setLayout(new BorderLayout(0, 0));

		txtDefinition = new JTextArea();

		JScrollPane scrollDefinition = new JScrollPane(txtDefinition);
		scrollDefinition.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDefinition.add(scrollDefinition, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelReferences = new JPanel();
		panelReferences
				.setBorder(new TitledBorder(null, "References", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReferences.setBounds(10, 310, 607, 100); // jarak kiri, jarak atas,
														// lebar content, tinggi
														// content
		panelReferences.setLayout(new BorderLayout(0, 0));

		txtReferences = new JTextArea();

		JScrollPane scrollReferences = new JScrollPane(txtReferences);
		scrollReferences.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelReferences.add(scrollReferences, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelOverview = new JPanel();
		panelOverview.setBorder(new TitledBorder(null, "Overview", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelOverview.setBounds(10, 410, 607, 100); // jarak kiri, jarak atas,
													// lebar content, tinggi
													// content
		panelOverview.setLayout(new BorderLayout(0, 0));

		txtOverview = new JTextArea();

		JScrollPane scrollOverview = new JScrollPane(txtOverview);
		scrollOverview.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelOverview.add(scrollOverview, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------

		panelIntroduction.add(panelPurpose);
		panelIntroduction.add(panelScope);
		panelIntroduction.add(panelDefinition);
		panelIntroduction.add(panelReferences);
		panelIntroduction.add(panelOverview);
		tabbedPane.addTab("Introduction", panelIntroduction);
		// Panel
		// 2============================================================================================================================
		JPanel panelGeneralDesc = new JPanel();
		panelGeneralDesc.setLayout(null);

		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelPerspective = new JPanel();
		panelPerspective.setBorder(
				new TitledBorder(null, "Product Perspective", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelPerspective.setBounds(10, 10, 607, 100); // jarak kiri, jarak atas,
														// lebar content, tinggi
														// content
		panelPerspective.setLayout(new BorderLayout(0, 0));

		txtPerspective = new JTextArea();

		JScrollPane scrollPerspective = new JScrollPane(txtPerspective);
		scrollPerspective.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelPerspective.add(scrollPerspective, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelFunctions = new JPanel();
		panelFunctions.setBorder(
				new TitledBorder(null, "Product Functions", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelFunctions.setBounds(10, 110, 607, 100); // jarak kiri, jarak atas,
														// lebar content, tinggi
														// content
		panelFunctions.setLayout(new BorderLayout(0, 0));

		txtFunctions = new JTextArea();

		JScrollPane scrollFunctions = new JScrollPane(txtFunctions);
		scrollFunctions.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelFunctions.add(scrollFunctions, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelCharacteristics = new JPanel();
		panelCharacteristics.setBorder(
				new TitledBorder(null, "User Characteristics", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelCharacteristics.setBounds(10, 210, 607, 100); // jarak kiri, jarak
															// atas, lebar
															// content, tinggi
															// content
		panelCharacteristics.setLayout(new BorderLayout(0, 0));

		txtCharacteristics = new JTextArea();

		JScrollPane scrollCharacteristics = new JScrollPane(txtCharacteristics);
		scrollCharacteristics.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCharacteristics.add(scrollCharacteristics, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelConstraints = new JPanel();
		panelConstraints.setBorder(
				new TitledBorder(null, "General Constraints", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelConstraints.setBounds(10, 310, 607, 100); // jarak kiri, jarak
														// atas, lebar content,
														// tinggi content
		panelConstraints.setLayout(new BorderLayout(0, 0));

		txtConstraints = new JTextArea();

		JScrollPane scrollConstraints = new JScrollPane(txtConstraints);
		scrollConstraints.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelConstraints.add(scrollConstraints, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelDependencies = new JPanel();
		panelDependencies.setBorder(new TitledBorder(null, "Assumptions and Dependencies", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		panelDependencies.setBounds(10, 410, 607, 100); // jarak kiri, jarak
														// atas, lebar content,
														// tinggi content
		panelDependencies.setLayout(new BorderLayout(0, 0));

		txtDependencies = new JTextArea();

		JScrollPane scrollDependencies = new JScrollPane(txtDependencies);
		scrollDependencies.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDependencies.add(scrollDependencies, BorderLayout.CENTER);
		// --------------------------------------------------------------------------------------------------------------------

		panelGeneralDesc.add(panelPerspective);
		panelGeneralDesc.add(panelFunctions);
		panelGeneralDesc.add(panelCharacteristics);
		panelGeneralDesc.add(panelConstraints);
		panelGeneralDesc.add(panelDependencies);
		tabbedPane.addTab("General Description", panelGeneralDesc);
		// Panel
		// 3============================================================================================================================
		JPanel panelFunctionalReq = new JPanel();
		panelFunctionalReq.setLayout(new BorderLayout(0, 0));

		JPanel panelFirstSFR = new JPanel();
		panelFirstSFR.setBorder(new TitledBorder(null, "First : Please Choose Step Number", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelFirstSFR.setLayout(new BorderLayout(0, 0));

		JPanel panelInFirstSFR = new JPanel();
		panelInFirstSFR.setBorder(new EmptyBorder(10, 23, 10, 150));
		panelInFirstSFR.setLayout(new BoxLayout(panelInFirstSFR, BoxLayout.X_AXIS));

		JLabel lblStepNumber = new JLabel("Step Number : ");
		lblStepNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInFirstSFR.add(lblStepNumber);

		comboBoxStep = new JComboBox();
		panelInFirstSFR.add(comboBoxStep);

		btnAddNewStep = new JButton("Add New Step");
		btnAddNewStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAddStep fAddStep = new FormAddStep(FormSRS.this);
				fAddStep.setVisible(true);
			}
		});
		panelInFirstSFR.add(btnAddNewStep);

		panelFirstSFR.add(panelInFirstSFR);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelSecondSFR = new JPanel();
		panelSecondSFR.setBorder(new TitledBorder(null, "Second : Add the Details of Functional Requirements",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSecondSFR.setLayout(new BorderLayout(0, 0));

		JPanel panelInSecondSFR = new JPanel();
		panelInSecondSFR.setBorder(new EmptyBorder(10, 35, 10, 10)); // atas,
																		// kiri,
																		// bawah,
																		// kanan
		panelInSecondSFR.setLayout(null);

		JLabel lblName = new JLabel("Name : ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(20, 20, 86, 14);
		panelInSecondSFR.add(lblName);

		txtNameSFR = new JTextField();
		txtNameSFR.setBounds(106, 20, 262, 20);
		txtNameSFR.setColumns(10);
		panelInSecondSFR.add(txtNameSFR);

		JLabel lblDescSFR = new JLabel("Description : ");
		lblDescSFR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescSFR.setBounds(20, 63, 86, 14);
		panelInSecondSFR.add(lblDescSFR);

		JPanel panelDescSFR = new JPanel();
		panelDescSFR.setBounds(106, 63, 501, 125);// atas, kiri, bawah, kanan
		panelDescSFR.setLayout(new BorderLayout(0, 0)); // border layout

		txtDescSFR = new JTextArea();

		JScrollPane scrollDescSFR = new JScrollPane(txtDescSFR);
		scrollDescSFR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDescSFR.add(scrollDescSFR);

		panelInSecondSFR.add(panelDescSFR);

		JLabel lblFitCriteriaSFR = new JLabel("Fit Criteria : ");
		lblFitCriteriaSFR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFitCriteriaSFR.setBounds(20, 212, 86, 14);
		panelInSecondSFR.add(lblFitCriteriaSFR);

		JPanel panelCriteriaSFR = new JPanel();
		panelCriteriaSFR.setBounds(106, 212, 501, 125);// atas, kiri, bawah,
														// kanan
		panelCriteriaSFR.setLayout(new BorderLayout(0, 0)); // border layout

		txtCriteriaSFR = new JTextArea();

		JScrollPane scrollCriteriaSFR = new JScrollPane(txtCriteriaSFR);
		scrollCriteriaSFR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCriteriaSFR.add(scrollCriteriaSFR);

		panelInSecondSFR.add(panelCriteriaSFR);
		panelSecondSFR.add(panelInSecondSFR);
		// --------------------------------------------------------------------------------------------------------------------
		panelFunctionalReq.add(panelFirstSFR, BorderLayout.NORTH);
		panelFunctionalReq.add(panelSecondSFR, BorderLayout.CENTER);
		tabbedPane.addTab("Functional Requirement", panelFunctionalReq);
		// Panel
		// 4============================================================================================================================
		JPanel panelNonFunctionalReq = new JPanel();
		panelNonFunctionalReq.setLayout(new BorderLayout(0, 0));

		JPanel panelFirstNFR = new JPanel();
		panelFirstNFR.setBorder(new TitledBorder(null, "First : Please Choose the Category", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelFirstNFR.setLayout(new BorderLayout(0, 0));

		JPanel panelInFirstNFR = new JPanel();
		panelInFirstNFR.setBorder(new EmptyBorder(10, 46, 10, 153));
		panelInFirstNFR.setLayout(new BoxLayout(panelInFirstNFR, BoxLayout.X_AXIS));

		JLabel lblCategory = new JLabel("Category : ");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInFirstNFR.add(lblCategory);

		comboBoxCat = new JComboBox();
		panelInFirstNFR.add(comboBoxCat);

		btnAddCat = new JButton("Add Category");
		btnAddCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAddCat fAddCat = new FormAddCat(FormSRS.this);
				fAddCat.setVisible(true);
			}
		});
		panelInFirstNFR.add(btnAddCat);

		panelFirstNFR.add(panelInFirstNFR);
		// --------------------------------------------------------------------------------------------------------------------
		JPanel panelSecondNFR = new JPanel();
		panelSecondNFR.setBorder(new TitledBorder(null, "Second : Add the Details of Non Functional Requirements",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSecondNFR.setLayout(new BorderLayout(0, 0));

		JPanel panelInSecondNFR = new JPanel();
		panelInSecondNFR.setBorder(new EmptyBorder(10, 35, 10, 10)); // atas,
																		// kiri,
																		// bawah,
																		// kanan
		panelInSecondNFR.setLayout(null);

		JLabel lblNameNFR = new JLabel("Name : ");
		lblNameNFR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNameNFR.setBounds(20, 20, 86, 14);
		panelInSecondNFR.add(lblNameNFR);

		txtNameNFR = new JTextField();
		txtNameNFR.setBounds(106, 20, 262, 20);
		txtNameNFR.setColumns(10);
		panelInSecondNFR.add(txtNameNFR);

		JLabel lblDescNFR = new JLabel("Description : ");
		lblDescNFR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescNFR.setBounds(20, 63, 86, 14);
		panelInSecondNFR.add(lblDescNFR);

		JPanel panelDescNFR = new JPanel();
		panelDescNFR.setBounds(106, 63, 501, 125);// atas, kiri, bawah, kanan
		panelDescNFR.setLayout(new BorderLayout(0, 0)); // border layout

		txtDescNFR = new JTextArea();

		JScrollPane scrollDescNFR = new JScrollPane(txtDescNFR);
		scrollDescNFR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDescNFR.add(scrollDescNFR);

		panelInSecondNFR.add(panelDescNFR);

		JLabel lblFitCriteriaNFR = new JLabel("Fit Criteria : ");
		lblFitCriteriaNFR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFitCriteriaNFR.setBounds(20, 212, 86, 14);
		panelInSecondNFR.add(lblFitCriteriaNFR);

		JPanel panelCriteriaNFR = new JPanel();
		panelCriteriaNFR.setBounds(106, 212, 501, 125);// atas, kiri, bawah,
														// kanan
		panelCriteriaNFR.setLayout(new BorderLayout(0, 0)); // border layout

		txtCriteriaNFR = new JTextArea();

		JScrollPane scrollCriteriaNFR = new JScrollPane(txtCriteriaNFR);
		scrollCriteriaNFR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCriteriaNFR.add(scrollCriteriaNFR);

		panelInSecondNFR.add(panelCriteriaNFR);

		panelSecondNFR.add(panelInSecondNFR);
		panelNonFunctionalReq.add(panelFirstNFR, BorderLayout.NORTH);
		panelNonFunctionalReq.add(panelSecondNFR, BorderLayout.CENTER);
		tabbedPane.addTab("Non Functional Requirement", panelNonFunctionalReq);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		// ============================================================================================================================
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new EmptyBorder(5, 0, 5, 0)); // atas, kiri,
															// bawah, kanan

		btnSaveToDatabase = new JButton("Save To Database");
		btnSaveToDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isValidSRS)
					srsADD();
				choice = tabbedPane.getSelectedIndex();
				System.out.println("Choice Tab : " + choice);
				if (isValidSRS)
					if (choice == 0)
						addIntro(1);
					else if (choice == 1)
						addDesc(1);
					else if (choice == 2)
						addSFR(1);
					else if (choice == 3)
						addNFR(1);
			}
		});
		panelButton.add(btnSaveToDatabase);

		btnSaveToXml = new JButton("Save To XML File");
		btnSaveToXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isValidSRS)
					srsADD();
				choice = tabbedPane.getSelectedIndex();
				String fileXML = dirName + fileName + ".xml";
				if (isValidSRS) {
					if (choice == 0) {
						addIntro(2);
					} else if (choice == 1) {
						addDesc(2);
					} else if (choice == 2) {				
						addSFR(2);
					} else if (choice == 3) {
						addNFR(2);
					}

					jaxbObjectToXML(mySRS, fileXML);

					MySRS objSRS = jaxbXMLToObject(fileXML);
					System.out.println(objSRS.toString());
				}
			}
		});
		panelButton.add(btnSaveToXml);

		contentPane.add(panelButton, BorderLayout.SOUTH);
	}

	public void setLoginUserName() {
		String fName = theUser.getFirstName();
		String lName = theUser.getLastName();
		lblCreatedBy.setText("Created By : " + fName + " " + lName);
	}

	public void setIsAdmin() {
		boolean isAdmin = theUser.isAdmin();
		btnAddNewStep.setEnabled(isAdmin);
		btnAddCat.setEnabled(isAdmin);
	}

	private void srsADD() {
		int idUser = theUser.getId();
		String srsName = txtSRSName.getText();
		if (srsName.equals(""))
			JOptionPane.showMessageDialog(this, "Input SRS Name.!");
		else {
			theSRS = new SRS(idUser, srsName);
			boolean result = srsDAO.addSRS(theSRS);
			if (result) {
				int srsID = srsDAO.getSRSID(srsName);
				if (srsID != 0)
					theSRS.setSrs_id(srsID);
				fileName = theSRS.getSrs_name();
				isValidSRS = true;
				txtSRSName.setEditable(false);
			}
		}
	}

	private void addIntro(int mode) {
		String purpose = txtPurpose.getText();
		String scope = txtScope.getText();
		String definition = txtDefinition.getText();
		String references = txtReferences.getText();
		String overview = txtOverview.getText();
		if (purpose.equals("") || scope.equals("") || definition.equals("") || references.equals("")
				|| overview.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Input All TextField at Introduction..!");
		} else {
			theIntro = new Introduction(theSRS.getSrs_id(), purpose, scope, definition, references, overview);
			if (mode == 1) {
				if (!isValidIntro) {
					boolean result = srsDAO.addIntro(theIntro);
					if (result) {
						isValidIntro = true;
						JOptionPane.showMessageDialog(this, "Succes add Introduction SRS to " + theSRS.getSrs_name());
					}
				} else {
					boolean result = srsDAO.updateIntro(theIntro);
					if (result) {
						JOptionPane.showMessageDialog(this,
								"Succes update Introduction SRS to " + theSRS.getSrs_name());
					}
				}
			} else if (mode == 2) {
				mySRS.setIntro(theIntro);
				JOptionPane.showMessageDialog(this,
						"Succes create XML File Introduction SRS to " + theSRS.getSrs_name());
			}
		}
	}

	private void addDesc(int mode) {
		String perspective = txtPerspective.getText();
		String functions = txtFunctions.getText();
		String characteristic = txtCharacteristics.getText();
		String constraints = txtConstraints.getText();
		String dependencies = txtDependencies.getText();
		if (perspective.equals("") || functions.equals("") || characteristic.equals("") || constraints.equals("")
				|| dependencies.equals("")) {
			JOptionPane.showMessageDialog(this, "Please Input All TextField at General Description..!");
		} else {
			theDesc = new Description(theSRS.getSrs_id(), perspective, functions, characteristic, constraints,
					dependencies);
			if (mode == 1) {
				if (!isValidDesc) {
					boolean result = srsDAO.addDesc(theDesc);
					if (result) {
						isValidDesc = true;
						JOptionPane.showMessageDialog(this,
								"Succes add General Description SRS to " + theSRS.getSrs_name());
					}
				} else {
					boolean result = srsDAO.updateDesc(theDesc);
					if (result) {
						JOptionPane.showMessageDialog(this,
								"Succes update General Description SRS to " + theSRS.getSrs_name());
					}
				}
			} else if (mode == 2) {
				mySRS.setGen_des(theDesc);
				JOptionPane.showMessageDialog(this,
						"Succes create XML File General Description SRS to " + theSRS.getSrs_name());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void populateStep(List<Step> step) {
		comboBoxStep.setModel(new DefaultComboBoxModel(step.toArray(new Step[0])));
	}

	public void refreshStepView() {
		try {
			// Get Step
			List<Step> step = srsDAO.getStep();
			// create the model and update the "step combobox"
			populateStep(step);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error refreshStepView : " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	public void populateCat(List<Cat> cat) {
		comboBoxCat.setModel(new DefaultComboBoxModel(cat.toArray(new Cat[0])));
	}

	public void refreshCatView() {
		try {
			// Get Cat
			List<Cat> cat = srsDAO.getCat();
			// create the model and update the "cat combobox"
			populateCat(cat);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error refreshCatView : " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addSFR(int mode) {
		// get the step id
		if (comboBoxStep.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Silahkan pilih Step terlebih dahulu.", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			Step theStep = (Step) comboBoxStep.getSelectedItem();
			int step_id = theStep.getStep_id();
			int srs_id = theSRS.getSrs_id();
			String nameSFR = txtNameSFR.getText();
			String descSFR = txtDescSFR.getText();
			String criteriaSFR = txtCriteriaSFR.getText();
			if (nameSFR.equals("") || descSFR.equals("") || criteriaSFR.equals("")) {
				JOptionPane.showMessageDialog(this, "Please Input All TextField at Functional Requirement..!");
			} else {
				boolean cekStep = srsDAO.cekStep(srs_id, step_id, nameSFR);
				theSFR = new SFR(step_id, srs_id, nameSFR, descSFR, criteriaSFR);
				if (mode == 1) {
					if (cekStep) {
						JOptionPane.showMessageDialog(this, "SFR sudah pernah ditambahkan sebelumnya.!");
					} else {
						boolean result = srsDAO.addSFR(theSFR);
						if (result)
							JOptionPane.showMessageDialog(this,
									"Succes add Functional Requirement SRS to " + theSRS.getSrs_name());
					}
				} else if (mode == 2) {
					System.out.println("Klik Step : " + klikStep);
					if (klikStep == 0) {
						listStep = new ArrayList<Step>();
						listSFR = new ArrayList<SFR>();
						tempStep = new Step();
						listUseCase.add(use_case);
					}
					
					if (theStep.getStep_id() == tempStep.getStep_id() || klikStep == 0) {
						System.out.println("Step sama!");
						listSFR.add(theSFR);
						theStep.setListSFR(listSFR);
					} else {
						System.out.println("Step tidak sama!");								
						listStep.add(tempStep);	
						use_case.setListStep(listStep);
						fun_req.setListUseCase(listUseCase);
						specific_req.setFun_req(fun_req);
						mySRS.setSpecific_req(specific_req);
						
						listSFR = new ArrayList<SFR>();	
						listSFR.add(theSFR);
						theStep.setListSFR(listSFR);
					}					
					tempStep = theStep;
					klikStep++;
				}
			}
		}
	}

	private void addNFR(int mode) {
		// get the cat id
		if (comboBoxCat.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Silahkan pilih Category terlebih dahulu.", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			Cat theCat = (Cat) comboBoxCat.getSelectedItem();
			int cat_id = theCat.getCat_id();
			int srs_id = theSRS.getSrs_id();
			String nameNFR = txtNameNFR.getText();
			String descNFR = txtDescNFR.getText();
			String criteriaNFR = txtCriteriaNFR.getText();
			if (nameNFR.equals("") || descNFR.equals("") || criteriaNFR.equals("")) {
				JOptionPane.showMessageDialog(this, "Please Input All TextField at Non Functional Requirement..!");
			} else {
				boolean cekCat = srsDAO.cekCat(srs_id, cat_id, nameNFR);
				theNFR = new NFR(cat_id, srs_id, nameNFR, descNFR, criteriaNFR);
				if (mode == 1) {
					if (cekCat) {
						JOptionPane.showMessageDialog(this, "NFR sudah pernah ditambahkan sebelumnya.!");
					} else {
						boolean result = srsDAO.addNFR(theNFR);
						if (result)
							JOptionPane.showMessageDialog(this,
									"Succes add Non Functional Requirement SRS to " + theSRS.getSrs_name());
					}
				}else if(mode == 2){
					System.out.println("Klik Cat : " + klikCat);
					if (klikCat == 0) {
						listCat = new ArrayList<Cat>();
						listNFR = new ArrayList<NFR>();
						tempCat = new Cat();
					}
					
					if (theCat.getCat_id() == tempCat.getCat_id() || klikCat == 0) {
						System.out.println("Cat sama!");
						listNFR.add(theNFR);
						theCat.setListNFR(listNFR);
					} else {
						System.out.println("Cat tidak sama!");								
						listCat.add(tempCat);	
						non_fun_req.setListCat(listCat);
						specific_req.setNon_fun_req(non_fun_req);
						mySRS.setSpecific_req(specific_req);
						
						listNFR = new ArrayList<NFR>();	
						listNFR.add(theNFR);
						theCat.setListNFR(listNFR);
					}					
					tempCat = theCat;
					klikCat++;
				}				
			}
		}
	}

	private void jaxbObjectToXML(MySRS mySRS, String fileXML) {
		try {
			JAXBContext context = JAXBContext.newInstance(MySRS.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			m.marshal(mySRS, System.out);

			// Write to File
			m.marshal(mySRS, new File(fileXML));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private MySRS jaxbXMLToObject(String fileXML) {
		try {
			JAXBContext context = JAXBContext.newInstance(MySRS.class);
			Unmarshaller un = context.createUnmarshaller();
			MySRS mySRS = (MySRS) un.unmarshal(new File(fileXML));
			return mySRS;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}

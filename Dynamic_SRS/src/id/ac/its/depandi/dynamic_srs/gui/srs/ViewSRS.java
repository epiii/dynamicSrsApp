package id.ac.its.depandi.dynamic_srs.gui.srs;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import id.ac.its.depandi.dynamic_srs.core.Cat;
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
import id.ac.its.depandi.dynamic_srs.table_model.SrsTableModel;
import id.ac.its.depandi.dynamic_srs.table_model.UserTableModel;

import javax.swing.JTable;

public class ViewSRS extends JFrame {

	private JPanel contentPane;
	private JTable srsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSRS frame = new ViewSRS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private User theUser;
	private SrsDAO srsDAO;
	private MySRS mySRS;
//	private FuncReq fun_req;
//	private ArrayList<UseCase> listUseCase;
//	private ArrayList<Step> listStep;
//	private ArrayList<SFR> listSFR;
//	private UseCase use_case;
//	private NonFuncReq non_fun_req;
//	private ArrayList<Cat> listCat;
//	private ArrayList<NFR> listNFR;
//	private SpesificReq specific_req;
	
	public void setLoginUserName() {
		String fName = theUser.getFirstName();
		String lName = theUser.getLastName();
//		lblCreatedBy.setText("Created By : " + fName + " " + lName);
	}

	public void setIsAdmin() {
		boolean isAdmin = theUser.isAdmin();
//		btnAddNewStep.setEnabled(isAdmin);
//		btnAddCat.setEnabled(isAdmin);
	}

	
	public ViewSRS(User theUser) {
		this();
		this.theUser = theUser;
		srsDAO = new SrsDAO();
		refreshSRSView(theUser);
		
//		setLoginUserName();
//		setIsAdmin();
//		refreshSRSView();
	}
	private void loadFHome(){
		dispose();
		FormHome fHome = new FormHome(theUser);
		fHome.setLoginUserName();
		fHome.pack();
		fHome.setLocationRelativeTo(null);
		fHome.setVisible(true);
	}
	public ViewSRS() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				loadFHome();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		srsTable = new JTable();
		contentPane.add(srsTable, BorderLayout.CENTER);
	}

	public void refreshSRSView(User theUser) {
		try {
			List<SRS> srs= srsDAO.getSrs(theUser.isAdmin(), theUser.getId());
			SrsTableModel model = new SrsTableModel(srs);
			srsTable.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


}

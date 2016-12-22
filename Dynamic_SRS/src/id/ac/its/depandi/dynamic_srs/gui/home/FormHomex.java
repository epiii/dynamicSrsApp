package id.ac.its.depandi.dynamic_srs.gui.home;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.gui.about.FormAbout;
import id.ac.its.depandi.dynamic_srs.gui.srs.FormSRS;
import id.ac.its.depandi.dynamic_srs.gui.user.FormViewUser;

public class FormHomex extends JFrame {

	private JPanel contentPane;
	
	private JLabel lblLoginAs;
	
	private ImageIcon imgSRS;
	private ImageIcon imgManageSRS;
	private ImageIcon imgUser;
	private ImageIcon imgAbout;
	
	private User theUser;

	public FormHomex(User theUser){
		this();
		this.theUser = theUser;
	}
	/**
	 * Create the frame.
	 */
	public FormHomex() {
		setTitle("Home Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelLogin = new JPanel();
		FlowLayout fl_panelLogin = (FlowLayout) panelLogin.getLayout();
		fl_panelLogin.setAlignment(FlowLayout.LEFT);
		panelLogin.setBorder(null);
		contentPane.add(panelLogin, BorderLayout.NORTH);
		
		lblLoginAs = new JLabel("Login As : ");
		lblLoginAs.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		panelLogin.add(lblLoginAs);
		
		JPanel panelMenu = new JPanel();
		FlowLayout fl_panelMenu = (FlowLayout) panelMenu.getLayout();
		fl_panelMenu.setHgap(15);
		fl_panelMenu.setVgap(30);
		panelMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		imgSRS = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/add_srs.png"));
		imgManageSRS = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/manage_srs.png"));
		imgUser = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/user.png"));
		imgAbout = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/about.png"));
		
		JLabel lblCreateNewSrs = new JLabel(imgSRS);
		lblCreateNewSrs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				FormSRS fSRS = new FormSRS(theUser);
				fSRS.setLocationRelativeTo(null);
				fSRS.setVisible(true);
			}
		});
		lblCreateNewSrs.setHorizontalTextPosition(JLabel.CENTER);
		lblCreateNewSrs.setVerticalTextPosition(JLabel.BOTTOM);
		lblCreateNewSrs.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblCreateNewSrs.setText("Create New SRS");
		panelMenu.add(lblCreateNewSrs);
		
		JLabel lblManageSrs = new JLabel(imgManageSRS);
		lblManageSrs.setHorizontalTextPosition(JLabel.CENTER);
		lblManageSrs.setVerticalTextPosition(JLabel.BOTTOM);
		lblManageSrs.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblManageSrs.setText("Manage SRS");
		panelMenu.add(lblManageSrs);
		
		JLabel lblManageUser = new JLabel(imgUser);
		lblManageUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				FormViewUser fViewUser = new FormViewUser(theUser);
				fViewUser.setLocationRelativeTo(null);
				fViewUser.setVisible(true);
			}
		});
		lblManageUser.setHorizontalTextPosition(JLabel.CENTER);
		lblManageUser.setVerticalTextPosition(JLabel.BOTTOM);
		lblManageUser.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblManageUser.setText("Manage User");
		panelMenu.add(lblManageUser);
		
		JLabel lblAbout = new JLabel(imgAbout);
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormAbout fAbout = new FormAbout();
				fAbout.setLocationRelativeTo(null);
				fAbout.setVisible(true);
			}
		});
		lblAbout.setHorizontalTextPosition(JLabel.CENTER);
		lblAbout.setVerticalTextPosition(JLabel.BOTTOM);
		lblAbout.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblAbout.setText("About Application");
		panelMenu.add(lblAbout);
		
		contentPane.add(panelMenu, BorderLayout.CENTER);
	}
	
	public void setLoginUserName() {
		String fName = theUser.getFirstName();
		String lName = theUser.getLastName();
		lblLoginAs.setText("Login As : " + fName + " " + lName);
	}
}

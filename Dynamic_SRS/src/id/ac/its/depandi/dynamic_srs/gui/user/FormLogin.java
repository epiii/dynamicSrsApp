package id.ac.its.depandi.dynamic_srs.gui.user;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.border.EtchedBorder;

import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.dao.UserDAO;
import id.ac.its.depandi.dynamic_srs.gui.home.FormHome;

public class FormLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JCheckBox cekPass;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private ImageIcon img;
	
	private UserDAO userDAO;
	
	private String PROP_FILE = "data/config.ini";
    private Properties properties;
    private String lblCekPass;

	/**
	 * Create the dialog.
	 */
	public FormLogin() {
		setTitle("User Login");
		setResizable(false);
		setBounds(100, 100, 450, 203);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.setLayout(null);
		{
			JLabel lblUsername = new JLabel("Username : ");
			lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setBounds(131, 27, 81, 17);
			contentPanel.add(lblUsername);
		}
		
		cekPass = new JCheckBox("Remember Password");
		cekPass.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		cekPass.setBounds(224, 98, 148, 23);
		contentPanel.add(cekPass);			
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		cekPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cekPass.isSelected()){
		            lblCekPass = "true";
		        }else{
		        	lblCekPass = "false";
		        }
			}
		});
		
		txtUsername = new JTextField();
		txtUsername.setBounds(222, 27, 177, 20);
		txtUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(131, 69, 81, 14);
		contentPanel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(222, 66, 177, 20);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtUsername.setToolTipText("<html><img src=" + getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/username.png") + "> Input Username !</html>");
        txtPassword.setToolTipText("<html><img src=" + getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/password.png") + "> Input Password !</html>");
		
		img = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/login.png"));
		JLabel lblIcon = new JLabel(img);
		lblIcon.setBounds(10, 0, 126, 137);
		contentPanel.add(lblIcon);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cekUserLogin();
					}
				});
			}
			{
				JButton closeButton = new JButton("Close");
				closeButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
				closeButton.setActionCommand("Close");
				buttonPane.add(closeButton);
				closeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
		
		loadConfig();
		ceklist();
	}
	
	@SuppressWarnings("deprecation")
	private void cekUserLogin(){
		// get the username
		String first_name = txtUsername.getText();
		
		// get the password
		String plainTextPassword = new String(txtPassword.getPassword());
		
		userDAO = new UserDAO();
		ArrayList<User> theUser = userDAO.getUserList(first_name);
		
		if(txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
		        JOptionPane.showMessageDialog(null,"Silahan Isi Username dan Password Anda !", "Info", JOptionPane.WARNING_MESSAGE);
	    }else{
	    	if(theUser.size() > 1){
				boolean result = false;
				for(int i = 0; i < theUser.size(); i++){
					theUser.get(i).setPassword(plainTextPassword);
					boolean isValid = cekUser(theUser.get(i));
					if(isValid){
						result = true;
					}
				}
				if(result == false){
					JOptionPane.showMessageDialog(this, "Password tidak cocok", "Password Tidak Valid", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText("");
					txtPassword.requestFocus();
				}
			}else if(theUser.size() == 1){
				theUser.get(0).setPassword(plainTextPassword);
				if(!cekUser(theUser.get(0))){
					JOptionPane.showMessageDialog(this, "Password tidak cocok", "Password Tidak Valid", JOptionPane.ERROR_MESSAGE);
					txtPassword.setText("");
					txtPassword.requestFocus();
				}
			}else{
				JOptionPane.showMessageDialog(this, "Username Tidak ditemukan", "User Tidak Valid", JOptionPane.ERROR_MESSAGE);
				txtUsername.setText("");
				txtPassword.setText("");
				txtUsername.requestFocus();
			}
	    }
	}
	
	private boolean cekUser(User theUser){
		boolean isValidPassword = userDAO.authenticate(theUser);
		if (isValidPassword) {
			setVisible(false);
			setProperties();
			FormHome fHome = new FormHome(theUser);
			fHome.setLoginUserName();
			fHome.pack();
			fHome.setLocationRelativeTo(null);
			fHome.setVisible(true);
		}
		return isValidPassword;
	}
	
	public Properties loadProperties(String sFile){
        Properties p = new Properties();
        try{
            try (FileInputStream in = new FileInputStream(sFile)) {
                p.load(in);
            }
        }catch (IOException iOException){
        	System.out.println("Tidak bisa meload User Login yang tersimpan !");
        }
        return p;
    }
	
	private void loadConfig(){
        properties = loadProperties(PROP_FILE);
        txtUsername.setText(properties.getProperty("Username"));
        txtPassword.setText(properties.getProperty("Password"));
        lblCekPass = properties.getProperty("Check");
    }
    
    private void ceklist(){
        File file = new File(PROP_FILE);
        if(file.exists()){
            if(lblCekPass.equals("true")){
            	cekPass.setSelected(true);
            }else{
            	cekPass.setSelected(false);
            }
        }else{
            System.out.println("File config.ini Tidak Ditemukan !");
        }
    }
    
    public void saveProperties(Properties p, String sFile)throws IOException{
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(sFile);
            p.store(out, "File Settingan User\nUsername dan Password User");
        }catch(FileNotFoundException ex){
        	System.out.println("Tidak bisa menyimpan User Login !");
        }finally{
             out.close();
        }
    }
    
    private void setProperties(){
    	String username = "", password = "", check = "false";
		if(cekPass.isSelected()){
			username = txtUsername.getText();
            password = new String(txtPassword.getPassword());
            check = lblCekPass;
		}
        try{
            Properties p = new Properties();
            p.setProperty("Username", username);
            p.setProperty("Password", password);
            p.setProperty("Check", check);
            saveProperties(p, PROP_FILE);
        }catch(IOException ex){
        	System.out.println("Tidak bisa menyimpan User Login !");
        }
    }

}

package id.ac.its.depandi.dynamic_srs.gui.user;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.dao.UserDAO;
import id.ac.its.depandi.dynamic_srs.gui.home.FormHome;
import id.ac.its.depandi.dynamic_srs.table_model.UserTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormViewUser extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogginAs;
	private JButton btnSearch;
	private JTable userTable;
	private JButton btnAddUser;
	private JButton btnDeleteUser;
	
	private UserDAO userDAO;
	private User theUser;
	private JTextField txtFirstName;
	
	public FormViewUser(User theUser){
		this();
		this.theUser = theUser;
		userDAO = new UserDAO();
		populateGui(theUser);
		refreshUsersView(theUser);
	}

	/**
	 * Create the frame.
	 */
	public FormViewUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				loadFHome();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 649, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTop = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelTop.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelTop, BorderLayout.NORTH);
		
		lblLogginAs = new JLabel("Loggin As : ");
		panelTop.add(lblLogginAs);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JScrollPane userScrollPane = new JScrollPane();
		panelCenter.add(userScrollPane, BorderLayout.CENTER);
			
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panelTable.setLayout(new BorderLayout(0, 0));
	
		userTable = new JTable();
		panelTable.add(userTable.getTableHeader(), BorderLayout.CENTER);
		panelTable.add(userTable);
		
		panelCenter.add(panelTable, BorderLayout.CENTER);
		
		JPanel panelSearch = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSearch.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelCenter.add(panelSearch, BorderLayout.NORTH);
		
		JLabel lblSearchUser = new JLabel("Enter FirstName ");
		panelSearch.add(lblSearchUser);
		
		txtFirstName = new JTextField();
		panelSearch.add(txtFirstName);
		txtFirstName.setColumns(11);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Get last name from the text field
				// Call DAO and get employees for the last name
				// If last name is empty, then get all employees
				// Print out employees								
				try {
					String firtName = txtFirstName.getText();

					List<User> users = null;

					if (firtName != null && firtName.trim().length() > 0) {
						users = userDAO.searchUsers(firtName);
					} else {
						users = userDAO.getAllUser();
					}
					
					// create the model and update the "table"
					UserTableModel model = new UserTableModel(users);
					
					userTable.setModel(model);
					
					/*for (User temp : users) {
						System.out.println(temp);
					}*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(FormViewUser.this, "Error Search User: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panelSearch.add(btnSearch);
		
		JPanel panelBottom = new JPanel();
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		
		btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormAddUser fAddUser = new FormAddUser(FormViewUser.this, userDAO);
				fAddUser.setVisible(true);
			}
		});
		panelBottom.add(btnAddUser);
		
		JButton btnUpdateUser = new JButton("Update User");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the selected item
				int row = userTable.getSelectedRow();			
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(FormViewUser.this, "You must select a user", "Error", JOptionPane.ERROR_MESSAGE);				
					return;
				}else{
					// get the current employee
					User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
					FormUpdateUser fUpdateUser = new FormUpdateUser(theUser, tempUser, FormViewUser.this, userDAO);
					fUpdateUser.setVisible(true);
				}
			}
		});
		panelBottom.add(btnUpdateUser);
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the selected item
				int row = userTable.getSelectedRow();			
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(FormViewUser.this, "You must select a user", "Error", JOptionPane.ERROR_MESSAGE);				
					return;
				}else{
					// get the current user
					User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
					if (JOptionPane.showConfirmDialog(FormViewUser.this, 
							"Are you sure delete this user?", "Really Deleted?", 
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						boolean result = userDAO.deleteUser(tempUser);
						if(result){
							// show success message
							JOptionPane.showMessageDialog(FormViewUser.this,"User has been deleted.", "User Deleted", JOptionPane.INFORMATION_MESSAGE);
							refreshUsersView(theUser);
						}else{
							JOptionPane.showMessageDialog(FormViewUser.this,"User has not been deleted.", "User Not Deleted", JOptionPane.ERROR_MESSAGE);
						}
				    }
				}
			}
		});
		panelBottom.add(btnDeleteUser);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the selected item
				int row = userTable.getSelectedRow();			
				// make sure a row is selected
				if (row < 0) {
					JOptionPane.showMessageDialog(FormViewUser.this, "You must select a user", "Error", JOptionPane.ERROR_MESSAGE);				
					return;
				}else{
					// get the current employee
					User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
					// create dialog
					FormChangePassword changePassword = new FormChangePassword(tempUser, userDAO);
					// show dialog
					changePassword.setVisible(true);
				}
			}
		});
		panelBottom.add(btnChangePassword);
	}
	
	public void populateGui(User theUser){
		lblLogginAs.setText("Login As : " + theUser.getFirstName() + " " + theUser.getLastName());
		btnAddUser.setEnabled(theUser.isAdmin());
		btnDeleteUser.setEnabled(theUser.isAdmin());
		txtFirstName.setEditable(theUser.isAdmin());
		btnSearch.setEnabled(theUser.isAdmin());
	}

	public void refreshUsersView(User theUser) {
		try {
			List<User> user = userDAO.getUsers(theUser.isAdmin(), theUser.getId());
			// create the model and update the "table"
			UserTableModel model = new UserTableModel(user);
			userTable.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadFHome(){
		dispose();
		FormHome fHome = new FormHome(theUser);
		fHome.setLoginUserName();
		fHome.pack();
		fHome.setLocationRelativeTo(null);
		fHome.setVisible(true);
	}
}

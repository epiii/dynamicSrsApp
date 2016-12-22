package id.ac.its.depandi.dynamic_srs.gui.user;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.dao.UserDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAddUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JCheckBox adminCheckBox;
	
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	
	private UserDAO userDAO;
	private FormViewUser fViewUser;
	
	public FormAddUser(FormViewUser fViewUser, UserDAO userDAO){
		this();
		this.fViewUser = fViewUser;
		this.userDAO = userDAO;
	}

	/**
	 * Create the dialog.
	 */
	public FormAddUser() {
		setTitle("Add User");
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC}));
		{
			JLabel lblNewLabel = new JLabel("First Name");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel, "2, 2, right, default");
		}
		{
			firstNameTextField = new JTextField();
			contentPanel.add(firstNameTextField, "4, 2, fill, default");
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Last Name");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			lastNameTextField = new JTextField();
			contentPanel.add(lastNameTextField, "4, 4, fill, default");
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Email");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			emailTextField = new JTextField();
			contentPanel.add(emailTextField, "4, 6, fill, default");
			emailTextField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Admin");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblNewLabel_3, "2, 8");
		}
		{
			adminCheckBox = new JCheckBox("");
			contentPanel.add(adminCheckBox, "4, 8");			
		}
		{
			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(passwordLabel, "2, 10, right, default");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 10, fill, default");
		}
		{
			JLabel confirmPasswordLabel = new JLabel("Confirm Password");
			confirmPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(confirmPasswordLabel, "2, 12, right, default");
		}
		{
			confirmPasswordField = new JPasswordField();
			contentPanel.add(confirmPasswordField, "4, 12, fill, default");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						addUser();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void addUser() {
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String email = emailTextField.getText();
		boolean admin = adminCheckBox.isSelected();	
		String password = new String(passwordField.getPassword());
		String confirmPassword = new String(confirmPasswordField.getPassword());

		if (!password.equals(confirmPassword)) {
			JOptionPane.showMessageDialog(this,
					"Passwords do not match.", "Error",
					JOptionPane.ERROR_MESSAGE);				
			return;
		}
		
		User theUser = new User(lastName, firstName, email, password, admin);

		try {
			// save to the database
			userDAO.addUser(theUser);

			// close dialog
			setVisible(false);			

			// show success message
			JOptionPane.showMessageDialog(fViewUser,
					"User saved succesfully.", "User Saved",
					JOptionPane.INFORMATION_MESSAGE);
			
			// refresh gui list
			fViewUser.refreshUsersView(theUser);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(fViewUser,
					"Error saving user: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}

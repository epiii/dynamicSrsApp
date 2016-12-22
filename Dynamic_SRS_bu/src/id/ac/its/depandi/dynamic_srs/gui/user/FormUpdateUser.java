package id.ac.its.depandi.dynamic_srs.gui.user;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class FormUpdateUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField emailTextField;
	private JCheckBox adminCheckBox;
	
	private User previousUser;
	private User loginUser;
	private UserDAO userDAO;
	private FormViewUser fViewUser;
	
	public FormUpdateUser(User loginUser, User previousUser, FormViewUser fViewUser, UserDAO userDAO){
		this();
		this.loginUser = loginUser;
		this.previousUser = previousUser;
		this.fViewUser = fViewUser;
		this.userDAO = userDAO;
		populateGui(loginUser, previousUser);
	}

	/**
	 * Create the dialog.
	 */
	public FormUpdateUser() {
		setTitle("Update User");
		setBounds(100, 100, 450, 192);
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
			JLabel lblAdmin = new JLabel("Admin");
			lblAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAdmin, "2, 8");
		}
		{
			adminCheckBox = new JCheckBox("");
			contentPanel.add(adminCheckBox, "4, 8");			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateUser();
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
	
	private void populateGui(User loginUser, User previousUser){
		firstNameTextField.setText(previousUser.getFirstName());
		lastNameTextField.setText(previousUser.getLastName());
		emailTextField.setText(previousUser.getEmail());
		adminCheckBox.setSelected(previousUser.isAdmin());
		adminCheckBox.setEnabled(loginUser.isAdmin());
	}

	private void updateUser(){
		String firstName = firstNameTextField.getText();
		String lastName = lastNameTextField.getText();
		String email = emailTextField.getText();
		boolean admin = adminCheckBox.isSelected();
		User theUser = previousUser;
		theUser.setLastName(lastName);
		theUser.setFirstName(firstName);
		theUser.setEmail(email);
		theUser.setAdmin(admin);
		try {
			// save to the database
			userDAO.updateUser(theUser);

			// close dialog
			setVisible(false);			

			// show success message
			JOptionPane.showMessageDialog(fViewUser,
					"User update succesfully.", "User Updated",
					JOptionPane.INFORMATION_MESSAGE);
			
			// check to see if we need to update GUI fields for current user
			if (loginUser.getId() == theUser.getId()) {
				loginUser.setAdmin(admin);
				fViewUser.populateGui(theUser);
			}
			
			// refresh gui list
			fViewUser.refreshUsersView(theUser);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(fViewUser,
					"Error updating user: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

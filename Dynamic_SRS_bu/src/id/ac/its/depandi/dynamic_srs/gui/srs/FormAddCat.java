package id.ac.its.depandi.dynamic_srs.gui.srs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import id.ac.its.depandi.dynamic_srs.core.Cat;
import id.ac.its.depandi.dynamic_srs.dao.CatDAO;

public class FormAddCat extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCat;
	private CatDAO catDAO;
	private Cat theCat;
	private FormSRS fSRS;
	
	public FormAddCat(FormSRS fSRS){
		this();
		this.fSRS = fSRS;
	}
	/**
	 * Create the dialog.
	 */
	public FormAddCat() {
		setResizable(false);
		setBounds(100, 100, 402, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblStepName = new JLabel("Category Name");
			lblStepName.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
			contentPanel.add(lblStepName);
		}
		{
			txtCat = new JTextField();
			txtCat.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
			contentPanel.add(txtCat);
			txtCat.setColumns(40);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertButton = new JButton("Insert");
				insertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addNewCat();				
					}
				});
				insertButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				insertButton.setActionCommand("OK");
				buttonPane.add(insertButton);
				getRootPane().setDefaultButton(insertButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void addNewCat(){
		catDAO = new CatDAO();
		String catName = txtCat.getText();
		if(catName.equals(""))
			JOptionPane.showMessageDialog(this, "Please Input New Category.!");
		else{
			theCat = new Cat(catName);
			boolean result = catDAO.addCat(theCat);
			if(result){
				JOptionPane.showMessageDialog(this, "New Category Success Added.!");
				dispose();
				fSRS.refreshCatView();
			}else
				JOptionPane.showMessageDialog(this, "Please Input Other New Category.!");
		}
	}
}

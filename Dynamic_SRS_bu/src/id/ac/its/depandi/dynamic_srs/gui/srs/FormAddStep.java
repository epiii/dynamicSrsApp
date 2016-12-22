package id.ac.its.depandi.dynamic_srs.gui.srs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import id.ac.its.depandi.dynamic_srs.core.Step;
import id.ac.its.depandi.dynamic_srs.dao.StepDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAddStep extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStep;
	private StepDAO stepDAO;
	private Step theStep;
	private FormSRS fSRS;
	
	public FormAddStep(FormSRS fSRS){
		this();
		this.fSRS = fSRS;
	}

	/**
	 * Create the dialog.
	 */
	public FormAddStep() {
		setResizable(false);
		setBounds(100, 100, 402, 120);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblStepName = new JLabel("Step Name");
			lblStepName.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
			contentPanel.add(lblStepName);
		}
		{
			txtStep = new JTextField();
			txtStep.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
			contentPanel.add(txtStep);
			txtStep.setColumns(40);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertButton = new JButton("Insert");
				insertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addNewStep();				
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

	private void addNewStep(){
		stepDAO = new StepDAO();
		String stepName = txtStep.getText();
		if(stepName.equals(""))
			JOptionPane.showMessageDialog(this, "Please Input New Step.!");
		else{
			theStep = new Step(stepName);
			boolean result = stepDAO.addStep(theStep);
			if(result){
				JOptionPane.showMessageDialog(this, "New Step Success Added.!");
				dispose();
				fSRS.refreshStepView();
			}else
				JOptionPane.showMessageDialog(this, "Please Input Other New Step.!");
		}
	}
}

package id.ac.its.depandi.dynamic_srs.gui.about;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class FormAbout extends JDialog {

	/**
	 * Create the frame.
	 */
	public FormAbout() {
		setTitle("About Author");
		setBounds(100, 100, 317, 432);
		ImageIcon img = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/about.jpg"));
		JLabel label = new JLabel(img);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		getContentPane().add(label);
	}

}

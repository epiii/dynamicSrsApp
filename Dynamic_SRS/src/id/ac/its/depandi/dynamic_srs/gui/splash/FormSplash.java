package id.ac.its.depandi.dynamic_srs.gui.splash;

import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.*;

import id.ac.its.depandi.dynamic_srs.dao.DAOConnection;
import id.ac.its.depandi.dynamic_srs.gui.user.FormLogin;

import java.awt.Color;
import java.awt.Font;

import id.ac.its.depandi.dynamic_srs.util.ReadProperties;

public class FormSplash extends JWindow {

	private static JProgressBar progressBar = new JProgressBar();
	public static FormSplash splashScreen;
	private static Timer timer;
	private static int progress = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
	private JLabel strProgress;
	private JLabel imglabel;
	private ImageIcon img;
	
	private static final String filename = "data/config.properties";
	private String host;
	private String username;
	private String password;
	private String database;
	DAOConnection dao;
	private ReadProperties readFile;
	
	ActionListener aListener = new ActionListener() {
		@Override
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			progressBar.setValue(progress);
			if(progress == 30){
				strProgress.setText("Progress : Set Config File App..!");
		        readFile = new ReadProperties(filename);
		        host = readFile.getHost();
		        username = readFile.getUsername();
		        password = readFile.getPassword();
		        database = readFile.getDatabase();
		        System.out.println(readFile);
			}else if(progress == 60){
				strProgress.setText("Progress : Connecting to database..!");
				dao = new DAOConnection(host, database, username, password);
			}else if(progress == 90){
				strProgress.setText("Progress : Prepare launch App..!");
			}
			if (PROGBAR_MAX == progress) {
				splashScreen.dispose();
				timer.stop();
				keFLogin();
			}
			progress++;// increase counter
		}
	};

	public FormSplash() {
		Container container = getContentPane();
		container.setLayout(null);
        
        strProgress = new JLabel("Progress : Running App..!");
        strProgress.setFont(new Font("Cambria", Font.BOLD, 12));
        strProgress.setForeground(Color.WHITE);
        strProgress.setBounds(32, 238, 280, 20);
        container.add(strProgress);
		progressBar.setBackground(Color.WHITE);
		progressBar.setForeground(Color.ORANGE);
		progressBar.setFont(new Font("Cambria", Font.BOLD, 11));

		progressBar.setMaximum(PROGBAR_MAX);
		progressBar.setBounds(30, 260, 400, 17);
		progressBar.setStringPainted(true);
		container.add(progressBar);
		
		img = new ImageIcon(getClass().getResource("/id/ac/its/depandi/dynamic_srs/img/splash.jpg"));
        imglabel = new JLabel(img);
        imglabel.setBounds(0, 0, 468, 300);
        container.add(imglabel);
		
		setSize(468, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		startProgressBar();
	}
	
	private void startProgressBar() {
		timer = new Timer(TIMER_PAUSE, aListener);
		timer.start();
	}
	
	private void keFLogin() {
		FormLogin dialog = new FormLogin();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}

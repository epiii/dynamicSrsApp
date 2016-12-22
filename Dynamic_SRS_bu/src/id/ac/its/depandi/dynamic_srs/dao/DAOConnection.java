package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DAOConnection extends JFrame{

	private static Connection koneksi;
	
	public static Connection getKoneksi() {
		return koneksi;
	}

	public DAOConnection(String host, String database, String username, String password){
		try{
			koneksi = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
			System.out.println("Connection to " + database + " success..!");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(this, "Unable to Connect", "Cannot Connect To Database : " + database, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}

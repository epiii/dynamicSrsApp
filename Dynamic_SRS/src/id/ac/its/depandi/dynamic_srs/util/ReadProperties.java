package id.ac.its.depandi.dynamic_srs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	private Properties properties;
	private String host;
	private String username;
	private String password;
	private String database;

	public ReadProperties(String filename) {
		File file = new File(filename);
		try {
			InputStream in = new FileInputStream(file);
			properties = new Properties();
			properties.load(in);
			host = properties.getProperty("host");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			database = properties.getProperty("database");
		} catch (Exception e) {
			System.out.println("Gagal Membaca File..!");
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public String getHost() {
		return host;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDatabase() {
		return database;
	}

	public String toString() {
		String result = "Host : " + host + "\n" + "Username : " + username + "\n" + "Password : " + password + "\n"
				+ "Database : " + database;
		return result;
	}
}

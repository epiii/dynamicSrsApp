package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import id.ac.its.depandi.dynamic_srs.core.Cat;

public class CatDAO {

	private Connection myConn;

	public CatDAO() {
		myConn = DAOConnection.getKoneksi();
		System.out.println("Permintaan koneksi Kelas CatDAO ok");
	}
	
	public boolean addCat(Cat theCat) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into cat" + " (cat_name)" + " values (?)");

			// set params
			myStmt.setString(1, theCat.getCat_name());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Category berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("Category tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addCat : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}
}

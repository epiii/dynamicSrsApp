package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import id.ac.its.depandi.dynamic_srs.core.Step;

public class StepDAO {

	private Connection myConn;

	public StepDAO() {
		myConn = DAOConnection.getKoneksi();
		System.out.println("Permintaan koneksi Kelas StepDAO ok");
	}
	
	public boolean addStep(Step theStep) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into step" + " (step_name)" + " values (?)");

			// set params
			myStmt.setString(1, theStep.getStep_name());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Step berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("Step tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addStep : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}
}

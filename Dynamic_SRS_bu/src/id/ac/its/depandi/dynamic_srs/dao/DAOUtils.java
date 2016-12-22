package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {
	
	public static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				
			}
			if (myConn != null) {
				myConn.close();
			}
		}catch(SQLException ex){
			System.out.println("Tidak dapat menutup statement : " + ex);
		}
	}

	public static void close(Statement myStmt, ResultSet myRs){
		close(null, myStmt, myRs);			
	}

	public static void close(Statement myStmt) {
		close(null, myStmt, null);		
	}
}


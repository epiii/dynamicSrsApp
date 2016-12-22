package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import id.ac.its.depandi.dynamic_srs.core.User;
import id.ac.its.depandi.dynamic_srs.util.PasswordUtils;

public class UserDAO {

	private Connection myConn;

	public UserDAO() {
		myConn = DAOConnection.getKoneksi();
		System.out.println("Permintaan koneksi Kelas UserDAO ok");
	}

	public ArrayList<User> getUserList(String first_name) {
		String query = "select * from users where first_name = '" + first_name + "';";
		Statement state = null;
		ResultSet res = null;
		ArrayList<User> userList = new ArrayList<>();
		try {
			state = myConn.createStatement();
			res = state.executeQuery(query);
			while (res.next()) {
				int id = res.getInt("id");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				String email = res.getString("email");
				String passwordEncrypt = res.getString("password");
				boolean isAdmin = res.getBoolean("is_admin");
				User user = new User(lastName, firstName, email, passwordEncrypt, isAdmin);
				user.setId(id);
				userList.add(user);
			}
		} catch (SQLException ex) {
			System.out.println("Error getUserList : " + ex);
		} finally {
			DAOUtils.close(state, res);
		}
		return userList;
	}

	public boolean authenticate(User theUser) {
		boolean result = false;

		String plainTextPassword = theUser.getPassword();

		// get the encrypted password from database for this user
		String encryptedPasswordFromDatabase = getEncrpytedPassword(theUser.getId());

		// compare the passwords
		result = PasswordUtils.checkPassword(plainTextPassword, encryptedPasswordFromDatabase);

		return result;
	}

	private String getEncrpytedPassword(int id) {
		String encryptedPassword = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select password from users where id =" + id);
			if (myRs.next())
				encryptedPassword = myRs.getString("password");
			else
				System.out.println("User id not found : " + id);
		} catch (SQLException e) {
			System.out.println("Error getEncrpytedPassword : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return encryptedPassword;
	}

	private User convertRowToUser(ResultSet myRs) {
		User tempUser = null;
		try {
			int id = myRs.getInt("id");
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			String email = myRs.getString("email");
			boolean admin = myRs.getBoolean("is_admin");
			tempUser = new User(id, lastName, firstName, email, admin);
		} catch (SQLException e) {
			System.out.println("Error convertRowToUser : " + e);
		}
		return tempUser;
	}

	public List<User> getUsers(boolean admin, int userId) {
		List<User> list = new ArrayList<User>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.createStatement();
			String sql = null;
			if (admin) {
				// get all users
				sql = "select * from users order by last_name";
			} else {
				// only the current user
				sql = "select * from users where id=" + userId + " order by last_name";
			}
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
		} catch (SQLException e) {
			System.out.println("Error getUsers : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return list;
	}

	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from users order by first_name");
			while (myRs.next()) {
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
		} catch (SQLException e) {
			System.out.println("Error getAllUser : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return list;
	}

	public List<User> searchUsers(String firstName) {
		List<User> list = new ArrayList<User>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			firstName += "%";
			myStmt = myConn.prepareStatement("select * from users where first_name like ?  order by first_name");
			myStmt.setString(1, firstName);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
		} catch (SQLException e) {
			System.out.println("Error searchUsers : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return list;
	}

	public void addUser(User theUser) {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into users" + " (first_name, last_name, email, password, is_admin)"
					+ " values (?, ?, ?, ?, ?)");

			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getEmail());

			// encrypt password
			String encryptedPassword = PasswordUtils.encryptPassword(theUser.getPassword());
			myStmt.setString(4, encryptedPassword);

			myStmt.setBoolean(5, theUser.isAdmin());

			// execute SQL
			myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error addUser : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
	}

	public void updateUser(User theUser) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement(
					"update users" + " set first_name=?, last_name=?, email=?, is_admin=?" + " where id=?");

			// set params
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getEmail());
			myStmt.setBoolean(4, theUser.isAdmin());
			myStmt.setInt(5, theUser.getId());

			// execute SQL
			myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error updateUser : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
	}

	public boolean deleteUser(User theUser) {
		boolean result = false;
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from users where id=?");
			// set param
			myStmt.setInt(1, theUser.getId());
			// execute SQL
			myStmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error deleteUser : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return result;
	}
	
	public void changePassword(User user) {
		// get plain text password
		String plainTextPassword = user.getPassword();
		// encrypt the password
		String encryptedPassword = PasswordUtils.encryptPassword(plainTextPassword);
		// update the password in the database
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update users"
					+ " set password=?"
					+ " where id=?");
			// set params
			myStmt.setString(1, encryptedPassword);
			myStmt.setInt(2, user.getId());
			// execute SQL
			myStmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error changePassword : " + e);
		}
		finally {
			DAOUtils.close(myStmt);
		}		
	}	
}

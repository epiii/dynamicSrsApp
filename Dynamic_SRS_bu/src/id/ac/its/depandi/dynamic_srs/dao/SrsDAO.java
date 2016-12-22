package id.ac.its.depandi.dynamic_srs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import id.ac.its.depandi.dynamic_srs.core.Cat;
import id.ac.its.depandi.dynamic_srs.core.Description;
import id.ac.its.depandi.dynamic_srs.core.Introduction;
import id.ac.its.depandi.dynamic_srs.core.NFR;
import id.ac.its.depandi.dynamic_srs.core.SFR;
import id.ac.its.depandi.dynamic_srs.core.SRS;
import id.ac.its.depandi.dynamic_srs.core.Step;

public class SrsDAO {

	private Connection myConn;

	public SrsDAO() {
		myConn = DAOConnection.getKoneksi();
		System.out.println("Permintaan koneksi Kelas SrsDAO ok");
	}

	public boolean addSRS(SRS theSRS) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into srs" + " (user_id, srs_name)" + " values (?, ?)");

			// set params
			myStmt.setInt(1, theSRS.getUser_id());
			myStmt.setString(2, theSRS.getSrs_name());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("SRS berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("SRS tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addSRS : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}

	public int getSRSID(String srsName) {
		int srsID = 0;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select srs_id from srs where srs_name='" + srsName + "'");
			if (myRs.next())
				srsID = myRs.getInt("srs_id");
			else
				System.out.println("SRS name not found: " + srsName);
			return srsID;
		} catch (SQLException e) {
			System.out.println("Error getSRSID : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return srsID;
	}

	public boolean addIntro(Introduction theIntro) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into introduction"
					+ " (srs_id, purpose, scope, definition, reference, overview)" + " values (?, ?, ?, ?, ?, ?)");

			// set params
			myStmt.setInt(1, theIntro.getSrs_id());
			myStmt.setString(2, theIntro.getPurpose());
			myStmt.setString(3, theIntro.getScope());
			myStmt.setString(4, theIntro.getDefinition());
			myStmt.setString(5, theIntro.getReference());
			myStmt.setString(6, theIntro.getOverview());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Introduction SRS berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("Introduction SRS tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addIntro : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}
	
	public boolean updateIntro(Introduction theIntro) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement(
					"update introduction" + " set purpose=?, scope=?, definition=?, reference=?, overview=?" + " where srs_id=?");

			// set params
			myStmt.setString(1, theIntro.getPurpose());
			myStmt.setString(2, theIntro.getScope());
			myStmt.setString(3, theIntro.getDefinition());
			myStmt.setString(4, theIntro.getReference());
			myStmt.setString(5, theIntro.getOverview());
			myStmt.setInt(6, theIntro.getSrs_id());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Introduction SRS berhasil diupdate.!");
				return true;
			} else {
				System.out.println("Introduction SRS tidak berhasil diupdate.!");
			}
		} catch (SQLException e) {
			System.out.println("Error updateIntro : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}

	public boolean addDesc(Description theDesc) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into description"
					+ " (srs_id, perspective, functions, characteristic, constraints, dependencies)"
					+ " values (?, ?, ?, ?, ?, ?)");

			// set params
			myStmt.setInt(1, theDesc.getSrs_id());
			myStmt.setString(2, theDesc.getPerspective());
			myStmt.setString(3, theDesc.getFunctions());
			myStmt.setString(4, theDesc.getCharacteristic());
			myStmt.setString(5, theDesc.getConstraints());
			myStmt.setString(6, theDesc.getDependencies());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("General Description SRS berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("General Description SRS tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addDesc : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}
	
	public boolean updateDesc(Description theDesc) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement(
					"update description" + " set perspective=?, functions=?, characteristic=?, constraints=?, dependencies=?" + " where srs_id=?");

			// set params
			myStmt.setString(1, theDesc.getPerspective());
			myStmt.setString(2, theDesc.getFunctions());
			myStmt.setString(3, theDesc.getCharacteristic());
			myStmt.setString(4, theDesc.getConstraints());
			myStmt.setString(5, theDesc.getDependencies());
			myStmt.setInt(6, theDesc.getSrs_id());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("General Description SRS berhasil diupdate.!");
				return true;
			} else {
				System.out.println("General Description SRS tidak berhasil diupdate.!");
			}
		} catch (SQLException e) {
			System.out.println("Error updateDesc : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}

	private Step convertRowToStep(ResultSet myRs) throws SQLException {
		int step_id = myRs.getInt("step_id");
		String step_name = myRs.getString("step_name");
		Step tempStep = new Step(step_id, step_name);
		return tempStep;
	}

	public List<Step> getStep() {
		List<Step> listStep = new ArrayList<Step>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.createStatement();
			String sql = "select * from step order by step_name";
			myRs = myStmt.executeQuery(sql);
			// get all step
			while (myRs.next()) {
				Step tempStep = convertRowToStep(myRs);
				listStep.add(tempStep);
			}
		} catch (SQLException e) {
			System.out.println("Error getStep : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return listStep;
	}

	private Cat convertRowToCat(ResultSet myRs) throws SQLException {
		int cat_id = myRs.getInt("cat_id");
		String cat_name = myRs.getString("cat_name");
		Cat tempCat = new Cat(cat_id, cat_name);
		return tempCat;
	}

	public List<Cat> getCat() {
		List<Cat> listCat = new ArrayList<Cat>();
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.createStatement();
			String sql = "select * from cat order by cat_name";
			myRs = myStmt.executeQuery(sql);
			// get all step
			while (myRs.next()) {
				Cat tempCat = convertRowToCat(myRs);
				listCat.add(tempCat);
			}
		} catch (SQLException e) {
			System.out.println("Error getCat : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return listCat;
	}

	public boolean addSFR(SFR theSFR) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into sfr" + " (step_id, srs_id, sfr_name, sfr_desc, sfr_fit_cri)"
					+ " values (?, ?, ?, ?, ?)");

			// set params
			myStmt.setInt(1, theSFR.getStep_id());
			myStmt.setInt(2, theSFR.getSrs_id());
			myStmt.setString(3, theSFR.getSfr_name());
			myStmt.setString(4, theSFR.getSfr_desc());
			myStmt.setString(5, theSFR.getSfr_fit_cri());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Functional Requirement SRS berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("Functional Requirement SRS tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addSFR : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}

	public boolean cekStep(int srs_id, int step_id, String nameSFR) {
		boolean result = false;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(
					"select sfr_id from sfr where srs_id = '" + srs_id + "' and step_id = '" + step_id + "' and sfr_name = '" + nameSFR + "'");
			if (myRs.next())
				result = true;
			else
				System.out.println("SFR belum pernah ditambahkan : " + step_id);
		} catch (SQLException e) {
			System.out.println("Error cekStep : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return result;
	}

	public boolean addNFR(NFR theNFR) {
		PreparedStatement myStmt = null;
		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into nfr" + " (cat_id, srs_id, nfr_name, nfr_desc, nfr_fit_cri)"
					+ " values (?, ?, ?, ?, ?)");

			// set params
			myStmt.setInt(1, theNFR.getCat_id());
			myStmt.setInt(2, theNFR.getSrs_id());
			myStmt.setString(3, theNFR.getNfr_name());
			myStmt.setString(4, theNFR.getNfr_desc());
			myStmt.setString(5, theNFR.getNfr_fit_cri());

			// execute SQL
			int result = myStmt.executeUpdate();
			if (result == 1) {
				System.out.println("Non Functional Requirement SRS berhasil ditambahkan.!");
				return true;
			} else {
				System.out.println("Non Functional Requirement SRS tidak berhasil ditambahkan.!");
			}
		} catch (SQLException e) {
			System.out.println("Error addNFR : " + e);
		} finally {
			DAOUtils.close(myStmt);
		}
		return false;
	}

	public boolean cekCat(int srs_id, int cat_id, String nameNFR) {
		boolean result = false;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(
					"select nfr_id from nfr where srs_id = '" + srs_id + "' and cat_id = '" + cat_id + "' and nfr_name = '" + nameNFR + "'");
			if (myRs.next())
				result = true;
			else
				System.out.println("NFR belum pernah ditambahkan : " + cat_id);
		} catch (SQLException e) {
			System.out.println("Error cekCat : " + e);
		} finally {
			DAOUtils.close(myStmt, myRs);
		}
		return result;
	}
}

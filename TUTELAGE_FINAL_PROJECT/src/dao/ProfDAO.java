package dao;

// Package for SQL class object
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import controller.ProfLoginController;
import model.Prof;

/**
 * This is a DAO class which contains the queries related to PROFESSOR table.
 *
 */
public class ProfDAO implements ProfService{

	// Objects has been created for Connection class, Statement, DbConnection and PROFESSOR class.
	DbConnection dbConn = new DbConnection();
	Connection con;
	Statement stmt = null;
	Prof prof = ProfLoginController.getprof();

/**
 * This method returns 'true' when new PROFESSOR was created and inserted their information
 * into PROFESSOR table.
 * 
 * @param firstName
 * @param lastName
 * @param username
 * @param password
 * @param profAddress1
 * @param profAddress2
 * @param city
 * @param state
 * @param postalCode
 * @param mobileNumber
 * @return
 */
	public boolean addProf(String firstName, String lastName, String username, String password, String profAddress1,
			String profAddress2, String city, String state, int postalCode, String mobileNumber) {
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to insert the PROFESSOR details into PROFESSOR table.
			String query = "INSERT INTO tutelage_prof (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE, PHONENUMBER)"
					+ " VALUES ('" + username + "','" + password + "','" + firstName + "','" + lastName + "','"
					+ profAddress1 + "','" + profAddress2 + "','" + city + "','" + state + "'," + postalCode + ",'"
					+ mobileNumber + "')";
			
			// Above query is executed.
			stmt.executeUpdate(query);
			System.out.println("New prof has been created.");
			// Connection is closed.
			con.close();

		} 
		// Returning False if SQL exception is caught.
		catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * This method is used to update the PROFESSOR information.
	 * When PROFESSOR updates his profile, then this method is called.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param profAddress1
	 * @param profAddress2
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param mobileNumber
	 * @return
	 */
	public boolean updateProfProfile(String firstName, String lastName, String username, String password,
			String profAddress1, String profAddress2, String city, String state, int postalCode, String mobileNumber) {
		try {
			int profID = prof.getProfID();
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to update the PROFESSOR details.
			String sql = "UPDATE tutelage_prof SET USERNAME ='" + username + "', PASSWORD ='" + password
					+ "', FIRSTNAME= '" + firstName + "'," + " LASTNAME ='" + lastName + "', ADDRESS1 ='" + profAddress1
					+ "', ADDRESS2 ='" + profAddress2 + "', CITY ='" + city + "', STATE ='" + state + "'," + " ZIPCODE ="
					+ postalCode + ", PHONENUMBER ='" + mobileNumber + "' WHERE PROFID =" + profID;

			System.out.println("Update Query: " + sql);

			stmt.executeUpdate(sql);
			System.out.println("PROFESSOR  profile has been updated");
			// Connection is closed.
			con.close();
		
		} 
		// Returning False if SQL exception is caught.
		catch (SQLException e) {
			e.getMessage();
			return false;
		}
		return true;
	}
	
}

package dao;

//Package for SQL class object
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Imported the required classes
import model.Administrator;
import model.Prof;
import model.Student;
import model.Login;

/**
 * This DAO class is used during login to check whether 
 * the user name and password is present in database or not. 
 */
public class LoginDAO {

	DbConnection dbConn = new DbConnection();
	Connection con;
	Statement stmt = null;
	ResultSet rs = null;

	/**
	 * This method is called when Administrator tries to login.
	 * User name and password is passed as a parameter to this method. 
	 * @param login
	 * @return
	 */
	public Administrator adminDetails(Login login) {
		
		Administrator administrator = new Administrator();
		System.out.println("Inside LoginDAO...");
		System.out.println("USERNAME: " + login.getUsername());
		System.out.println("PASSWORD: " + login.getPassword());

		try {
			// Establish the database connection.
			con = dbConn.getConnections();
			stmt = con.createStatement();
			System.out.println(stmt);
//			MOMMOM
//			SURSUR
			// Checks whether the given user name and password is present is ADMIN table.
			String selectAdmin = "SELECT * FROM tutelage_administrator WHERE username = '" + login.getUsername()
					+ "' AND password = '" + login.getPassword() + "'";

			rs = stmt.executeQuery(selectAdmin);

			/* If present, then the admin informations are stored in Administrator class. 
			   Else, it returns null. 
			     */
			if(rs.next()) {
				administrator.setAdminID(rs.getInt(1));
				administrator.setfName(rs.getString(2));
				administrator.setlName(rs.getString(3));
				administrator.setAdminUsername(rs.getString(4));
				administrator.setPassword(rs.getString(5));
			}
			else
				return null;

		} catch (SQLException e) {
			e.getMessage();
		}
		
		try {
			// Closes the opened connection.
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return administrator;
	}
	
	
	/**
	 * This method is called when Student tries to login.
	 * User name and password is passed as a parameter to this method. 
	 * @param login
	 * @return
	 */
	
	public Student studentDetails(Login login) {

		Student student = new Student();
		System.out.println("Inside LoginDAO...");
		System.out.println("USERNAME: " + login.getUsername());
		System.out.println("PASSWORD: " + login.getPassword());

		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			System.out.println(stmt);

			// Checks whether the given user name and password is present is STUDENT table.
			String selectStudent = "SELECT * FROM tutelage_Student WHERE username = '" + login.getUsername()
					+ "' AND password = '" + login.getPassword() + "'";

			rs = stmt.executeQuery(selectStudent);

			/* If present, then the Student informations are stored in Student class. 
			   Else, it returns null. 
			     */
			if(rs.next()) {
				student.setStudentID(rs.getInt(1));
				student.setUsername(rs.getString(2));
				student.setPassword(rs.getString(3));
				student.setFirstName(rs.getString(4));
				student.setLastName(rs.getString(5));
				student.setAddress1(rs.getString(6));
				student.setAddress2(rs.getString(7));
				student.setCity(rs.getString(8));
				student.setState(rs.getString(9));
				student.setZipCode(rs.getInt(10));
				student.setPhoneNumber(rs.getString(11));
			}
			else
				return null;

		} catch (SQLException e) {
			e.getMessage();
		}
		
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		
		// Returns Student object.
		return student;
	}


	
	/**
	 * This method is called when PROFESSOR tries to login.
	 * User name and password is passed as a parameter to this method. 
	 * @param login
	 * @return
	 */
	public Prof ProfDetails(Login login) {

		Prof prof = new Prof();
		System.out.println("Inside LoginDAO...");
		System.out.println("USERNAME: " + login.getUsername());
		System.out.println("PASSWORD: " + login.getPassword());

		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();
			System.out.println(stmt);

			// Checks whether the given user name and password is present is PROFESSOR table.
			String selectProf = "SELECT * FROM tutelage_prof WHERE username = '" + login.getUsername()
					+ "' AND password = '" + login.getPassword() + "'";

			rs = stmt.executeQuery(selectProf);

			/* If present, then the PROFESSOR informations are stored in PROFESSOR class. 
			   Else, it returns null. 
			     */
			if(rs.next()) {
				prof.setProfID(rs.getInt(1));
				prof.setUsername(rs.getString(2));
				prof.setPassword(rs.getString(3));
				prof.setFirstName(rs.getString(4));
				prof.setLastName(rs.getString(5));
				prof.setAddress1(rs.getString(6));
				prof.setAddress2(rs.getString(7));
				prof.setCity(rs.getString(8));
				prof.setState(rs.getString(9));
				prof.setZipCode(rs.getInt(10));
				prof.setPhoneNumber(rs.getString(11));
			}
			else
				return null;

		} catch (SQLException e) {
			e.getMessage();
		}
		
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		
		// Returns PROFESSOR object.
		return prof;
	}
}

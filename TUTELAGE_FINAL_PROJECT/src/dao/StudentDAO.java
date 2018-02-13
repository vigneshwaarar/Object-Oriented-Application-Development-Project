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

// Imported the required classes
import controller.StudentLoginController;

import model.Student;

/**
 * This is a DAO class which contains the queries related to Student table.
 *
 */
public class StudentDAO implements StudentService{

	// Objects has been created for Connection class, Statement, DbConnection and Student class.
	DbConnection dbConn = new DbConnection();
	Connection con;
	Statement stmt = null;
	Student std = StudentLoginController.getStudent();

/**
 * This method returns 'true' when new Student was created and inserted their information
 * into Student table.
 * 
 * @param firstName
 * @param lastName
 * @param username
 * @param password
 * @param stdAddress1
 * @param stdAddress2
 * @param city
 * @param state
 * @param postalCode
 * @param mobileNumber
 * @return
 */
	public boolean addStudent(String firstName, String lastName, String username, String password, String stdAddress1,
			String stdAddress2, String city, String state, int postalCode, String mobileNumber) {
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to insert the Student details into tutelage_Student table.
			String query = "INSERT INTO tutelage_Student (USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS1, ADDRESS2, CITY, STATE, ZIPCODE, PHONENUMBER)"
					+ " VALUES ('" + username + "','" + password + "','" + firstName + "','" + lastName + "','"
					+ stdAddress1 + "','" + stdAddress2 + "','" + city + "','" + state + "'," + postalCode + ",'"
					+ mobileNumber + "')";
			
			// Above query is executed.
			stmt.executeUpdate(query);
			System.out.println("New Student has been created.");
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
	 * This method is used to update the Student information.
	 * When Student updates his profile, then this method is called.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 * @param stdAddress1
	 * @param stdAddress2
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param mobileNumber
	 * @return
	 */
	public boolean updateStudentProfile(String firstName, String lastName, String username, String password,
			String stdAddress1, String stdAddress2, String city, String state, int postalCode, String mobileNumber) {
		try {
			int StudentID = std.getStudentID();
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to update the Student details.
			String sql = "UPDATE tutelage_Student SET USERNAME ='" + username + "', PASSWORD ='" + password
					+ "', FIRSTNAME= '" + firstName + "'," + " LASTNAME ='" + lastName + "', ADDRESS1 ='" + stdAddress1
					+ "', ADDRESS2 ='" + stdAddress2 + "', CITY ='" + city + "', STATE ='" + state + "'," + " ZIPCODE ="
					+ postalCode + ", PHONENUMBER ='" + mobileNumber + "' WHERE StudentID =" + StudentID;

			System.out.println("Update Query: " + sql);

			stmt.executeUpdate(sql);
			System.out.println("Student profile has been updated");
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
	
	/**
	 * This method is used to create a new record in tutelage_register_course table,
	 * when new course is registered.
	 * @param CourseID
	 * @return
	 */
	public boolean RegisterCourse(int CourseID){
		try {
			int StudentID = std.getStudentID();
			con = dbConn.getConnections();
			stmt = con.createStatement();
			
			// To insert the Tutelage_Course_Register date, Calendar class is used.
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
			System.out.println("TimeStamp: "+timeStamp);
			// Query to insert the Tutelage_Course_Register details.
			String query = "INSERT INTO Tutelage_Course_Register (studentid, Courseid) VALUES ("+StudentID+","+CourseID+")";
			System.out.println(query);
			stmt.executeUpdate(query);
			System.out.println("Course has been created.");
			con.close();

		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	/**
	 * This method returns the COURSEID for a given Course name.
	 * @param CourseName
	 * @return
	 */
	public int fetchCourseID(String CourseName) {
		ResultSet rs = null;
		// itemID (i.e. COURSEID) variable has been created and set to zero. 
		int CourseID = 0;
		try {
			con = dbConn.getConnections();
			stmt = con.createStatement();

			// Query to fetch the COURSEID for a given COURSE name.
			String sql = "SELECT type_id FROM tutelage_course WHERE type_name='" + CourseName + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			// If the COURSEID is present, then that ID is set to itemID variable.
			while (rs.next()) {
				CourseID = rs.getInt(1);
			}
			con.close();
		} 
		// Returns zero if SQL exception is caught.
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return CourseID;
	}
		
//	@Override
//	public boolean createTransaction(int proID, int quantity, double price) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
}
